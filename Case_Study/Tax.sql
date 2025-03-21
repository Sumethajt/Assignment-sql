create table tax(
TaxID int primary key not null auto_increment,
EmployeeID int,
TaxYear year not null,
TaxableIncome DECIMAL(10,2) NOT NULL,
TaxAmount DECIMAL(10,2) NOT NULL,
FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON DELETE CASCADE
);

alter table tax auto_increment = 2001;
INSERT INTO Tax (EmployeeID, TaxYear, TaxableIncome, TaxAmount) 
VALUES 
(1001, 2021, 48000.00, 7200.00),  -- John (Joined 2020, so tax starts from 2021)
(1002, 2020, 62000.00, 9300.00),  -- Jane (Joined 2019, tax from 2020)
(1003, 2022, 45000.00, 6750.00),  -- Alice (Joined 2021, tax from 2022)
(1004, 2023, 55000.00, 8250.00),  -- Bob (Joined 2022, tax from 2023)
(1005, 2019, 40000.00, 6000.00),  -- Charlie (Joined 2018, tax valid till 2023 due to termination)
(1006, 2018, 71000.00, 10650.00), -- Diana (Joined 2017, so tax from 2018)
(1007, 2017, 53000.00, 7950.00),  -- Ethan (Joined 2016, tax from 2017)
(1008, 2024, 49000.00, 7350.00),  -- Fiona (Joined 2023, tax from 2024)
(1009, 2016, 66000.00, 9900.00),  -- George (Joined 2015, tax from 2016)
(1010, 2015, 77000.00, 11550.00); -- Hannah (Joined 2014, tax from 2015)

select * from Tax;