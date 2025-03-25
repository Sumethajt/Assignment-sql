CREATE TABLE PetShelter (
    PetID INT,
    ShelterID INT,
    PRIMARY KEY (PetID, ShelterID),
    FOREIGN KEY (PetID) REFERENCES Pets(PetID) ON DELETE CASCADE,
    FOREIGN KEY (ShelterID) REFERENCES Shelters(ShelterID) ON DELETE CASCADE
);

-- Insert data into PetShelter table --
INSERT INTO PetShelter (PetID, ShelterID) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8), (9, 1), (10, 2),(11, 3), (12, 4), (13, 5), 
(14, 6), (15, 7), (16, 8), (17, 1), (18, 2), (19, 3), (20, 4); 

select * from Petshelter;