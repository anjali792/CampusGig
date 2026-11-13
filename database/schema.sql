-- ===================================================================
-- CampusGig Database Schema (MySQL 8.0+)
-- Hyper-local Micro-Internship & Student Project Marketplace
-- ===================================================================

DROP DATABASE IF EXISTS campusgig_db;
CREATE DATABASE campusgig_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE campusgig_db;

-- 1. Users Table (Core authentication & role management)
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(120) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    role ENUM('ROLE_STUDENT', 'ROLE_CLIENT', 'ROLE_ADMIN') NOT NULL,
    avatar_url VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_users_email (email),
    INDEX idx_users_role (role)
) ENGINE=InnoDB;

-- 2. Student Profiles (Extended info, skills, college, portfolios)
CREATE TABLE student_profiles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    college VARCHAR(150),
    major VARCHAR(100),
    graduation_year INT,
    bio TEXT,
    skills_tags VARCHAR(500), -- Comma-separated: "Java, Python, Spring Boot, React, Tailwind CSS"
    github_url VARCHAR(255),
    linkedin_url VARCHAR(255),
    portfolio_url VARCHAR(255),
    hourly_rate DECIMAL(10, 2) DEFAULT 0.00,
    completed_gigs_count INT DEFAULT 0,
    average_rating DECIMAL(3, 2) DEFAULT 5.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_student_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_student_college (college),
    INDEX idx_student_skills (skills_tags)
) ENGINE=InnoDB;

-- 3. Client Profiles (Company/organization info, business leads)
CREATE TABLE client_profiles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    company_name VARCHAR(150) NOT NULL,
    organization_type VARCHAR(100) DEFAULT 'Startup', -- 'Startup', 'Local Business', 'College Club Lead', 'Individual Freelance Client'
    website_url VARCHAR(255),
    location VARCHAR(150),
    description TEXT,
    total_gigs_posted INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_client_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_client_company (company_name)
) ENGINE=InnoDB;

-- 4. Gigs / Micro-Internships Table
CREATE TABLE gigs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_id BIGINT NOT NULL,
    title VARCHAR(200) NOT NULL,
    category VARCHAR(100) NOT NULL, -- 'Web Development', 'Mobile Apps', 'AI & Data Science', 'UI/UX Design', 'Content & Marketing'
    description LONGTEXT NOT NULL,
    budget DECIMAL(10, 2) NOT NULL,
    budget_type ENUM('FIXED', 'HOURLY', 'MONTHLY') DEFAULT 'FIXED',
    deadline DATE NOT NULL,
    required_skills VARCHAR(500) NOT NULL, -- Comma-separated skills
    location_type ENUM('REMOTE', 'ON_CAMPUS', 'HYBRID') DEFAULT 'REMOTE',
    campus_location VARCHAR(150),
    company_name VARCHAR(150),
    external_apply_url VARCHAR(500),
    is_verified BOOLEAN DEFAULT FALSE,
    status ENUM('OPEN', 'IN_PROGRESS', 'CLOSED') DEFAULT 'OPEN',
    applicants_count INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_gig_client FOREIGN KEY (client_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_gigs_category (category),
    INDEX idx_gigs_status (status),
    INDEX idx_gigs_deadline (deadline),
    INDEX idx_gigs_created (created_at)
) ENGINE=InnoDB;

-- 5. Applications / Proposals Table
CREATE TABLE applications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gig_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    cover_letter TEXT NOT NULL,
    proposed_budget DECIMAL(10, 2) NOT NULL,
    estimated_days INT NOT NULL,
    portfolio_link VARCHAR(255),
    status ENUM('PENDING', 'ACCEPTED', 'REJECTED', 'COMPLETED') DEFAULT 'PENDING',
    client_feedback TEXT,
    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    reviewed_at TIMESTAMP NULL,
    CONSTRAINT fk_app_gig FOREIGN KEY (gig_id) REFERENCES gigs(id) ON DELETE CASCADE,
    CONSTRAINT fk_app_student FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE KEY uk_gig_student (gig_id, student_id), -- Prevent duplicate applications
    INDEX idx_app_status (status),
    INDEX idx_app_applied (applied_at)
) ENGINE=InnoDB;
