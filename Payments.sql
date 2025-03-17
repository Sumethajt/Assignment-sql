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
