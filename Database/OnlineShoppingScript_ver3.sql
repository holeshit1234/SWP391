USE master
GO
if exists (select * from sysdatabases where name='OnlineShoppingDTVH')
		drop database OnlineShoppingDTVH

go 

create database OnlineShoppingDTVH
Go

use OnlineShoppingDTVH

Go 

create table [Role] (
RoleID int IDENTITY(1,1) not null primary key,
RoleName nvarchar(50)
)

go

insert into [Role] (RoleName)
values (N'ADMIN')

insert into [Role] (RoleName)
values (N'STAFF')

insert into [Role] (RoleName)
values (N'CUSTOMER')

GO 
create table UserDetails (
UserID int not null IDENTITY(1,1) primary key,
RoleID int not null,
UserName varchar(50) unique not null,
[PassWord] varchar(50) ,
Email varchar(50) not null unique,
FullName nvarchar(50),
Phone varchar(15),
Status bit
)



alter table UserDetails 
ADD DOB date

alter table UserDetails 
ADD Gender nvarchar(50)

go

insert into UserDetails (RoleID, UserName, [PassWord], Email, FullName, Phone, DOB, Gender)
values (1,'dunghh',12345,'hominhdund@gmail.com',N'Hồ Minh Dũng', 0963697057, '2002-04-21', N'Nam')

insert into UserDetails (RoleID, UserName, [PassWord], Email, FullName, Phone, DOB, Gender)
values (2,'dunghh1',12345,'hominhdund1@gmail.com',N'Hồ Minh Dũng', 0963694557, '2002-04-22', N'Nam')

insert into UserDetails (RoleID, UserName, [PassWord], Email, FullName, Phone, DOB, Gender)
values (3,'dunghh2',12345,'hominhdund2@gmail.com',N'Hồ Minh Dũng', 0967897057, '2002-04-23', N'Nam')


Go

create table [Address] (
AddressID int IDENTITY(1,1) not null primary key,
UserId int not null,
Province Nvarchar(50),
Ward Nvarchar(30),
Street Nvarchar(50),
Notice Nvarchar(50)
)

alter table [Address]
Add District Nvarchar(50)


insert into [Address] (UserId, Province, Ward, Street, Notice, District)
values (3, N'Bình Dương', N'bình thạnh', N'3 quang trung', 'non', N'binh hoà')
go

Create table PaymentMethod(
PaymentID int IDENTITY(1,1) not null primary key,
PaymentMethod varchar(20),
Status bit
)

go



create table [Order] (
OrderID int IDENTITY(1,1) not null primary key,
UserID int not null,
PaymentID int not null,
AddressID int not null,
[Date] date,
TotalPrice float,
Shippingfee float
)

go

Create table Cart(
CartID int IDENTITY(1,1) not null primary key,
ProductID int not null,
UserID int not null,
Quantity int
)

go

create table Product(
ProductID int IDENTITY(1,1) not null primary key,
ProductName nvarchar(200),
BrandID int not null,
CategoryID int not null,
Price float,
[Status] bit
)

go

create table OderDetail(
OrderID int not null,
ProductID int not null,
Quantity int ,
Price float
)

go

create table Brand(
BrandID int IDENTITY(1,1) not null primary key,
BrandName Nvarchar(20),
[Status] bit,
[Description] Nvarchar(200)
)

 go

 create table ProductDetails(
ProductID int not null,
StoreID int not null,
SizeID int not null,
Quantity int
)



go

create table Size(
SizeID int IDENTITY(1,1) not null primary key,
NameSize varchar(10)
)

GO

create table ProductIMG(
ImageID int IDENTITY(1,1)not null primary key,
ProductID int not null,
[Image] varchar(200)
)

go

create table Comment(
CommentID int IDENTITY(1,1) not null primary key,
UserID int not null,
ProductID int not null,
[Date] date,
[Description] nvarchar(200),
Point int
)

go

Create table Store(
StoreID int IDENTITY(1,1) not null primary key,
[Address] Nvarchar(50),
[Status] bit,
[Description] Nvarchar(200)
)



alter table UserDetails
Add Constraint fk_UserDetails_Role
Foreign Key (RoleID)
References [Role] (RoleID)

go

alter table [Address]
Add Constraint fk_Address_UserDetails
Foreign Key (UserId)
References UserDetails (UserID)

GO

alter table [Order]
Add Constraint fk_Order_UserDetails
Foreign Key (UserId)
References UserDetails (UserId)

alter table [Order]
Add Constraint fk_Order_Address
Foreign Key (AddressID)
References [Address] (AddressID)

alter table [Order]
Add Constraint fk_Order_Payment
Foreign Key (PaymentID)
References PaymentMethod (PaymentID)

go 

alter table Cart
Add Constraint fk_Cart_UserDetails
Foreign Key (UserID)
References UserDetails (UserID)

go

