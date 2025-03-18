CREATE TABLE Payments (
    PaymentID INT PRIMARY KEY AUTO_INCREMENT,
    CourierID INT NOT NULL,
    LocationID INT NOT NULL,
    Amount DECIMAL(10,2) NOT NULL,
    PaymentDate DATE NOT NULL,
    FOREIGN KEY (CourierID) REFERENCES Couriers(CourierID),
    FOREIGN KEY (LocationID) REFERENCES Locations(LocationID)
);

-- insert datas --

INSERT INTO Payments (CourierID, LocationID, Amount, PaymentDate)
VALUES 
(101, 1, 10.99, '2025-03-17'),
(102, 2, 25.99, '2025-03-16'),
(103, 3, 50.00, '2025-03-15'),
(104, 4, 100.00, '2025-03-14'),
(105, 5, 75.00, '2025-03-13'),
(106, 6, 10.99, '2025-03-12'),
(107, 7, 25.99, '2025-03-11'),
(108, 8, 50.00, '2025-03-10'),
(109, 9, 100.00, '2025-03-09'),
(110, 10, 75.00, '2025-03-08'),
(111, 11, 10.99, '2025-03-07'),
(112, 12, 25.99, '2025-03-06'),
(113, 13, 50.00, '2025-03-05'),
(114, 14, 100.00, '2025-03-04'),
(115, 15, 75.00, '2025-03-03');

select * from payments;

-- adding packageid as foreign key --

alter table payments add column packageID INT NOT NULL;
alter table payments add constraint foreign key(packageID) references package(packageID);

-- setting paymentdate and packageid --

UPDATE Payments SET PaymentDate = '2025-03-14', PackageID = 1001 WHERE CourierID = 101;
UPDATE Payments SET PaymentDate = '2025-03-18', PackageID = 1002 WHERE CourierID = 102;
UPDATE Payments SET PaymentDate = '2025-03-16', PackageID = 1003 WHERE CourierID = 103;
UPDATE Payments SET PaymentDate = '2025-03-10', PackageID = 1004 WHERE CourierID = 104;
UPDATE Payments SET PaymentDate = '2025-03-18', PackageID = 1005 WHERE CourierID = 105;
UPDATE Payments SET PaymentDate = '2025-03-14', PackageID = 1006 WHERE CourierID = 106;
UPDATE Payments SET PaymentDate = '2025-03-17', PackageID = 1007 WHERE CourierID = 107;
UPDATE Payments SET PaymentDate = '2025-03-07', PackageID = 1008 WHERE CourierID = 108;
UPDATE Payments SET PaymentDate = '2025-03-23', PackageID = 1009 WHERE CourierID = 109;
UPDATE Payments SET PaymentDate = '2025-03-14', PackageID = 1010 WHERE CourierID = 110;
UPDATE Payments SET PaymentDate = '2025-03-25', PackageID = 1011 WHERE CourierID = 111;
UPDATE Payments SET PaymentDate = '2025-03-18', PackageID = 1012 WHERE CourierID = 112;
UPDATE Payments SET PaymentDate = '2025-03-08', PackageID = 1013 WHERE CourierID = 113;
UPDATE Payments SET PaymentDate = '2025-03-23', PackageID = 1014 WHERE CourierID = 114;
UPDATE Payments SET PaymentDate = '2025-03-16', PackageID = 1015 WHERE CourierID = 115;
UPDATE Payments SET PaymentDate = '2025-03-21', PackageID = 1016 WHERE CourierID = 116;
UPDATE Payments SET PaymentDate = '2025-03-18', PackageID = 1017 WHERE CourierID = 117;
UPDATE Payments SET PaymentDate = '2025-03-14', PackageID = 1018 WHERE CourierID = 118;
UPDATE Payments SET PaymentDate = '2025-03-18', PackageID = 1019 WHERE CourierID = 119;
UPDATE Payments SET PaymentDate = '2025-03-14', PackageID = 1020 WHERE CourierID = 120;
