-- donation table creation --
CREATE TABLE IF NOT EXISTS Donations (
    DonationID INT PRIMARY KEY AUTO_INCREMENT,
    DonorName VARCHAR(255) NOT NULL,
    DonationType ENUM('Cash', 'Item') NOT NULL,
    DonationAmount DECIMAL(10,2) DEFAULT NULL,
    DonationItem VARCHAR(255) DEFAULT NULL,
    DonationDate DATETIME NOT NULL
);

-- Insert data into Donations table --
INSERT INTO Donations (DonorName, DonationType, DonationAmount, DonationItem, DonationDate) VALUES
('John Doe', 'Cash', 200.00, NULL, '2025-03-01 10:00:00'),
('Jane Smith', 'Item', NULL, 'Pet Food', '2025-03-05 14:30:00'),
('Michael Johnson', 'Cash', 500.00, NULL, '2025-02-20 09:15:00'),
('Emily Davis', 'Item', NULL, 'Blankets', '2025-03-10 11:45:00'),
('Sarah Wilson', 'Cash', 300.00, NULL, '2025-01-15 13:00:00'),
('Daniel Brown', 'Cash', 450.00, NULL, '2025-04-02 16:30:00'),
('Olivia Martinez', 'Item', NULL, 'Dog Toys', '2025-02-18 09:20:00'),
('William Anderson', 'Cash', 600.00, NULL, '2025-01-25 14:10:00'),
('Sophia Thomas', 'Item', NULL, 'Cat Beds', '2025-03-12 12:40:00'),
('Liam Rodriguez', 'Cash', 350.00, NULL, '2025-04-05 08:50:00');

select * from donations;