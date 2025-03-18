create table Package (
PackageID INT PRIMARY KEY AUTO_INCREMENT,
PackageName VARCHAR(255) NOT NULL,
Quantity INT NOT NULL
);
ALTER TABLE Package AUTO_INCREMENT = 1000;
INSERT INTO Package (PackageName, Quantity)
VALUES 
('Laptop', 1),
('Smartphone, Headphones', 2),
('Tablet, Smartwatch', 2),
('Bluetooth Speaker', 2),
('Camera, External Hard Drive', 2),
('Gaming Console, Wireless Router', 2),
('Keyboard, Mouse', 2),
('Books, Laptop Stand',2),
('Clothing, Shoes',2),
('Perfume, Handbag',2),
('Office Chair',1),
('Drone, Camera', 2),
('Smartphone, Bluetooth Speaker',3),
('Tablet, Keyboard, Mouse',3),
('Smartwatch, Wireless Router',2),
('Gaming Console, Headphones',2),
('External Hard Drive, Books',2),
('Laptop, Office Chair',2),
('Shoes, Perfume',2),
('Laptop Stand, Handbag, Smartwatch',3);

select * from package;

ALTER TABLE Package ADD COLUMN Weight DECIMAL(5,2) NOT NULL;

UPDATE Package SET Weight = 2.5 WHERE PackageID = 1000;
UPDATE Package SET Weight = 0.5 WHERE PackageID = 1001;
UPDATE Package SET Weight = 1.2 WHERE PackageID = 1002;
UPDATE Package SET Weight = 0.7 WHERE PackageID = 1003;
UPDATE Package SET Weight = 3.0 WHERE PackageID = 1004;
UPDATE Package SET Weight = 4.8 WHERE PackageID = 1005;
UPDATE Package SET Weight = 1.0 WHERE PackageID = 1006;
UPDATE Package SET Weight = 5.2 WHERE PackageID = 1007;
UPDATE Package SET Weight = 3.9 WHERE PackageID = 1008;
UPDATE Package SET Weight = 2.8 WHERE PackageID = 1009;
UPDATE Package SET Weight = 1.5 WHERE PackageID = 1010;
UPDATE Package SET Weight = 0.9 WHERE PackageID = 1011;
UPDATE Package SET Weight = 6.3 WHERE PackageID = 1012;
UPDATE Package SET Weight = 7.1 WHERE PackageID = 1013;
UPDATE Package SET Weight = 2.0 WHERE PackageID = 1014;
UPDATE Package SET Weight = 5.6 WHERE PackageID = 1015;
UPDATE Package SET Weight = 8.2 WHERE PackageID = 1016;
UPDATE Package SET Weight = 4.7 WHERE PackageID = 1017;
UPDATE Package SET Weight = 3.3 WHERE PackageID = 1018;
UPDATE Package SET Weight = 6.9 WHERE PackageID = 1019;

