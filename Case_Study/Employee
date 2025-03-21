create database payroll_manageent_system;
use payroll_manageent_system;

create table Employee(
EmployeeID int primary key auto_increment,
FirstName varchar(100) not null,
LastName varchar(100) not null,
dateOfBirth date,
Gender enum('Female', 'Male', 'Other') default null,
Email varchar(255) unique not null,
PhoneNum varchar(20) not null,
Address varchar(255) not null,
Position varchar(100) not null,
JoiningDate date,
TerminationDate date);

alter table employee modify column Address text not null;
alter table employee modify column TerminationDate date null;
alter table employee auto_increment = 1001;

INSERT INTO Employee (FirstName, LastName, DateOfBirth, Gender, Email, PhoneNum, Address, Position, JoiningDate, TerminationDate) 
VALUES 
('John', 'Doe', '1985-06-15', 'Male', 'john.doe@example.com', '9876543210', '123 Main St', 'Software Engineer', '2020-05-10', NULL),
('Jane', 'Smith', '1990-09-22', 'Female', 'jane.smith@example.com', '8765432109', '456 Elm St', 'HR Manager', '2019-07-20', NULL),
('Alice', 'Brown', '1988-03-10', 'Female', 'alice.brown@example.com', '7654321098', '789 Oak St', 'Data Analyst', '2021-01-15', NULL),
('Bob', 'Williams', '1992-12-05', 'Male', 'bob.williams@example.com', '6543210987', '101 Pine St', 'Marketing Executive', '2022-06-10', NULL),
('Charlie', 'Davis', '1995-07-14', 'Male', 'charlie.davis@example.com', '5432109876', '202 Maple St', 'Sales Associate', '2018-09-01', '2023-12-31'), -- Terminated Employee
('Diana', 'Miller', '1987-09-30', 'Female', 'diana.miller@example.com', '4321098765', '303 Birch St', 'Finance Manager', '2017-03-12', NULL),
('Ethan', 'Harris', '1993-04-22', 'Male', 'ethan.harris@example.com', '3210987654', '404 Cedar St', 'Software Developer', '2016-08-25', NULL),
('Fiona', 'Clark', '1998-01-11', 'Female', 'fiona.clark@example.com', '2109876543', '505 Redwood St', 'Graphic Designer', '2023-04-19', NULL),
('George', 'Lewis', '1989-11-17', 'Male', 'george.lewis@example.com', '1098765432', '606 Elm St', 'Project Manager', '2015-12-05', NULL),
('Hannah', 'Walker', '1991-06-29', 'Female', 'hannah.walker@example.com', '0987654321', '707 Beech St', 'Product Manager', '2014-07-07', NULL);

SELECT * FROM Employee; 

