CREATE TABLE Couriers (
    CourierID INT PRIMARY KEY AUTO_INCREMENT,
    SenderName VARCHAR(255) NOT NULL,
    SenderAddress TEXT NOT NULL,
    ReceiverName VARCHAR(255) NOT NULL,
    ReceiverAddress TEXT NOT NULL,
    Weight DECIMAL(5,2) NOT NULL,
    Status ENUM('Pending', 'In Transit', 'Delivered', 'Cancelled') DEFAULT 'Pending',
    TrackingNumber VARCHAR(20) UNIQUE NOT NULL,
    DeliveryDate DATE,
    UserID INT NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);
ALTER TABLE Couriers MODIFY COLUMN Status ENUM('Pending', 'In Transit', 'Delivered', 'Cancelled', 'Shipped') NOT NULL;

-- inserting datas--

INSERT INTO Couriers (CourierID, SenderName, SenderAddress, ReceiverName, ReceiverAddress, Weight, Status, TrackingNumber, DeliveryDate, UserID)
VALUES 
(101, 'Alice Johnson', '123 Main St, NY', 'Michael Brown', '555 Oak St, TX', 2.5, 'Pending', 'TRK12345', '2025-03-20', 1),
(102, 'Bob Smith', '456 Elm St, LA', 'Emily Davis', '777 Pine St, CA', 5.0, 'In Transit', 'TRK67890', '2025-03-21', 2),
(103, 'Charlie Brown', '789 Oak St, TX', 'Sophia White', '222 Pine St, WA', 3.2, 'Delivered', 'TRK11122', '2025-03-18', 3),
(104, 'Diana Ross', '159 Maple St, FL', 'Liam Carter', '444 Cedar St, GA', 6.0, 'Pending', 'TRK33444', '2025-03-22', 4),
(105, 'Ethan Hunt', '753 Birch St, CA', 'Olivia Turner', '666 Redwood St, OR', 4.8, 'Shipped', 'TRK55666', '2025-03-19', 5),
(106, 'Fiona Harper', '852 Walnut St, WA', 'Nathan Reynolds', '111 Cherry St, NV', 2.1, 'Delivered', 'TRK77788', '2025-03-17', 6),
(107, 'George Wilson', '963 Poplar St, MI', 'Megan Parker', '333 Aspen St, IL', 7.5, 'In Transit', 'TRK99887', '2025-03-23', 7),
(108, 'Hannah Green', '741 Pine St, AZ', 'Jason Moore', '666 Willow St, CO', 3.9, 'Shipped', 'TRK55443', '2025-03-18', 8),
(109, 'Isaac Adams', '369 Redwood St, CO', 'Ella Bennett', '777 Cypress St, FL', 5.6, 'Pending', 'TRK11223', '2025-03-25', 9),
(110, 'Jessica Lee', '147 Cedar St, NC', 'Daniel Harris', '999 Palm St, TX', 4.3, 'Delivered', 'TRK66554', '2025-03-20', 10),
(111, 'Kevin Rogers', '258 Birch St, TX', 'Victoria Cooper', '111 Pinecone St, PA', 6.2, 'In Transit', 'TRK77889', '2025-03-26', 11),
(112, 'Laura Bennett', '369 Elm St, NV', 'David Scott', '333 Oakridge St, NY', 4.7, 'Pending', 'TRK22334', '2025-03-21', 12),
(113, 'Mike Dawson', '951 Oak St, SC', 'Sophia Green', '555 Hickory St, WA', 3.4, 'Shipped', 'TRK88990', '2025-03-19', 13),
(114, 'Nancy Carter', '753 Maple St, WA', 'Luke Thompson', '222 Beech St, IL', 5.9, 'Pending', 'TRK99001', '2025-03-24', 14),
(115, 'Oliver Young', '357 Pine St, FL', 'Grace Mitchell', '444 Fir St, GA', 4.1, 'Delivered', 'TRK77665', '2025-03-22', 15);

