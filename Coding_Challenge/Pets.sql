create database IF NOT EXISTS PetPals;
use petpals;

-- Pet table creation --
CREATE TABLE IF NOT EXISTS Pets (
    PetID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Age INT NOT NULL,
    Breed VARCHAR(255) NOT NULL,
    Type VARCHAR(50) NOT NULL,
    AvailableForAdoption BIT NOT NULL DEFAULT 1
);

-- Insert data into Pets table ----

INSERT INTO Pets (Name, Age, Breed, Type, AvailableForAdoption) VALUES
('Buddy', 3, 'Golden Retriever', 'Dog', 1),
('Whiskers', 2, 'Persian', 'Cat', 1),
('Charlie', 5, 'Beagle', 'Dog', 0),
('Luna', 1, 'Siberian', 'Cat', 1),
('Max', 4, 'Bulldog', 'Dog', 1),
('Bella', 6, 'Labrador', 'Dog', 0),
('Oliver', 2, 'Maine Coon', 'Cat', 1),
('Rocky', 3, 'Poodle', 'Dog', 1),
('Simba', 4, 'Bengal', 'Cat', 1),
('Daisy', 2, 'Shih Tzu', 'Dog', 1),
('Milo', 1, 'Scottish Fold', 'Cat', 1),
('Coco', 5, 'Dachshund', 'Dog', 0),
('Shadow', 7, 'Siamese', 'Cat', 1),
('Buster', 2, 'Cocker Spaniel', 'Dog', 1),
('Ginger', 3, 'Maine Coon', 'Cat', 1),
('Bailey', 6, 'Border Collie', 'Dog', 0),
('Zoe', 4, 'Ragdoll', 'Cat', 1),
('Oscar', 2, 'Husky', 'Dog', 1),
('Nala', 5, 'Birman', 'Cat', 0),
('Leo', 3, 'German Shepherd', 'Dog', 1);

