CREATE TABLE Locations (
    LocationID INT PRIMARY KEY,
    LocationName VARCHAR(100) NOT NULL,
    Address TEXT NOT NULL
);
desc locations;

-- inserting datas --

INSERT INTO Locations (LocationID, LocationName, Address)
VALUES 
(1, 'New York Hub', '123 Warehouse St, NY'),
(2, 'Los Angeles Hub', '789 Logistics Rd, LA'),
(3, 'Chicago Distribution Center', '555 Industrial Ave, IL'),
(4, 'Houston Shipping Yard', '321 Cargo St, TX'),
(5, 'San Francisco Port', '888 Dockside Rd, CA'),
(6, 'Miami Transit Hub', '101 Ocean Blvd, FL'),
(7, 'Seattle Logistic Center', '222 Pine Ave, WA'),
(8, 'Denver Cargo Depot', '333 Mountain Rd, CO'),
(9, 'Dallas Freight Hub', '444 Highway St, TX'),
(10, 'Phoenix Shipping Center', '555 Desert Rd, AZ'),
(11, 'Boston Shipping Terminal', '666 Atlantic Ave, MA'),
(12, 'Atlanta Storage Hub', '777 Peachtree St, GA'),
(13, 'Las Vegas Transit Station', '888 Vegas Blvd, NV'),
(14, 'Detroit Industrial Warehouse', '999 Motor City Dr, MI'),
(15, 'Philadelphia Cargo Hub', '1000 Liberty St, PA');

select * from locations;