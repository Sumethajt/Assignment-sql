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

delete from locations where locationid = 15;
delete from locations where locationid = 14;
-- updating LocationID with respect to Location --
UPDATE Locations 
SET 
    Address = CASE 
        WHEN LocationID = 1  THEN '123 Warehouse St, NY'
        WHEN LocationID = 2  THEN '789 Logistics Rd, CA'
        WHEN LocationID = 3  THEN '555 Industrial Ave, IL'
        WHEN LocationID = 4  THEN '321 Cargo St, TX'
        WHEN LocationID = 5  THEN '101 Ocean Blvd, FL'
        WHEN LocationID = 6  THEN '666 Redwood St, OR'
        WHEN LocationID = 7  THEN '222 Pine Ave, WA'
        WHEN LocationID = 8  THEN '333 Mountain Rd, CO'
        WHEN LocationID = 10 THEN '444 Highway St, NJ'
        WHEN LocationID = 11 THEN '888 Vegas Blvd, NV'
        WHEN LocationID = 12 THEN '777 Peachtree St, GA'
        WHEN LocationID = 13 THEN '1000 Liberty St, PA'
    END,
    
    LocationName = CASE 
        WHEN LocationID = 1  THEN 'New York'
        WHEN LocationID = 2  THEN 'California'
        WHEN LocationID = 3  THEN 'Illinois'
        WHEN LocationID = 4  THEN 'Texas'
        WHEN LocationID = 5  THEN 'Florida'
        WHEN LocationID = 6  THEN 'Oregon'
        WHEN LocationID = 7  THEN 'Washington'
        WHEN LocationID = 8  THEN 'Colorado'
        WHEN LocationID = 10 THEN 'New Jersey'
        WHEN LocationID = 11 THEN 'Nevada'
        WHEN LocationID = 12 THEN 'Georgia'
        WHEN LocationID = 13 THEN 'Pennsylvania'
    END
WHERE LocationID IN (1,2,3,4,5,6,7,8,10,11,12,13);

select * from locations;
