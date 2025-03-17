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