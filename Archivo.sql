CREATE DATABASE anonimos;

USE anonimos;

CREATE TABLE person (
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
allname VARCHAR(100) NOT NULL,
cc BIGINT UNIQUE NOT NULL,
birthdate DATE NOT NULL,
city VARCHAR(100) NOT NULL
);

INSERT INTO person (allname, cc, birthdate, city)
VALUES ('Nombre Apellido', 1234567892, '2023-09-25', 'Ciudad de Nacimiento');
SELECT * FROM person;

CREATE TABLE alcoholic (
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
allname VARCHAR(100) NOT NULL,
cc BIGINT UNIQUE NOT NULL,
birthdate DATE NOT NULL,
city VARCHAR(100) NOT NULL,
sessions INT NOT NULL,
nickname VARCHAR(100) NOT NULL
);

CREATE TABLE psychologist(
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
allname VARCHAR(100) NOT NULL,
cc BIGINT UNIQUE NOT NULL,
birthdate DATE NOT NULL,
city VARCHAR(100) NOT NULL,
graduation DATE NOT NULL,
days INT NOT NULL,
supportedsessions INT NOT NULL,
salary INT NOT NULL
);

CREATE TABLE services (
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
allname VARCHAR(100) NOT NULL,
cc BIGINT UNIQUE NOT NULL,
birthdate DATE NOT NULL,
city VARCHAR(100) NOT NULL,
salary DOUBLE NOT NULL,
cleanup INT NOT NULL
);

CREATE TABLE administrador(
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
allname VARCHAR(100) NOT NULL,
cc BIGINT UNIQUE NOT NULL,
birthdate DATE NOT NULL,
city VARCHAR(100) NOT NULL
);

SELECT * FROM administrador;
SELECT * FROM alcoholic;
SELECT * FROM psychologist;
SELECT * FROM services;
INSERT INTO administrador (allname, cc, birthdate, city)
VALUES ('jose', 123 , '2023-09-25', 'Ciudad de Nacimiento');

INSERT INTO alcoholic (allname, cc, birthdate, city, sessions, nickname)
VALUES ('Juan Pérez', 1234567890, '1990-05-15', 'Ciudad A', 5, 'juancito123');

INSERT INTO alcoholic (allname, cc, birthdate, city, sessions, nickname)
VALUES ('María López', 9876543210, '1985-09-20', 'Ciudad B', 8, 'mary1985');

INSERT INTO psychologist (allname, cc, birthdate, city, graduation, days, supportedsessions, salary)
VALUES ('Dr. Ana Rodríguez', 4567891234, '1975-03-10', 'Ciudad A', '2000-07-15', 4, 20, 5000);

INSERT INTO psychologist (allname, cc, birthdate, city, graduation, days, supportedsessions, salary)
VALUES ('Dr. Carlos García', 7890123456, '1980-11-25', 'Ciudad C', '2005-05-20', 3, 15, 4500);

INSERT INTO services (allname, cc, birthdate, city, salary, cleanup)
VALUES ('Servicio de Limpieza', 987612345, '1998-02-28', 'Ciudad A', 1500.50, 10);

INSERT INTO services (allname, cc, birthdate, city, salary, cleanup)
VALUES ('Servicio de Mantenimiento', 123498765, '1995-07-12', 'Ciudad B', 1800.75, 12);