select * from couriers;
desc couriers ;

--inserting more values --

INSERT INTO Couriers (CourierID, SenderName, SenderAddress, ReceiverName, ReceiverAddress, Weight, Status, TrackingNumber, DeliveryDate, UserID)
VALUES 
(116, 'Isaac Adams', '369 Redwood St, CO', 'Luke Thompson', '222 Beech St, IL', 5.7, 'Shipped', 'TRK10016', '2025-03-23', 9);
INSERT INTO Couriers (CourierID, SenderName, SenderAddress, ReceiverName, ReceiverAddress, Weight, Status, TrackingNumber, DeliveryDate, UserID)
VALUES 
(117, 'Jessica Lee', '147 Cedar St, NC', 'Noah Carter', '555 Redwood St, TX', 5.4, 'In Transit', 'TRK10017', '2025-03-21', 10),
(118, 'Charlie Brown', '789 Oak St, TX', 'Bruce Wayne', '666 Gotham St, NJ', 7.1, 'Pending', 'TRK10020', '2025-03-25', 3),
(119, 'Nancy Carter', '753 Maple St, WA', 'Tony Stark', '111 Hero Ave, NY', 8.2, 'Delivered', 'TRK10019', '2025-03-19', 14),
(120, 'Diana Ross', '159 Maple St, FL', 'Jack Dawson', '777 Fir St, NV', 3.6, 'In Transit', 'TRK10018', '2025-03-20', 4);

alter table Couriers add column Packages VARCHAR(255);
ALTER TABLE Couriers ADD COLUMN ServiceID INT NOT NULL;
ALTER TABLE Couriers ADD CONSTRAINT FOREIGN KEY(ServiceID) REFERENCES CourierServices(ServiceID);

-- adding package details --
UPDATE Couriers SET PackageID = 1000 WHERE CourierID = 101;
UPDATE Couriers SET PackageID = 1001 WHERE CourierID = 102;
UPDATE Couriers SET PackageID = 1002 WHERE CourierID = 103;
UPDATE Couriers SET PackageID = 1003 WHERE CourierID = 104;
UPDATE Couriers SET PackageID = 1004 WHERE CourierID = 105;
UPDATE Couriers SET PackageID = 1005 WHERE CourierID = 106;
UPDATE Couriers SET PackageID = 1006 WHERE CourierID = 107;
UPDATE Couriers SET PackageID = 1007 WHERE CourierID = 108;
UPDATE Couriers SET PackageID = 1008 WHERE CourierID = 109;
UPDATE Couriers SET PackageID = 1009 WHERE CourierID = 110;
UPDATE Couriers SET PackageID = 1010 WHERE CourierID = 111;
UPDATE Couriers SET PackageID = 1011 WHERE CourierID = 112;
UPDATE Couriers SET PackageID = 1012 WHERE CourierID = 113;
UPDATE Couriers SET PackageID = 1013 WHERE CourierID = 114;
UPDATE Couriers SET PackageID = 1014 WHERE CourierID = 115;
UPDATE Couriers SET PackageID =  1015 WHERE CourierID = 116;
UPDATE Couriers SET PackageID = 1016 WHERE CourierID = 117;
UPDATE Couriers SET PackageID = 1017 WHERE CourierID = 118;
UPDATE Couriers SET PackageID = 1018 WHERE CourierID = 119;
UPDATE Couriers SET PackageID = 1019 WHERE CourierID = 120;
ALTER TABLE Couriers DROP COLUMN Packages;
ALTER TABLE Couriers ADD COLUMN PackageID INT NOT NULL;
ALTER TABLE Couriers ADD CONSTRAINT FOREIGN KEY(PackageID ) REFERENCES Package(PackageID );
ALTER TABLE Couriers 

