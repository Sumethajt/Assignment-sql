CREATE TABLE Adoptions (
    AdoptionID INT PRIMARY KEY AUTO_INCREMENT,
    PetID INT,
    AdopterID INT,
    AdoptionDate DATETIME NOT NULL,
    FOREIGN KEY (PetID) REFERENCES Pets(PetID) ON DELETE CASCADE,
    FOREIGN KEY (AdopterID) REFERENCES Participants(ParticipantID) ON DELETE CASCADE
);

-- Insert data into Adoptions table---
INSERT INTO Adoptions (PetID, AdopterID, AdoptionDate) VALUES
(3, 5, '2025-04-16 12:00:00'),
(6, 6, '2025-05-12 15:00:00'),
(10, 7, '2025-06-18 14:30:00'),
(12, 8, '2025-07-25 11:45:00'),
(15, 9, '2025-08-05 10:10:00'),
(17, 10, '2025-09-07 16:00:00'),
(18, 5, '2025-10-10 09:30:00'),
(20, 6, '2025-11-15 12:45:00'),
(9, 7, '2025-12-22 14:50:00'),
(4, 8, '2026-01-10 13:15:00');

select * from Adoptions;