alter table Cart
Add Constraint fk_Cart_Product
Foreign Key (ProductID)
References Product (ProductID)

GO

alter table OderDetail
Add Constraint fk_OrderDetails_Order
Foreign Key (OrderID)
References [Order] (OrderID)

go

alter table OderDetail
Add Constraint fk_OrderDetails_Product
Foreign Key (ProductID)
References Product (ProductID)

GO

create table Category(
CategoryID int IDENTITY(1,1) not null primary key,
CategoryName nvarchar(20),
Gender bit,
[Description] nvarchar(200)
)

Go

alter table Comment
Add Constraint fk_UserDetails_Comment
Foreign Key (UserID)
References UserDetails (UserID)

Go 

alter table Comment
Add Constraint fk_Product_Comment
Foreign Key (ProductID)
References Product (ProductID)

alter table  Product
Add Constraint fk_Product_Brand
Foreign Key (BrandID)
References Brand (BrandID)

alter table Product
Add constraint fk_Product_Category
foreign key (CategoryID)
references Category (CategoryID)

Go

alter table ProductDetails
Add constraint fk_ProductDetails_Product
foreign key (ProductID)
references Product (ProductID)

alter table ProductDetails
Add constraint fk_ProductDetails_Size
foreign key (SizeID)
references Size (SizeID)

alter table ProductDetails
Add constraint fk_ProductDetails_Store
foreign key (StoreID)
references Store (StoreID)


GO

alter table ProductIMG
Add constraint fk_ProductIMG_Product
foreign key (ProductID)
references Product (ProductID)

go

alter table UserDetails 
ADD Picture varchar(100)

go

INSERT INTO Brand (BrandName ,Description ,Status )
VALUES
    ( 'LV', N'Luon vui tuoi', 1),
	( 'GUCCi', N'Guốc chì', 1),
	( 'Dior', N'Đì or', 1)

go

INSERT INTO Size
    ( NameSize)
VALUES
    ('S' ),
	('M' ),
	('L' ),
	('XL' ),
	('XXL' )

go


INSERT INTO Store
    ( Address,Description,Status)
VALUES
    ('222 Duong Ba Trac P1 Q9','Chi nhanh 1',1 )

go


INSERT INTO Category
    ( CategoryName,Gender,Description)
VALUES 
    ('Quan sot',0,'Limited' ),
	 ('Quan tay',1,'Limited' ),
	  ('Ao so mi',1,'Limited' ),
	   ('Ao thun',0,'Limited' )


go

Set IDENTITY_INSERT Product on
INSERT INTO Product
    ( ProductID,BrandID,CategoryID,Price,Status,ProductName)
VALUES
    (1,3,2, 10000,1,'Ao thun' ),
	(2,2,1, 20000,1,'Quan' ),
	(3,2,4, 50000,1,'Non' ),
	(4,1,3, 10000,1,'Giay' ),
	(5,1,2, 40000,1,'Dep' ),
	(6,3,4, 100000,1,'Tat')
Set IDENTITY_INSERT Store off

go

INSERT INTO ProductIMG
    (ProductID,Image )
VALUES
    (1,'https://bucket.nhanh.vn/05016d-25618/art/artCT/20210613_7gWT1nftpu24Ax9bSsonMzlE.jpg' ),
	(2,'https://lzd-img-global.slatic.net/g/p/d52ff5ecbbf8a8cc289984fc1a6a3072.jpg_720x720q80.jpg_.webp'),
	(3,'https://salt.tikicdn.com/ts/tmp/b8/6a/7f/9440a57a6352bce5ceb87bb28b41be57.jpg'),
	(4,'https://salt.tikicdn.com/ts/tmp/a0/d9/ee/ba84557bb9a519207c3baee99a2541c9.jpg'),
	(5,'https://cf.shopee.vn/file/582170e1c0ba7c882fc20048cc32f549'),
	(6,'https://cf.shopee.vn/file/8e4aa52894382b9685284b7c05741613')


go

INSERT INTO ProductDetails
    ( ProductID,StoreID,SizeID,Quantity)
VALUES
    (1,1,1,15 ),
	(2,1,2,10 ),
	(3,1,3,19 ),
	(4,1,2,17 ),
	(5,1,5,16 ),
	(6,1,4,12 )


go

insert into [Comment] ([UserID],[ProductID],[Date],[Description] ,[Point])
values
	(1,1,'2021-07-12',N'quá đẹp luôn',5),
	(1,2,'2021-05-14',N'quá xấu luôn',1),
	(2,1,'2022-06-02',N'cũng ok á',4),
	(2,1,'2022-06-17',N'tạm ',3),
	(2,2,'2023-01-12',N'10 điểm',5),
	(3,2,'2023-01-13',N'quá ok',5),
	(3,2,'2023-04-13',N'cũng ok',4),
	(3,1,'2023-01-13',N'xấu quá chời',2),
	(3,1,'2023-02-23',N'lần này thì cũng tạm',3)