-- adding shipment date --
ADD COLUMN ShipmentDate DATE NOT NULL DEFAULT '2025-01-01';
UPDATE Couriers SET ShipmentDate = '2025-03-15' WHERE CourierID = 101;
UPDATE Couriers SET ShipmentDate = '2025-03-19' WHERE CourierID = 102;
UPDATE Couriers SET ShipmentDate = '2025-03-17' WHERE CourierID = 103;
UPDATE Couriers SET ShipmentDate = '2025-03-12' WHERE CourierID = 104;
UPDATE Couriers SET ShipmentDate = '2025-03-19' WHERE CourierID = 105;
UPDATE Couriers SET ShipmentDate = '2025-03-15' WHERE CourierID = 106;
UPDATE Couriers SET ShipmentDate = '2025-03-18' WHERE CourierID = 107;
UPDATE Couriers SET ShipmentDate = '2025-03-08' WHERE CourierID = 108;
UPDATE Couriers SET ShipmentDate = '2025-03-24' WHERE CourierID = 109;
UPDATE Couriers SET ShipmentDate = '2025-03-15' WHERE CourierID = 110;
UPDATE Couriers SET ShipmentDate = '2025-03-26' WHERE CourierID = 111;
UPDATE Couriers SET ShipmentDate = '2025-03-19' WHERE CourierID = 112;
UPDATE Couriers SET ShipmentDate = '2025-03-09' WHERE CourierID = 113;
UPDATE Couriers SET ShipmentDate = '2025-03-24' WHERE CourierID = 114;
UPDATE Couriers SET ShipmentDate = '2025-03-17' WHERE CourierID = 115;
UPDATE Couriers SET ShipmentDate = '2025-03-22' WHERE CourierID = 116;
UPDATE Couriers SET ShipmentDate = '2025-03-19' WHERE CourierID = 117;
UPDATE Couriers SET ShipmentDate = '2025-03-15' WHERE CourierID = 118;
UPDATE Couriers SET ShipmentDate = '2025-03-19' WHERE CourierID = 119;
UPDATE Couriers SET ShipmentDate = '2025-03-15' WHERE CourierID = 120;

ALTER TABLE Couriers DROP COLUMN Weight;

alter table couriers add column EmployeeID INT NOT NULL;

UPDATE Couriers SET EmployeeID = 1051 WHERE CourierID = 101;
UPDATE Couriers SET EmployeeID = 2098 WHERE CourierID = 102;
UPDATE Couriers SET EmployeeID = 3175 WHERE CourierID = 103;
UPDATE Couriers SET EmployeeID = 4240 WHERE CourierID = 104;
UPDATE Couriers SET EmployeeID = 5312 WHERE CourierID = 105;
UPDATE Couriers SET EmployeeID = 6789 WHERE CourierID = 106;
UPDATE Couriers SET EmployeeID = 7213 WHERE CourierID = 107;
UPDATE Couriers SET EmployeeID = 8564 WHERE CourierID = 108;
UPDATE Couriers SET EmployeeID = 9032 WHERE CourierID = 109;
UPDATE Couriers SET EmployeeID = 10025 WHERE CourierID = 110;
UPDATE Couriers SET EmployeeID = 11247 WHERE CourierID = 111;
UPDATE Couriers SET EmployeeID = 12089 WHERE CourierID = 112;
UPDATE Couriers SET EmployeeID = 13542 WHERE CourierID = 113;
UPDATE Couriers SET EmployeeID = 14923 WHERE CourierID = 114;
UPDATE Couriers SET EmployeeID = 15789 WHERE CourierID = 115;
UPDATE Couriers SET EmployeeID = 1051 WHERE CourierID = 116;
UPDATE Couriers SET EmployeeID = 2098 WHERE CourierID = 117;
UPDATE Couriers SET EmployeeID = 3175 WHERE CourierID = 118;
UPDATE Couriers SET EmployeeID = 4240 WHERE CourierID = 119;
UPDATE Couriers SET EmployeeID = 5312 WHERE CourierID = 120;

