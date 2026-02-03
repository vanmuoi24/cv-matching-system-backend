package com.example.smartcv_backend.service;

import com.example.smartcv_backend.dto.request.ChangePasswordRequest;
import com.example.smartcv_backend.dto.request.RefreshRequest;
import com.example.smartcv_backend.dto.response.AuthenticationRequest;
import com.example.smartcv_backend.dto.response.AuthenticationResponse;
import com.example.smartcv_backend.entity.InvalidatedToken;
import com.example.smartcv_backend.entity.User;
import com.example.smartcv_backend.exception.AppException;
import com.example.smartcv_backend.exception.ErrorCode;
import com.example.smartcv_backend.mapper.UserMapper;
import com.example.smartcv_backend.repository.InvalidatedTokenRepository;
import com.example.smartcv_backend.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuthService.class);

    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    public AuthenticationResponse authenticated(AuthenticationRequest request) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        var userResponse = userMapper.toUserResponse(user);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + VALID_DURATION);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated)
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        var token = generateToken(user);
        System.out.println(user.getEmail());
        System.out.println("mapper" + userResponse);
        return AuthenticationResponse.builder().token(token).user(userResponse).expiryTime(expiryDate).build();
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(String.valueOf(user.getId()))
                .issuer("smartcv.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))

                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {

            throw new RuntimeException(e);
        }
    }
    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (user.getRole() != null) {
            stringJoiner.add("ROLE_" + user.getRole());
        }
        return stringJoiner.toString();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh)
            throws JOSEException, ParseException {

        SignedJWT signedJWT = SignedJWT.parse(token);

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        if (!signedJWT.verify(verifier)) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        Date expiryTime;
        if (isRefresh) {
            expiryTime = Date.from(
                    signedJWT.getJWTClaimsSet().getIssueTime().toInstant()
                            .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS));
        } else {

            expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        }

        if (expiryTime.before(new Date())) {

            throw new AppException(ErrorCode.EXPIRED_TOKEN);
        }


        String jti = signedJWT.getJWTClaimsSet().getJWTID();
        if (invalidatedTokenRepository.existsById(jti)) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        System.out.println("✅ Token verified successfully: "
                + signedJWT.getJWTClaimsSet().getSubject()
                + ", JWT ID: " + jti
                + ", Expiry: " + expiryTime);

        return signedJWT;
    }

    public AuthenticationResponse refreshToken(RefreshRequest request)
            throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken(), true);
        var jti = signedJWT.getJWTClaimsSet().getJWTID();
        var oldExpiry = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jti)
                .expiryTime(oldExpiry)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
        var username = signedJWT.getJWTClaimsSet().getSubject();
        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
        var newToken = generateToken(user);
        Date now = new Date();
        Date newExpiryTime = new Date(now.getTime() + VALID_DURATION * 1000);
        var userResponse = userMapper.toUserResponse(user);
        return AuthenticationResponse.builder()
                .token(newToken)
                .user(userResponse)
                .expiryTime(newExpiryTime)
                .build();
    }

    public void logout(com.example.smartcv_backend.dto.request.LogoutRequest request)
            throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken(), false);
        invalidatedTokenRepository.save(
                com.example.smartcv_backend.entity.InvalidatedToken.builder()
                        .id(signedJWT.getJWTClaimsSet().getJWTID())
                        .build());
    }

    public void changePassword(ChangePasswordRequest request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken(), false);
        var id = signedJWT.getJWTClaimsSet().getSubject();
        var user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        // Kiểm tra confirmPassword
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new AppException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        // Kiểm tra mật khẩu cũ
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.OLD_PASSWORD_NOT_MATCH);
        }

        // Cập nhật mật khẩu mới
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

}
