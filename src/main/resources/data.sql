use vladprodimport_db;

INSERT INTO Users
(email,password,phone,name,surname,organization,role)
VALUES
('admin@mail.ru','12345678','+375(44)111-11-11','Andrew','admin', 'stud',0),
('client@mail.ru','12345678','+375(44)111-11-11','Artem','client', 'stud',1),
('operator@mail.ru','12345678','+375(44)111-11-11','Dima','operator', 'stud',2),
('manager@mail.ru','12345678','+375(44)111-11-11','Slava','manager', 'stud',3);

INSERT INTO Products
(name,price,weight,type,producer,hcp)
VALUES
('juice',100,2,'drink','producer',0.1);

INSERT INTO Packages
(name,amount,product_id)
VALUES
('waterPack',25,1);

insert into Orders
(user_id,date_time,comment,state)
values
(1,CURRENT_DATE(),null,'rdy');

INSERT INTO Order_items
(order_id,package_id,amount)
Values
(1,1,10),
(1,1,20),
(1,1,30);