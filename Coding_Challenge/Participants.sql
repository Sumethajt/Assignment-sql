CREATE TABLE Participants (
    ParticipantID INT PRIMARY KEY AUTO_INCREMENT,
    ParticipantName VARCHAR(255) NOT NULL,
    ParticipantType ENUM('Shelter', 'Adopter') NOT NULL,
    EventID INT,
    FOREIGN KEY (EventID) REFERENCES AdoptionEvents(EventID) ON DELETE SET NULL
);

-- Insert data into Participants table---
INSERT INTO Participants (ParticipantName, ParticipantType, EventID) VALUES
('Happy Paws Shelter', 'Shelter', 1),
('Furry Friends Rescue', 'Shelter', 2),
('Purrfect Haven', 'Shelter', 3),
('Safe Haven Shelter', 'Shelter', 4),
('Emily Johnson', 'Adopter', 1),
('Mark Thompson', 'Adopter', 2),
('Anna Lee', 'Adopter', 3),
('Kevin Wright', 'Adopter', 5),
('Sarah Green', 'Adopter', 7),
('James Carter', 'Adopter', 9);

select * from participants;
