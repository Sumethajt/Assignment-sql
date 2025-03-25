-- task 5 
SELECT Name, Age, Breed, Type 
FROM Pets 
WHERE AvailableForAdoption = 1;

-- task 6 --
SELECT p.ParticipantName, p.ParticipantType 
FROM Participants p
JOIN AdoptionEvents e ON p.EventID = e.EventID
WHERE e.EventID = 1; 

-- task 7--


-- Task 8
SELECT s.Name AS ShelterName, COALESCE(SUM(d.DonationAmount), 0) AS TotalDonation
FROM Shelters s
LEFT JOIN Donations d ON s.ShelterID = d.DonationID
GROUP BY s.ShelterID, s.Name;

-- Task 9
SELECT Name, Age, Breed, Type 
FROM Pets 
WHERE PetID NOT IN (SELECT PetID FROM Adoptions);

-- Task 10
SELECT DATE_FORMAT(DonationDate, '%Y-%M') AS MonthYear,  
       SUM(DonationAmount) AS TotalDonation  
FROM Donations  
GROUP BY MonthYear  
ORDER BY MIN(DonationDate);

-- Task 11
SELECT DISTINCT Breed 
FROM Pets 
WHERE Age BETWEEN 1 AND 3 OR Age > 5;

-- Task 12
SELECT p.Name AS PetName, s.Name AS ShelterName
FROM Pets p
JOIN PetShelter ps ON p.PetID = ps.PetID
JOIN Shelters s ON ps.ShelterID = s.ShelterID
WHERE p.AvailableForAdoption = 1;

-- Task 13
SELECT COUNT(*) AS TotalParticipants
FROM Participants p
JOIN AdoptionEvents e ON p.EventID = e.EventID
JOIN Shelters s ON e.Location = s.Location
WHERE s.Location = 'Chennai';

-- Task 14
SELECT DISTINCT TRIM(LOWER(Breed)) AS Breed  
FROM Pets  
WHERE Age BETWEEN 1 AND 5;

-- Task 15
SELECT Name, Age, Breed, Type 
FROM Pets 
WHERE PetID NOT IN (SELECT PetID FROM Adoptions);

-- Task 16
SELECT p.Name AS PetName, pr.ParticipantName AS AdopterName
FROM Adoptions a
JOIN Pets p ON a.PetID = p.PetID
JOIN Participants pr ON a.AdopterID = pr.ParticipantID;

-- Task 17
SELECT s.Name AS ShelterName, COUNT(p.PetID) AS AvailablePets
FROM Shelters s
LEFT JOIN PetShelter ps ON s.ShelterID = ps.ShelterID
LEFT JOIN Pets p ON ps.PetID = p.PetID AND p.AvailableForAdoption = 1
GROUP BY s.ShelterID, s.Name;

-- Task 18
SELECT p1.Name AS Pet1, p2.Name AS Pet2, s.Name AS ShelterName, p1.Breed
FROM Pets p1
JOIN Pets p2 ON p1.Breed = p2.Breed AND p1.PetID < p2.PetID
JOIN PetShelter ps1 ON p1.PetID = ps1.PetID
JOIN PetShelter ps2 ON p2.PetID = ps2.PetID AND ps1.ShelterID = ps2.ShelterID
JOIN Shelters s ON ps1.ShelterID = s.ShelterID;

-- Task 19
SELECT s.Name AS ShelterName, e.EventName
FROM Shelters s
CROSS JOIN AdoptionEvents e;

-- Task 20
SELECT s.Name AS ShelterName, COUNT(a.AdoptionID) AS TotalAdoptions
FROM Shelters s
JOIN PetShelter ps ON s.ShelterID = ps.ShelterID
JOIN Adoptions a ON ps.PetID = a.PetID
GROUP BY s.ShelterID, s.Name
ORDER BY TotalAdoptions DESC
LIMIT 1;