-- add locationId --
ALTER TABLE Couriers ADD COLUMN LocationID INT;
alter table couriers ADD CONSTRAINT FOREIGN KEY(LocationID ) REFERENCES Locations(LocationID );
SET SQL_SAFE_UPDATES = 0;
SET SQL_SAFE_UPDATES = 1;

-- updating receiver address --
UPDATE Couriers SET ReceiverAddress = '555 Oak St, TX' WHERE CourierID = 101;
UPDATE Couriers SET ReceiverAddress = '777 Pine St, CA' WHERE CourierID = 102;
UPDATE Couriers SET ReceiverAddress = '222 Pine St, WA' WHERE CourierID = 103;
UPDATE Couriers SET ReceiverAddress = '444 Cedar St, GA' WHERE CourierID = 104;
UPDATE Couriers SET ReceiverAddress = '666 Redwood St, OR' WHERE CourierID = 105;
UPDATE Couriers SET ReceiverAddress = '111 Cherry St, NV' WHERE CourierID = 106;
UPDATE Couriers SET ReceiverAddress = '333 Aspen St, IL' WHERE CourierID = 107;
UPDATE Couriers SET ReceiverAddress = '666 Willow St, CO' WHERE CourierID = 108;
UPDATE Couriers SET ReceiverAddress = '777 Cypress St, FL' WHERE CourierID = 109;
UPDATE Couriers SET ReceiverAddress = '999 Palm St, TX' WHERE CourierID = 110;
UPDATE Couriers SET ReceiverAddress = '111 Pinecone St, PA' WHERE CourierID = 111;
UPDATE Couriers SET ReceiverAddress = '333 Oakridge St, NY' WHERE CourierID = 112;
UPDATE Couriers SET ReceiverAddress = '555 Hickory St, WA' WHERE CourierID = 113;
UPDATE Couriers SET ReceiverAddress = '222 Beech St, IL' WHERE CourierID = 114;
UPDATE Couriers SET ReceiverAddress = '444 Fir St, GA' WHERE CourierID = 115;
UPDATE Couriers SET ReceiverAddress = '777 Fir St, NV' WHERE CourierID = 116;
UPDATE Couriers SET ReceiverAddress = '555 Redwood St, TX' WHERE CourierID = 117;
UPDATE Couriers SET ReceiverAddress = '666 Gotham St, NJ' WHERE CourierID = 118;
UPDATE Couriers SET ReceiverAddress = '111 Hero Ave, NY' WHERE CourierID = 119;
UPDATE Couriers SET ReceiverAddress = '888 Metropolis St, IL' WHERE CourierID = 120;

-- updating locationID --
UPDATE Couriers SET LocationID = 1 WHERE ReceiverAddress LIKE '%NY%';
UPDATE Couriers SET LocationID = 2 WHERE ReceiverAddress LIKE '%CA%';
UPDATE Couriers SET LocationID = 3 WHERE ReceiverAddress LIKE '%IL%';
UPDATE Couriers SET LocationID = 4 WHERE ReceiverAddress LIKE '%TX%';
UPDATE Couriers SET LocationID = 5 WHERE ReceiverAddress LIKE '%FL%';
UPDATE Couriers SET LocationID = 6 WHERE ReceiverAddress LIKE '%OR%';
UPDATE Couriers SET LocationID = 7 WHERE ReceiverAddress LIKE '%WA%';
UPDATE Couriers SET LocationID = 8 WHERE ReceiverAddress LIKE '%CO%';
UPDATE Couriers SET LocationID = 10 WHERE ReceiverAddress LIKE '%NJ%';
UPDATE Couriers SET LocationID = 11 WHERE ReceiverAddress LIKE '%NV%';
UPDATE Couriers SET LocationID = 12 WHERE ReceiverAddress LIKE '%GA%';
UPDATE Couriers SET LocationID = 13 WHERE ReceiverAddress LIKE '%PA';

