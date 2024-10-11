CREATE DATABASE IF NOT EXISTS oop;
USE oop;

CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(191) UNIQUE NOT NULL,  -- Reduced to 191 characters
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

INSERT INTO users (username, password, role) VALUES ('admin', 'adminpass', 'ADMIN');
INSERT INTO users (username, password, role) VALUES ('marketing_user', 'marketingpass', 'MARKETING');
INSERT INTO users (username, password, role) VALUES ('sales_user', 'salespass', 'SALES');
