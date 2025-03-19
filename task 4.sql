-- Retrieve Payments with Courier Information 
select p.paymentID, p.amount, p.paymentDate, c.courierID, c.senderName, c.receiverName from couriers c
inner join payments p on c.courierID = p.courierID;

-- Retrieve Payments with Location Information 
select p.paymentID, p.paymentDate, p.amount, l.locationID, l.locationName from locations l
inner join payments p on l.locationID = p.locationID;

-- Retrieve Payments with Courier and Location Information 
select p.paymentID, p.PaymentDate, p.amount, l.locationID, l.locationName, c.courierID, c.sendername, c.receiverName,c.status 
from payments p 
inner join couriers c on p.courierID = c.courierID
inner join locations l on p.locationID = l.locationID;

-- List all payments with courier details 
select p.paymentID, p.amount, p.paymentDate, c.courierID, c.senderName, c.receiverName from couriers c
right join payments p on c.courierID = p.courierID;

-- Total payments received for each courier 
select c.courierID,c.senderName,c.receiverName,GROUP_CONCAT(p.PaymentID ORDER BY p.PaymentID) , sum(p.amount) as tot_payment
from couriers c 
right join payments p on c.courierID = p.courierID
group by c.courierID,c.senderName,c.receivername;

-- List payments made on a specific date 
select p.paymentID,c.courierID, p.paymentdate from payments p
left join couriers c on p.courierID = c.courierID
where p.paymentDate = '2025-03-18';

-- Get Courier Information for Each Payment 
select c.courierID, c.senderName, c.receivername,c.status,c.trackingNumber, c.shipmentDate,c.deliveryDate, 
p.paymentID, p.paymentdate
from couriers c
inner join  payments p on c.courierID = p.courierID;

-- Get Payment Details with Location 
select p.paymentID, p.paymentDate, p.amount, l.locationID, l.locationName 
from payments p
left join locations l on p.locationid = l.locationid;

-- Calculating Total Payments for Each Courier 
SELECT c.CourierID, c.TrackingNumber, c.SenderName, c.ReceiverName, 
group_concat(p.paymentID order by paymentID), SUM(p.Amount) AS TotalPayments
FROM Couriers c
INNER JOIN Payments p ON c.CourierID = p.CourierID
GROUP BY c.CourierID, c.TrackingNumber, c.SenderName, c.ReceiverName
ORDER BY TotalPayments DESC;

-- List Payments Within a Date Range 
SELECT p.PaymentID, p.Amount, p.PaymentDate, 
       c.CourierID, c.TrackingNumber, c.SenderName, c.ReceiverName, 
       l.LocationID, l.LocationName
FROM Payments p
INNER JOIN Couriers c ON p.CourierID = c.CourierID
INNER JOIN Locations l ON p.LocationID = l.LocationID
WHERE p.PaymentDate BETWEEN '2025-03-01' AND '2025-03-15'
ORDER BY p.PaymentDate DESC;

/*Retrieve a list of all users and their corresponding courier records, including cases 
where there are no matches on either side */
select u.userID, u.Name, u.Email, c.courierID, c.sendername, c.receivername, c.trackingnumber,c.status 
from couriers c
left join users u on c.userID = u.userID
union 
select u.userID, u.Name, u.Email, c.courierID, c.sendername, c.receivername, c.trackingnumber,c.status 
from couriers c
right join users u on c.userID = u.userID;

/*Retrieve a list of all couriers and their corresponding services, including cases where there are no 
matches on either side */
select c.courierID, c.receivername, c.sendername, s.serviceID, s.serviceName 
from couriers c
left join courierServices s on c.serviceID = s.serviceID
union 
select c.courierID, c.receivername, c.sendername, s.serviceID, s.serviceName 
from couriers c
right join courierServices s on c.serviceID = s.serviceID;

/* Retrieve a list of all employees and their corresponding payments, including cases where there are 
no matches on either side */
select e.employeeID, e.name, e.Email, e.role,e.salary, p.paymentID, p.amount, p.paymentdate 
from employees e
right join payments p on e.EmployeeID = p.employeeID
union 
select e.employeeID, e.name, e.Email, e.role, e.salary, p.paymentID, p.amount, p.paymentdate 
from employees e
left join payments p on e.EmployeeID = p.employeeID;

