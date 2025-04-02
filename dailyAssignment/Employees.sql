CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    ContactNumber VARCHAR(20) NOT NULL,
    Role VARCHAR(50) NOT NULL,
    Salary DECIMAL(10,2) NOT NULL
);

-- inserting datas --

INSERT INTO Employees (EmployeeID, Name, Email, ContactNumber, Role, Salary)
VALUES 
(1051, 'John Doe', 'john.doe@example.com', '9876543225', 'Courier Staff', 45000.00),
(2098, 'Sarah Lee', 'sarah.lee@example.com', '9876543226', 'Manager', 60000.00),
(3175, 'Michael Scott', 'michael.scott@example.com', '9876543227', 'Courier Staff', 48000.00),
(4240, 'Pam Beesly', 'pam.beesly@example.com', '9876543228', 'HR', 55000.00),
(5312, 'Jim Halpert', 'jim.halpert@example.com', '9876543229', 'Courier Staff', 47000.00),
(6789, 'Ryan Howard', 'ryan.howard@example.com', '9876543230', 'Logistics Head', 62000.00),
(7213, 'Angela Martin', 'angela.martin@example.com', '9876543231', 'Finance', 53000.00),
(8564, 'Stanley Hudson', 'stanley.hudson@example.com', '9876543232', 'Courier Staff', 46000.00),
(9032, 'Toby Flenderson', 'toby.flenderson@example.com', '9876543233', 'HR', 52000.00),
(10025, 'Meredith Palmer', 'meredith.palmer@example.com', '9876543234', 'Courier Staff', 45000.00),
(11247, 'Andy Bernard', 'andy.bernard@example.com', '9876543235', 'Manager', 61000.00),
(12089, 'Kelly Kapoor', 'kelly.kapoor@example.com', '9876543236', 'Customer Service', 50000.00),
(13542, 'Creed Bratton', 'creed.bratton@example.com', '9876543237', 'Courier Staff', 44000.00),
(14923, 'Erin Hannon', 'erin.hannon@example.com', '9876543238', 'Customer Service', 48000.00),
(15789, 'Oscar Martinez', 'oscar.martinez@example.com', '9876543239', 'Finance', 54000.00);

select * from employees;
