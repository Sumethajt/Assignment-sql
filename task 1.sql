-- List all customers: 
select UserID,Name from users;

 -- List all orders for a specific customer:
select CourierID, Status, TrackingNumber, Packages from couriers where ReceiverName = 'Luke Thompson';

-- List all couriers: 
select CourierID, SenderName, ReceiverName, packages from couriers;

-- List all packages for a specific order:
select CourierID, packages from couriers where courierID = 105;

-- List all deliveries for a specific courier: 
select CourierID, status, trackingNumber from Couriers where CourierID = 105;

-- List all undelivered packages: 
select CourierID, Packages, Status from couriers where status != 'Delivered';

-- List all packages that are scheduled for delivery today: 
SELECT CourierID, Packages, TrackingNumber, Status, DeliveryDate FROM Couriers 
WHERE DeliveryDate = CURDATE();

-- List all packages with a specific status: 
select CourierID, Packages, TrackingNumber, Status from couriers 
where status = 'shipped';

-- Calculate the total number of packages for each courier. 
SELECT PackageID, quantity AS TotalItems FROM Package;
SELECT PackageID, PackageName, 
       (LENGTH(PackageName) - LENGTH(REPLACE(PackageName, ',', '')) + 1) AS TotalItems
FROM Package;

-- Find the average delivery time for each courier 
SELECT AVG(DATEDIFF(DeliveryDate, ShipmentDate)) AS AvgDeliveryDays FROM Couriers;

-- List all packages with a specific weight range: 
select PackageName, weight from package where weight>5

-- Retrieve employees whose names contain 'John' --
select EmployeeID, Name, Email, ContactNumber, Role, Salary FROM Employees WHERE Name LIKE '%John%';

-- Retrieve all courier records with payments greater than $50.
select courierID from Payments where amount>50;
SELECT c.CourierID, c.senderName, c.ReceiverName, c.ShipmentDate, p.PaymentDate, p.PackageID, c.status,p.amount
FROM Couriers c
JOIN Payments p ON c.CourierID = p.CourierID
WHERE p.amount>50;
