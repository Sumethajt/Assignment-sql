CREATE TABLE CourierServices (
    ServiceID INT PRIMARY KEY,
    ServiceName VARCHAR(100) NOT NULL,
    Cost DECIMAL(8,2) NOT NULL
);

-- inserting datas --

INSERT INTO CourierServices (ServiceID, ServiceName, Cost)
VALUES 
(10, 'Standard Delivery', 10.99),
(20, 'Express Delivery', 25.99),
(30, 'Overnight Shipping', 50.00),
(40, 'International Shipping', 100.00),
(50, 'Same-Day Delivery', 75.00);
