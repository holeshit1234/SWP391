drop database OnlineShoppingDTVH

go 

create database OnlineShoppingDTVH

Go

use OnlineShoppingDTVH

Go 

create table UserDetails (
UserID int not null IDENTITY(1,1) primary key,
RoleID int not null,
UserName varchar(50) unique not null,
[PassWord] varchar(50) not null,
Email varchar(50) not null unique,
FullName nvarchar(50),
Phone varchar(10)
)

insert into UserDetails (RoleID, UserName, [PassWord], Email, FullName, Phone)
values (1,'dunghh',12345,'hominhdund@gmail.com',N'Hồ Minh Dũng', 0963697057)

insert into UserDetails (RoleID, UserName, [PassWord], Email, FullName, Phone)
values (2,'dunghh1',123456,'hominhdund1@gmail.com',N'Hồ Minh Dũng 1', 0963697054)

insert into UserDetails (RoleID, UserName, [PassWord], Email, FullName, Phone)
values (3,'dunghh2',123457,'hominhdund2@gmail.com',N'Hồ Minh Dũng 2 ', 0963697053)

Go

create table [Role] (
RoleID int IDENTITY(1,1) not null primary key,
RoleName nvarchar(50)
)

insert into [Role] (RoleName)
values (N'ADMIN')

insert into [Role] (RoleName)
values (N'STAFF')

insert into [Role] (RoleName)
values (N'CUSTOMER')
GO 

alter table UserDetails
Add Constraint fk_UserDetails_Role
Foreign Key (RoleID)
References [Role] (RoleID)

go

create table [Address] (
AddressID int IDENTITY(1,1) not null primary key,
UserId int not null,
Province Nvarchar(50),
Ward Nvarchar(30),
Street Nvarchar(50),
Notice Nvarchar(50)
)

GO 

alter table [Address]
Add Constraint fk_Address_UserDetails
Foreign Key (UserId)
References UserDetails (UserId)

GO

Create table PaymentMethod(
PaymentID int IDENTITY(1,1) not null primary key,
PaymentMethod varchar(20),
Status bit
)

Go

create table [Order] (
OrderID int IDENTITY(1,1) not null primary key,
UserID int not null,
PaymentID int not null,
AddressID int not null,
[Date] date,
TotalPrice float,
Shippingfee float
)

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


Create table Cart(
CartID int IDENTITY(1,1) not null primary key,
ProductID int not null,
UserID int not null,
Quantity int
)

alter table Cart
Add Constraint fk_Cart_UserDetails
Foreign Key (UserID)
References UserDetails (UserID)

alter table Cart
Add Constraint fk_Cart_Product
Foreign Key (ProductID)
References Product (ProductID)

GO

create table OderDetail(
OrderID int not null,
ProductID int not null,
Quantity int ,
Price float
)

alter table OderDetail
Add Constraint fk_OrderDetails_Order
Foreign Key (OrderID)
References [Order] (OrderID)

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

create table Comment(
CommentID int IDENTITY(1,1) not null primary key,
UserID int not null,
ProductID int not null,
[Date] date,
[Description] nvarchar(200)
)

alter table Comment
Add Constraint fk_UserDetails_Comment
Foreign Key (UserID)
References UserDetails (UserID)

Create table Store(
StoreID int IDENTITY(1,1) not null primary key,
[Address] Nvarchar(50),
[Status] bit,
[Description] Nvarchar(200)
)

Go 

create table Product(
ProductID int IDENTITY(1,1) not null primary key,
BrandID int not null,
CategoryID int not null,
Price float,
[Status] bit
)

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

create table ProductDetails(
ProductID int not null,
StoreID int not null,
SizeID int not null,
Quantity int
)

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

create table Rate(
RateID int IDENTITY(1,1) not null primary key,
ProductID int not null,
Point int 
)

alter table Rate
Add constraint fk_Rate_Product
foreign key (ProductID)
references Product (ProductID)

GO

create table Brand(
BrandID int IDENTITY(1,1) not null primary key,
BrandName Nvarchar(20),
[Status] bit,
[Description] Nvarchar(200)
)

GO

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

alter table ProductIMG
Add constraint fk_ProductIMG_Product
foreign key (ProductID)
references Product (ProductID)