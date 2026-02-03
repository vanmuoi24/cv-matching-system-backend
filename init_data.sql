USE smartcv;
INSERT INTO  users
(`id`,
`full_name`,
`create_at`,
`email`,
`password_hash`,
`role`,
`status`,
`update_at`)
VALUES
(1,'Admin',NOW(),'admin@gmail.com','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','ADMIN','ACTIVE',NOW()),
(2,'Recruiter A',NOW(),'rec1@job.com','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','RECRUITER','ACTIVE',NOW()),
(3,'Recruiter B',NOW(),'rec2@job.com','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','RECRUITER','ACTIVE',NOW()),
(4,'Candidate A',NOW(),'c1@job.com','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','CANDIDATE','ACTIVE',NOW()),
(5,'Candidate B',NOW(),'c2@job.com','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','CANDIDATE','ACTIVE',NOW()),
(6,'Candidate C',NOW(),'c3@job.com','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','CANDIDATE','ACTIVE',NOW()),
(7,'Candidate D',NOW(),'c4@job.com','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','CANDIDATE','ACTIVE',NOW()),
(8,'Candidate E',NOW(),'c5@job.com','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','CANDIDATE','ACTIVE',NOW()),
(9,'Candidate F',NOW(),'c6@job.com','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','CANDIDATE','ACTIVE',NOW()),
(10,'Candidate G',NOW(),'c7@job.com','$10$a6iZN2LTRYm.lmse9nIaFOC6fLpiXrLXZBW6X0bV7xq6XnKlZ/No.','CANDIDATE','BLOCKED',NOW());

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
INSERT INTO companies VALUES
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