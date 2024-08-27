CREATE DATABASE shopforhome;
USE shopforhome;

#Create User Table
CREATE TABLE users(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(255) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL
);

#Create Role Table
CREATE TABLE roles(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO roles VALUES 
(1, "USER"),
(2, "ADMIN");

#Create Role Lookup Table
CREATE TABLE users_roles(
user_id BIGINT NOT NULL REFERENCES users(id),
role_id BIGINT NOT NULL REFERENCES roles(id)
);

#Create Products Table
CREATE TABLE products(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL UNIQUE,
description VARCHAR(255),
category VARCHAR(255),
price FLOAT(8,2) NOT NULL,
stock INT DEFAULT(0),
image VARCHAR(255)
);

#Create Coupons Table
CREATE TABLE coupons(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
code VARCHAR(255) UNIQUE NOT NULL,
discount_percent INT NOT NULL,
exp_Date DATE NOT NULL
);

#Create Orders Table
CREATE TABLE orders(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
user_id BIGINT NOT NULL REFERENCES users(id),
date DATE NOT NULL,
total_price FLOAT(9,2) NOT NULL,
coupon_id BIGINT REFERENCES coupons(id)
);
