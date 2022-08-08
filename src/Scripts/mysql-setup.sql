DROP DATABASE IF EXISTS bookdb2;
DROP USER IF EXISTS 'bookadmin'@'%';
DROP USER IF EXISTS 'bookuser'@'%';

CREATE DATABASE IF NOT EXISTS bookdb2 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS 'bookadmin'@'%' IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON bookdb2.* TO 'bookadmin'@'%';
CREATE USER IF NOT EXISTS 'bookuser'@'%' IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON bookdb2.* TO 'bookuser'@'%';
FLUSH PRIVILEGES;