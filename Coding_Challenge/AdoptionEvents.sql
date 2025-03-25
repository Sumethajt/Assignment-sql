CREATE TABLE  AdoptionEvents (
    EventID INT PRIMARY KEY AUTO_INCREMENT,
    EventName VARCHAR(255) NOT NULL,
    EventDate DATETIME NOT NULL,
    Location VARCHAR(255) NOT NULL
);

-- Insert data into AdoptionEvents table--
INSERT INTO AdoptionEvents (EventName, EventDate, Location) VALUES
('Spring Pet Adoption Fair', '2025-04-15 09:00:00', 'New York'),
('Pawfect Adoption Day', '2025-05-10 10:30:00', 'Los Angeles'),
('Forever Home Event', '2025-06-05 14:00:00', 'Chicago'),
('Furry Friends Meet', '2025-07-20 11:00:00', 'Houston'),
('Adopt & Love Event', '2025-08-10 13:30:00', 'San Francisco'),
('Home for Paws Drive', '2025-09-05 10:00:00', 'Miami'),
('Rescue & Rehome', '2025-10-12 15:45:00', 'Dallas'),
('Loving Hearts Adoption', '2025-11-18 09:20:00', 'Seattle'),
('Pet Adoption Fiesta', '2025-12-02 16:10:00', 'Boston'),
('New Year Pet Homecoming', '2026-01-08 12:30:00', 'Denver');

select * from AdoptionEvents;

