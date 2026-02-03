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
INSERT INTO users (`id`, `full_name`, `password_hash`, `email`, `status`, `create_at`, `update_at`, `role`) VALUES
(1,'Asmin','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','admin@job.com','ACTIVE',NOW(),NOW(),'ADMIN'),
(2,'Recruiter A','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','rec1@job.com','ACTIVE',NOW(),NOW(),'RECRUITER'),
(3,'Recruiter B','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','rec2@job.com','ACTIVE',NOW(),NOW(),'RECRUITER'),
(4,'Candidate A','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','c1@job.com','ACTIVE',NOW(),NOW(),'CANDIDATE'),
(5,'Candidate B','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','c2@job.com','ACTIVE',NOW(),NOW(),'CANDIDATE'),
(6,'Candidate C','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','c3@job.com','ACTIVE',NOW(),NOW(),'CANDIDATE'),
(7,'Candidate D','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','c4@job.com','ACTIVE',NOW(),NOW(),'CANDIDATE'),
(8,'Candidate E','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','c5@job.com','ACTIVE',NOW(),NOW(),'CANDIDATE'),
(9,'Candidate F','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','c6@job.com','ACTIVE',NOW(),NOW(),'CANDIDATE'),
(10,'Candidate G','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','c7@job.com','BLOCKED',NOW(),NOW(),'CANDIDATE');

-- =========================
-- INSERT CANDIDATE_PROFILES
-- =========================
INSERT INTO candidate_profiles VALUES
(4,'Java Dev',JSON_ARRAY('Java','Spring'),2,'API Dev','/cv/1.pdf',NOW()),
(5,'Frontend Dev',JSON_ARRAY('React','JS'),3,'UI Dev','/cv/2.pdf',NOW()),
(6,'Fullstack Dev',JSON_ARRAY('Java','React'),4,'FS Dev','/cv/3.pdf',NOW()),
(7,'Android Dev',JSON_ARRAY('Kotlin'),2,'Mobile','/cv/4.pdf',NOW()),
(8,'Data Analyst',JSON_ARRAY('SQL','Python'),1,'Data','/cv/5.pdf',NOW()),
(9,'AI Engineer',JSON_ARRAY('ML','Python'),3,'AI','/cv/6.pdf',NOW()),
(10,'QA Engineer',JSON_ARRAY('Testing'),2,'QA','/cv/7.pdf',NOW()),
(1,'Backend Intern',JSON_ARRAY('Java'),1,'Intern','/cv/8.pdf',NOW()),
(2,'UI Designer',JSON_ARRAY('Figma'),2,'Design','/cv/9.pdf',NOW()),
(3,'DevOps',JSON_ARRAY('Docker'),3,'DevOps','/cv/10.pdf',NOW());

-- =========================
-- INSERT COMPANIES
-- =========================
INSERT INTO companies (`id`, `name`, `description`, `website`, `logo_url`, `owner_id`, `status`, `create_at`) VALUES
(1,'TechSoft','Software','tech.com','/l1.png',2,'ACTIVE',NOW()),
(2,'DataCorp','Data','data.com','/l2.png',2,'ACTIVE',NOW()),
(3,'AILab','AI','ai.com','/l3.png',3,'ACTIVE',NOW()),
(4,'FinTech','Finance','fin.com','/l4.png',3,'ACTIVE',NOW()),
(5,'Cloudify','Cloud','cloud.com','/l5.png',2,'ACTIVE',NOW()),
(6,'MobileX','Mobile','mobile.com','/l6.png',3,'ACTIVE',NOW()),
(7,'GameStudio','Game','game.com','/l7.png',2,'ACTIVE',NOW()),
(8,'EduTech','Edu','edu.com','/l8.png',3,'ACTIVE',NOW()),
(9,'HealthPlus','Health','health.com','/l9.png',2,'ACTIVE',NOW()),
(10,'EcomPro','Ecom','ecom.com','/l10.png',3,'ACTIVE',NOW());

-- =========================
-- INSERT JOBS
-- =========================
INSERT INTO jobs VALUES
(1,1,2,'Java Dev','Backend','Java 2y',JSON_ARRAY('Java'), 'HN','FULL_TIME','IT',1000,2000,'OPEN',NOW(),DATE_ADD(NOW(),INTERVAL 30 DAY)),
(2,2,2,'Data Analyst','Data','SQL',JSON_ARRAY('SQL'), 'HCM','FULL_TIME','DATA',900,1800,'OPEN',NOW(),DATE_ADD(NOW(),INTERVAL 30 DAY)),
(3,3,3,'AI Eng','AI','ML',JSON_ARRAY('ML'), 'HN','FULL_TIME','AI',1500,3000,'OPEN',NOW(),DATE_ADD(NOW(),INTERVAL 45 DAY)),
(4,4,3,'Backend','API','Spring',JSON_ARRAY('Spring'), 'DN','FULL_TIME','IT',1200,2200,'OPEN',NOW(),DATE_ADD(NOW(),INTERVAL 30 DAY)),
(5,5,2,'DevOps','CI/CD','Docker',JSON_ARRAY('Docker'), 'HN','FULL_TIME','DEVOPS',1300,2500,'OPEN',NOW(),DATE_ADD(NOW(),INTERVAL 60 DAY)),
(6,6,3,'Android','Mobile','Kotlin',JSON_ARRAY('Kotlin'), 'HCM','FULL_TIME','MOBILE',1000,2000,'OPEN',NOW(),DATE_ADD(NOW(),INTERVAL 30 DAY)),
(7,7,2,'Game Dev','Game','Unity',JSON_ARRAY('Unity'), 'HN','FULL_TIME','GAME',1100,2100,'OPEN',NOW(),DATE_ADD(NOW(),INTERVAL 30 DAY)),
(8,8,3,'Frontend','UI','React',JSON_ARRAY('React'), 'HN','FULL_TIME','FE',900,1800,'OPEN',NOW(),DATE_ADD(NOW(),INTERVAL 30 DAY)),
(9,9,2,'QA','Test','Auto',JSON_ARRAY('Selenium'), 'HCM','FULL_TIME','QA',800,1500,'OPEN',NOW(),DATE_ADD(NOW(),INTERVAL 30 DAY)),
(10,10,3,'PM','Manage','Agile',JSON_ARRAY('Agile'), 'HN','FULL_TIME','PM',1500,3000,'OPEN',NOW(),DATE_ADD(NOW(),INTERVAL 45 DAY));

-- =========================
-- INSERT APPLICATIONS
-- =========================
INSERT INTO applications VALUES
(1,1,4,0.85,'PENDING',NOW()),
(2,2,5,0.80,'PENDING',NOW()),
(3,3,6,0.90,'SHORTLISTED',NOW()),
(4,4,7,0.78,'REJECTED',NOW()),
(5,5,8,0.88,'PENDING',NOW()),
(6,6,9,0.92,'SHORTLISTED',NOW()),
(7,7,10,0.70,'REJECTED',NOW()),
(8,8,4,0.83,'PENDING',NOW()),
(9,9,5,0.79,'PENDING',NOW()),
(10,10,6,0.95,'HIRED',NOW());