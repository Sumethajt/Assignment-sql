create database courier_management_sys;
use courier_management_sys;
CREATE TABLE Users (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    ContactNumber VARCHAR(20) NOT NULL,
    Address TEXT NOT NULL
);

-- inserting datas --

INSERT INTO Users (Name, Email, Password, ContactNumber, Address)
VALUES 
('Alice Johnson', 'alice@example.com', 'password123', '9876543210', '123 Main St, NY'),
('Bob Smith', 'bob@example.com', 'securepass', '9876543211', '456 Elm St, LA'),
('Charlie Brown', 'charlie@example.com', 'mypassword', '9876543212', '789 Oak St, TX'),
('Diana Ross', 'diana@example.com', 'diana123', '9876543213', '159 Maple St, FL'),
('Ethan Hunt', 'ethan@example.com', 'mission007', '9876543214', '753 Birch St, CA'),
('Fiona Harper', 'fiona@example.com', 'harperpass', '9876543215', '852 Walnut St, WA'),
('George Wilson', 'george@example.com', 'wilson123', '9876543216', '963 Poplar St, MI'),
('Hannah Green', 'hannah@example.com', 'greenpass', '9876543217', '741 Pine St, AZ'),
('Isaac Adams', 'isaac@example.com', 'adams2024', '9876543218', '369 Redwood St, CO'),
('Jessica Lee', 'jessica@example.com', 'lee2024', '9876543219', '147 Cedar St, NC'),
('Kevin Rogers', 'kevin@example.com', 'rogers007', '9876543220', '258 Birch St, TX'),
('Laura Bennett', 'laura@example.com', 'bennettpass', '9876543221', '369 Elm St, NV'),
('Mike Dawson', 'mike@example.com', 'dawson123', '9876543222', '951 Oak St, SC'),
('Nancy Carter', 'nancy@example.com', 'carterpass', '9876543223', '753 Maple St, WA'),
('Oliver Young', 'oliver@example.com', 'youngpass', '9876543224', '357 Pine St, FL');

select * from Users;

