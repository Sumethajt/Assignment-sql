-- Shelters table creation --
CREATE TABLE IF NOT EXISTS Shelters (
    ShelterID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Location VARCHAR(255) NOT NULL
);

-- Insert data into Shelters table---
INSERT INTO Shelters (ShelterID, Name, Location) VALUES
(1,'Happy Paws Shelter', 'New York'),
(2,'Furry Friends Rescue', 'Los Angeles'),
(3,'Purrfect Haven', 'Chicago'),
(4,'Safe Haven Shelter', 'Houston'),
(5,'Home for Paws', 'San Francisco'),
(6,'Golden Hearts Shelter', 'Miami'),
(7,'Loving Tails Rescue', 'Dallas'),
(8,'Hopeful Companions', 'Seattle');

select * from shelters;
DELETE FROM Shelters;
