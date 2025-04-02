create table PayRoll(
PayrollID int primary key not null auto_increment,
EmployeeID int ,
PayPeriodStartDate date not null,
PayPeriodEndDate date not null,
BasicSalary decimal(10,2) not null,
OverTimePay decimal(10,2) default 0.00,
Deduction decimal(10,2) default 0.00,
NetSalary decimal(10,2) not null,
FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON DELETE CASCADE
);

INSERT INTO Payroll (EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deduction, NetSalary) 
VALUES 
(1001, '2025-01-01', '2025-01-31', 5000.00, 200.00, 300.00, 4900.00),
(1002, '2024-12-01', '2024-12-31', 6000.00, 250.00, 400.00, 5850.00),
(1003, '2025-03-01', '2025-03-31', 4500.00, 150.00, 250.00, 4400.00),
(1004, '2024-11-01', '2024-11-30', 5500.00, 180.00, 320.00, 5360.00),
(1005, '2023-10-01', '2023-10-31', 4000.00, 100.00, 200.00, 3900.00), 
(1006, '2022-09-01', '2022-09-30', 7000.00, 300.00, 500.00, 6800.00),
(1007, '2021-08-01', '2021-08-31', 5200.00, 220.00, 350.00, 5070.00),
(1008, '2024-07-01', '2024-07-31', 4800.00, 190.00, 280.00, 4710.00),
(1009, '2025-06-01', '2025-06-30', 6500.00, 270.00, 450.00, 6320.00),
(1010, '2020-05-01', '2020-05-31', 7500.00, 350.00, 600.00, 7250.00);

select * from payroll;
