-- Create database (if not exists, though usually handled by connection string)
CREATE DATABASE IF NOT EXISTS smartcv;
USE smartcv;

-- Table: users
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(50) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    status VARCHAR(255) NOT NULL,
    create_at DATETIME,
    update_at DATETIME,
    role VARCHAR(255) NOT NULL
);

-- Table: companies
CREATE TABLE IF NOT EXISTS companies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    website VARCHAR(255) NOT NULL,
    logo_url VARCHAR(255) NOT NULL UNIQUE,
    status VARCHAR(255) NOT NULL,
    create_at DATETIME,
    owner_id BIGINT NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

-- Table: jobs
CREATE TABLE IF NOT EXISTS jobs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    create_by BIGINT NOT NULL,
    company_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    requirement TEXT NOT NULL,
    skills VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    job_type VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    min_salary INT NOT NULL,
    max_salary INT NOT NULL,
    status VARCHAR(255),
    create_at DATETIME,
    expired_at DATETIME,
    FOREIGN KEY (create_by) REFERENCES users(id),
    FOREIGN KEY (company_id) REFERENCES companies(id)
);

-- Table: candidate_profiles
CREATE TABLE IF NOT EXISTS candidate_profiles (
    user_id BIGINT PRIMARY KEY,
    summary TEXT,
    skills VARCHAR(255) NOT NULL,
    experience_year VARCHAR(255),
    cv_text TEXT,
    cv_file_url VARCHAR(255),
    update_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Table: applications
CREATE TABLE IF NOT EXISTS applications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    candidate_id BIGINT NOT NULL,
    job_id BIGINT NOT NULL,
    similarity_score FLOAT,
    status VARCHAR(255) NOT NULL,
    applied_at DATETIME NOT NULL,
    FOREIGN KEY (candidate_id) REFERENCES users(id),
    FOREIGN KEY (job_id) REFERENCES jobs(id)
);

-- Table: InvalidatedToken
CREATE TABLE IF NOT EXISTS invalidated_token (
    id VARCHAR(255) PRIMARY KEY,
    expiry_time DATETIME
);

-- Insert Users
-- Password for all is "123456" -> $2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qX5H.
INSERT INTO users (full_name, password_hash, email, status, create_at, update_at, role) VALUES 
('Admin User', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qX5H.', 'admin@smartcv.com', 'ACTIVE', NOW(), NOW(), 'ADMIN'),
('Employer One', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qX5H.', 'employer1@smartcv.com', 'ACTIVE', NOW(), NOW(), 'EMPLOYER'),
('Employer Two', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qX5H.', 'employer2@smartcv.com', 'ACTIVE', NOW(), NOW(), 'EMPLOYER'),
('Candidate One', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qX5H.', 'candidate1@smartcv.com', 'ACTIVE', NOW(), NOW(), 'CANDIDATE'),
('Candidate Two', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qX5H.', 'candidate2@smartcv.com', 'ACTIVE', NOW(), NOW(), 'CANDIDATE');

-- Insert Companies
INSERT INTO companies (name, description, website, logo_url, status, create_at, owner_id) VALUES
('Tech Corp', 'Leading tech company in AI.', 'https://techcorp.com', 'https://techcorp.com/logo.png', 'ACTIVE', NOW(), 2),
('Soft Solutions', 'Innovative software solutions.', 'https://softsolutions.com', 'https://softsolutions.com/logo.png', 'ACTIVE', NOW(), 3);

-- Insert Jobs
INSERT INTO jobs (create_by, company_id, title, description, requirement, skills, location, job_type, category, min_salary, max_salary, status, create_at, expired_at) VALUES
(2, 1, 'Java Backend Developer', 'Develop robust backend systems.', '3+ years Java experience.', 'Java, Spring Boot, MySQL', 'San Francisco, CA', 'FULL_TIME', 'Software Development', 80000, 120000, 'OPEN', NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY)),
(2, 1, 'Frontend Developer', 'Create beautiful UIs.', '2+ years React experience.', 'React, TypeScript, CSS', 'Remote', 'FULL_TIME', 'Software Development', 70000, 110000, 'OPEN', NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY)),
(3, 2, 'Data Scientist', 'Analyze large datasets.', 'Strong Python and ML skills.', 'Python, Pandas, Scikit-Learn', 'New York, NY', 'PART_TIME', 'Data Science', 60000, 90000, 'OPEN', NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY));

-- Insert Candidate Profiles
INSERT INTO candidate_profiles (user_id, summary, skills, experience_year, cv_text, cv_file_url, update_at) VALUES
(4, 'Passionate Java Developer with 4 years of experience.', 'Java, Spring Boot, SQL', '4 years', 'Resume text content...', 'https://smartcv.com/cvs/candidate1.pdf', NOW()),
(5, 'Data enthusiast looking for opportunities.', 'Python, R, SQL', '2 years', 'Resume text content...', 'https://smartcv.com/cvs/candidate2.pdf', NOW());

-- Insert Applications
INSERT INTO applications (candidate_id, job_id, similarity_score, status, applied_at) VALUES
(4, 1, 0.85, 'PENDING', NOW()),
(5, 3, 0.90, 'REVIEWING', NOW());
