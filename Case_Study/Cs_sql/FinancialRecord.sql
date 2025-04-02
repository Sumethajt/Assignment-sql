create table FinancialRecord(
RecordID int primary key not null auto_increment,
EmployeeID int,
RecordDate Date not null,
Descriptions text not null,
Amount decimal(10,2),
RecordType enum(
'Income', 'Expense', 'Tax Payment','Pension Contribution', 'Health Insurance Deduction', 'Retirement Benefit') not null,
foreign key (EmployeeID) references Employee(EmployeeID) on delete cascade
);

alter table FinancialRecord auto_increment = 101; 

INSERT INTO FinancialRecord (EmployeeID, RecordDate, Descriptions, Amount, RecordType) 
VALUES 
(1001, '2023-12-01', 'Salary Payment', 5000.00, 'Income'),
(1002, '2024-06-15', 'Office Supplies Purchase', 200.00, 'Expense'),
(1003, '2025-03-10', 'Quarterly Tax Payment', 2500.00, 'Tax Payment'),
(1004, '2024-09-20', 'Monthly Pension Contribution', 800.00, 'Pension Contribution'),
(1005, '2023-11-30', 'Health Insurance Premium Deduction', 300.00, 'Health Insurance Deduction'), -- Charlie (Last record before termination)
(1006, '2022-07-10', 'Retirement Fund Payout', 20000.00, 'Retirement Benefit'),
(1007, '2021-05-28', 'Monthly Salary Payment', 7000.00, 'Income'),
(1008, '2024-08-30', 'Travel Expense Reimbursement', 500.00, 'Expense'),
(1009, '2025-02-28', 'Annual Income Tax Payment', 3000.00, 'Tax Payment'),
(1010, '2020-01-01', 'Company Pension Fund Contribution', 900.00, 'Pension Contribution');

select * from financialrecord;
