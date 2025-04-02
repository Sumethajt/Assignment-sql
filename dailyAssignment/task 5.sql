-- Find couriers that have a weight greater than the average weight of all couriers 
select c.courierID, c.sendername,c.receivername,c.status,p.weight,p.packageID 
from couriers c
inner join package p on c.packageID = p.packageID
where p.weight is not null
and p.weight > (select avg(weight) from package where weight is not null);

-- Find the names of all employees who have a salary greater than the average salary: 
select employeeID, name, email, role, salary from employees
where salary > (select avg(salary) from employees);

-- Find the total cost of all courier services where the cost is less than the maximum cost 
select sum(cost) as totalcost,(select max(cost) from courierservices) from courierservices
where cost < (select max(cost) from courierservices);

-- Find all couriers that have been paid for 
select c.courierID, c.sendername, c.receivername, c.deliverydate, c.status, p.paymentID, p.Amount, p.PaymentDate
FROM Couriers c
INNER JOIN Payments p ON c.CourierID = p.CourierID
where p.paymentDate <= curdate()
ORDER BY p.PaymentDate DESC;
-- -----------------------
SELECT CourierID, TrackingNumber, SenderName, ReceiverName
FROM Couriers
WHERE CourierID IN (
    SELECT CourierID 
    FROM Payments 
    WHERE PaymentDate <= CURDATE()
);

-- Find the locations where the maximum payment amount was made 
select l.locationID, l.locationname,p.paymentID,p.amount 
from locations l
inner join payments p on l.locationid = p.locationid
where p.amount = (select max(amount) from payments);

/* Find all couriers whose weight is greater than the weight of all couriers sent by a specific sender 
(e.g., 'SenderName'):*/
select c1.courierID, c1.sendername, c1.status, c1.deliverydate,p1.weight,p1.packageID 
from couriers c1
inner join package p1 on c1.packageID = p1.packageID 
where  p1.weight > (select max(p2.weight) 
					from package p2
                    inner join couriers c2 on c2.packageID = p2.packageID 
                    where c2.sendername = 'Diana ross');