-- List all users and all courier services, showing all possible combinations. 
select u.userID, s.serviceID, u.Name, u.Email,  s.serviceName, s.cost
from users u
cross join courierservices s; -- too many duplicate values so alternatively using inner join
-- ----------------------
SELECT u.UserID, u.Name, 
       s.ServiceID, s.ServiceName, s.Cost
FROM Users u
INNER JOIN Couriers c ON u.UserID = c.UserID
INNER JOIN CourierServices s ON c.ServiceID = s.ServiceID;

-- List all employees and all locations, showing all possible combinations: 
select e.EmployeeID, e.Name, e.role, e.Email, e.salary,
	   l.locationID, l.locationName
from employees e
inner join couriers c on e.employeeID = c.employeeID
inner join locations l on c.locationID = l.locationID;
-- ----------------------------------
select e.EmployeeID, e.Name, e.role, e.Email, e.salary,
	   l.locationID, l.locationName
from employees e
cross join locations l;

-- Retrieve a list of couriers and their corresponding sender information (if available) 
select c.courierID, c.senderName, 
p.packageID, p.packageName, 
u.userID, u.Email, u.contactnumber, u.address
from users u
inner join couriers c on u.userID = c.userID
inner join package p on c.packageID = p.packageID;

-- Retrieve a list of couriers and their corresponding receiver information (if available): 
SELECT CourierID, TrackingNumber, SenderName, ReceiverName, ReceiverAddress 
FROM Couriers;

-- Retrieve a list of couriers along with the courier service details (if available): 
select c.courierID, c.sendername, c.receivername, c.trackingnumber, c.status,c.shipmentdate,c.deliverydate,
s.serviceID, s.serviceName, s.cost
from couriers c
inner join courierservices s on c.serviceID = s.serviceID;

-- Retrieve a list of employees and the number of couriers assigned to each employee:
select e.employeeID, e.name, count(c.employeeID) as numOfCouriers 
from employees e
inner join couriers c on  e.employeeID = c.employeeID
group by c.employeeID
order by numOfCouriers ;

-- Retrieve a list of locations and the total payment amount received at each location: 
select l.locationID, l.locationName, 
group_concat(p.paymentID order by p.paymentID), sum(p.amount) as tot_payment, group_concat(p.paymentdate order by p.paymentdate)
from locations l 
inner join payments p on l.locationID = p.locationID
group by p.locationID
order by tot_payment desc;

-- Retrieve all couriers sent by the same sender (based on SenderName). 
select c.courierID,c.sendername,c.receivername,c.trackingnumber,c.status from couriers c
where c.sendername = 'Isaac Adams';
-- ------------------------------------
select c.sendername,group_concat(c.receivername order by c.receivername) as receiversname, 
group_concat(c.status order by c.status) as status, 
group_concat(c.courierID order by c.courierID) as courierID,count(c.courierID) as tot_couriers
from couriers c
group by c.sendername
order by tot_couriers desc;

-- List all employees who share the same role. 
select role, group_concat(employeeID order by employeeID) as employeeID, 
group_concat(name order by name) as name
from employees
group by role;

-- Retrieve all payments made for couriers sent from the same location. 
select group_concat(p.paymentID order by p.paymentID) as paymentID, 
group_concat(p.amount order by p.amount) as amount, 
group_concat(p.paymentdate order by p.paymentdate) as paymentdate, l.locationID, l.locationName
from payments p
inner join locations l on p.locationID = l.locationID
group by p.locationID;
-- --------------------------------------
select group_concat(p.paymentID order by p.paymentID) as paymentID, 
group_concat(p.amount order by p.amount) as amount, 
group_concat(p.paymentdate order by p.paymentdate) as paymentdate, c.senderaddress
from payments p
inner join couriers c on p.courierID = c.courierID
group by c.senderaddress;

-- Retrieve all couriers sent from the same location (based on SenderAddress). 
select senderaddress, group_concat(sendername order by sendername) as sendername, 
group_concat(courierID order by courierID) as courierID, group_concat(status order by status) as status
from couriers
group by senderaddress;

-- List employees and the number of couriers they have delivered: 
select e.employeeID, e.name, count(c.courierID) as numOfCouriers
from Employees e
inner join couriers c on e.employeeID = c.employeeID
group by c.employeeID
order by numOfCouriers desc;

-- Find couriers that were paid an amount greater than the cost of their respective courier services 
select p.courierID, p.paymentID, p.amount, s.cost, s.serviceID, s.servicename 
from payments p
inner join courierservices s on p.serviceID = s.serviceID
where p.amount>s.cost ; 