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
DOB date,
Gender nvarchar(20),
Picture nvarchar(100),
Status bit
)


go

insert into UserDetails (RoleID, UserName, [PassWord], Email, FullName, Phone, DOB, Gender,Picture, Status)
values (1,'dunghh',12345,'hominhdund@gmail.com',N'Hồ Minh Dũng', 0963697057, '2002-04-21', N'Nam',N'logo.png',1)

insert into UserDetails (RoleID, UserName, [PassWord], Email, FullName, Phone, DOB, Gender,Picture, Status)
values (2,'dunghh1',12345,'hominhdund1@gmail.com',N'Hồ Minh Dũng', 0963694557, '2002-04-22', N'Nam',N'logo.png',1)

insert into UserDetails (RoleID, UserName, [PassWord], Email, FullName, Phone, DOB, Gender,Picture, Status)
values (3,'dunghh2',12345,'hominhdund2@gmail.com',N'Hồ Minh Dũng', 0967897057, '2002-04-23', N'Nam',N'logo.png',1)

insert into UserDetails (RoleID, UserName, [PassWord], Email, FullName, Phone, DOB, Gender,Picture, Status)
values (3,'dinh',12345,'vinhtc191@gmail.com',N'Trần Công Vinh', 0909321452, '2002-04-12', N'Nam',N'logo.png',1)

insert into UserDetails (RoleID, UserName, [PassWord], Email, FullName, Phone, DOB, Gender,Picture, Status)
values (3,'thinh',12345,'hthinhnd2@gmail.com',N'Trường Thịnh', 095427057, '2003-04-23', N'Nam',N'logo.png',1)

insert into UserDetails (RoleID, UserName, [PassWord], Email, FullName, Phone, DOB, Gender,Picture, Status)
values (3,'hieu',12345,'hieu2@gmail.com',N'Hiếu', 09674327057, '2002-03-23', N'Nam',N'logo.png',1)

Go

create table [Address] (
AddressID int IDENTITY(1,1) not null primary key,
UserId int not null,
Province Nvarchar(50),
Ward Nvarchar(30),
Street Nvarchar(50),
District Nvarchar(50),
[Status] bit
)


insert into [Address] (UserId, Province, Ward, Street, District,[Status])
values (3, N'Bình Dương', N'bình thạnh', N'3 quang trung', N'binh hoà',1),
	   (4, N'Dong thap', N'quan 9', N'5 le van viet', N'binh hoà',1)
go

Create table PaymentMethod(
PaymentID int IDENTITY(1,1) not null primary key,
PaymentMethod varchar(50),
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
Shippingfee float,
ApprovalStatusID int,
PaymentStatus bit,
point int
)
go






Create table ApprovalStatus(
ApprovalStatusID int primary key,
ApprovalStatus nvarchar(50)
)


go

Create table Cart(
CartID int IDENTITY(1,1) not null primary key,
ProductID int not null,
SizeID int,
StoreID int,
UserID int not null,
Quantity int,
Status bit,
Price float
)

go

create table Product(
ProductID int IDENTITY(1,1) not null primary key,
ProductName nvarchar(200),
BrandID int not null,
CategoryID int not null,
Price float,
[Status] bit,
[Description] nvarchar(800),
[Image] varchar(300)
)

go

create table OrderDetail(
OrderDetailID int IDENTITY(1,1) not null primary key,
OrderID int not null,
ProductID int not null,
SizeID int,
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



create table Comment(
CommentID int IDENTITY(1,1) not null primary key,
UserID int not null,
ProductID int not null,
[Date] date,
[Description] nvarchar(200),
Point int,
[Status] bit
)

go

create table Report(
ReportID int IDENTITY(1,1) not null primary key,
UserID int,
CommentID int,
[Date] date,
[Description] nvarchar(200)
)

go

Create table Store(
StoreID int IDENTITY(1,1) not null primary key,
[Address] Nvarchar(50),
[Status] bit,
[Description] Nvarchar(200)
)

go

alter table [Order]
Add Constraint fk_Order_Approval
Foreign key (ApprovalStatusID)
references [ApprovalStatus] (ApprovalStatusID)

go

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

go




go

alter table [Order]
Add Constraint fk_Order_Address
Foreign Key (AddressID)
References [Address] (AddressID)

go



go

alter table [Order]
Add Constraint fk_Order_Payment
Foreign Key (PaymentID)
References PaymentMethod (PaymentID)

go




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
go

alter table Cart
Add Constraint fk_Cart_Size
Foreign Key (SizeID)
References Size (SizeID)
go

alter table Cart
Add Constraint fk_Cart_Store
Foreign Key (StoreID)
References Store (StoreID)

GO

alter table OrderDetail
Add Constraint fk_OrderDetails_Order
Foreign Key (OrderID)
References [Order] (OrderID)

go




alter table OrderDetail
Add Constraint fk_OrderDetails_Product
Foreign Key (ProductID)
References Product (ProductID)

go





create table Category(
CategoryID int IDENTITY(1,1) not null primary key,
CategoryName nvarchar(100),
Gender nvarchar(50),
[Description] nvarchar(200),
[Status] bit

)

Go

alter table Comment
Add Constraint fk_UserDetails_Comment
Foreign Key (UserID)
References UserDetails (UserID)

go

alter table Report
Add Constraint fk_Report_UserDetails
Foreign Key (UserID)
References UserDetails (UserID)

go

alter table Report
Add Constraint fk_Report_Comment
Foreign Key (CommentID)
References Comment (CommentID)

Go 

alter table Comment
Add Constraint fk_Comment_Product
Foreign Key (ProductID)
References Product (ProductID)

go

alter table  Product
Add Constraint fk_Product_Brand
Foreign Key (BrandID)
References Brand (BrandID)

go

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
	('L' )

go


INSERT INTO Store
    ( Address,Description,Status)
VALUES
    (N'54 Điện Biên Phủ','Tổng kho',1 ),
    (N'222 Duong Ba Trac, P1, Quận 9','Chi nhanh 1',1 ),
	(N'123 Nguyễn Xuyển, P.PLA, Quận 9','Chi nhanh 2',1 ),
	(N'543 Lê Văn Việt, P.TNP, Quận 9','Chi nhanh 3',1 )
go


INSERT INTO Category
    ( CategoryName,Gender,[Description],[Status])
VALUES 
    (N'Quần ngắn',N'Nam',N'Tạm thời không có mô tả' ,1),
	 (N'Quần dài',N'Nam',N'Tạm thời không có mô tả',1 ),
	  (N'Váy',N'Nữ',N'Tạm thời không có mô tả',1 ),
	   (N'Đầm',N'Nữ',N'Tạm thời không có mô tả' ,1),
	   (N'Áo tay dài',N'Nam',N'Tạm thời không có mô tả',1 ),
	   (N'Áo tay ngắn',N'Nam',N'Tạm thời không có mô tả' ,1),
	   (N'Áo tay dài',N'Nữ',N'Tạm thời không có mô tả',1 ),
	   (N'Áo tay ngắn',N'Nữ',N'Tạm thời không có mô tả',1 ),
	   (N'Áo sơ mi',N'Nam',N'Tạm thời không có mô tả',1 ),
	   (N'Áo khoác',N'Unisex',N'Tạm thời không có mô tả' ,1),
	   (N'Áo phông',N'Unisex',N'Tạm thời không có mô tả',1 ),
	   (N'Quần dài',N'Unisex',N'Tạm thời không có mô tả' ,1),
	   (N'Quần ngắn',N'Unisex',N'Tạm thời không có mô tả',1 ),
	   (N'Nón',N'Unisex',N'Tạm thời không có mô tả' ,1),
	   (N'Giày',N'Unisex',N'Tạm thời không có mô tả',1 ),
	   (N'Dép',N'Unisex',N'Tạm thời không có mô tả',1 ),
	   (N'Tất',N'Unisex',N'Tạm thời không có mô tả',1 ),
	   (N'Túi',N'Nữ',N'Tạm thời không có mô tả' ,1)


go


INSERT INTO Product
    (BrandID,CategoryID,Price,[Status],ProductName,[Description],[Image])
VALUES
    (3,11, 100000,1,N'Áo thun coton',N'Chiếc áo thun là sản phẩm thời trang đơn giản nhưng vô cùng phổ biến. Với chất liệu vải cotton mềm mại và thoáng khí, chiếc áo thun này cực kỳ thoải mái khi mặc. Thiết kế đơn giản với cổ tròn và tay ngắn giúp chiếc áo này trở nên tiện dụng hơn.' 
	,'aothungau.jpg'),
	(2,12, 200000,1,N'Quần bông',N'Chiếc quần bông là sản phẩm thời trang phổ biến cho cả nam và nữ. Với chất liệu vải cotton mềm mại và thoải mái, chiếc quần bông này dễ dàng kết hợp với nhiều kiểu áo khác nhau. Thiết kế đơn giản với túi hai bên giúp chiếc quần này trở nên tiện dụng hơn.' 
	,'setbong.jpg'),
	(2,14, 50000,1,N'Nón lưỡi trai',N'Chiếc nón lưỡi trai là sản phẩm thời trang phổ biến cho cả nam và nữ. Với thiết kế đơn giản và màu sắc đa dạng, chiếc nón này phù hợp với nhiều phong cách khác nhau. Chất liệu vải bền bỉ và đội nón cực kỳ thoải mái.' 
	,'nonluoitrai.jpg'),
	(1,15, 1000000,1,N'Giày da cao cấp',N'Chiếc giày da là sản phẩm thời trang đẳng cấp cho cả nam và nữ. Với chất liệu da cao cấp và màu sắc đa dạng, chiếc giày này phù hợp với nhiều phong cách khác nhau. Thiết kế đơn giản hoặc phức tạp tùy thuộc vào sở thích và hoàn cảnh sử dụng. Đế giày chắc chắn và êm ái giúp chiếc giày này mang lại sự thoải mái khi di chuyển.' 
	,'dayda.jpg'),
	(1,16, 40000,1,N'Dép cao su',N'Chiếc dép là sản phẩm thời trang phổ biến trong mùa hè. Với thiết kế đơn giản và màu sắc đa dạng, chiếc dép này phù hợp với nhiều phong cách khác nhau. Chất liệu vải bền bỉ và đế dép êm ái giúp chiếc dép này mang lại sự thoải mái khi mặc.' 
	,'dep.jpg'),
	(3,17, 35000,1,N'Tất',N'Chiếc tất là sản phẩm thời trang nhỏ nhưng cực kỳ quan trọng trong việc tạo ra phong cách của bạn. Với chất liệu cotton thoáng khí và màu sắc đa dạng, chiếc tất này giúp bạn thoải mái khi mặc giày và kết hợp với nhiều loại giày và trang phục khác nhau.'
	,'tat.jpg'),
	(2,11, 150000,1,N'Áo Phông',N'Chiếc áo phông là một trong những sản phẩm thời trang phổ biến nhất trên thế giới. Với thiết kế đơn giản, kiểu dáng thoải mái và màu sắc đa dạng, áo phông phù hợp với nhiều phong cách và hoàn cảnh khác nhau. Với chất liệu vải cotton thấm hút mồ hôi và mềm mại, chiếc áo này cực kỳ thoải mái khi mặc.'
	,'aophong.jpg'),
	(1,10, 350000,1,N'Hoodie siu xịn',N'Chiếc áo hoodie unisex này là sự kết hợp giữa phong cách thể thao và đường phố. Với chất liệu vải nhẹ và đàn hồi, chiếc áo này mang lại sự thoải mái'
	,'hutdi.jpg'),
	(3,2, 200000,1,N'Quần jean',N'Chiếc quần jean là một trong những sản phẩm thời trang không thể thiếu trong tủ đồ của bất kỳ ai. Với chất liệu denim bền bỉ và màu sắc đa dạng, chiếc quần jean này phù hợp với nhiều phong cách và hoàn cảnh khác nhau. Thiết kế đơn giản với túi hai bên và túi sau giúp chiếc quần này trở nên tiện dụng hơn.'
	,'jean.jpg'),
	(1,15, 1350000,1,N'Giày sneaker',N'Chiếc giày sneaker là sự kết hợp tuyệt vời giữa phong cách và thoải mái. Với thiết kế đơn giản và màu sắc đa dạng, chiếc giày này phù hợp với nhiều phong cách và hoàn cảnh khác nhau. Chất liệu vải bền bỉ và đế giày đàn hồi giúp chiếc giày này mang lại sự thoải mái khi mặc.'
	,'giay.jpg'),
	(2,4, 430000,1,N'Váy đầm',N'Chiếc váy đầm là sản phẩm thời trang dành cho phụ nữ, với thiết kế đa dạng và màu sắc đa dạng. Với chất liệu vải mềm mại và thoáng khí, chiếc váy này mang lại sự thoải mái và gợi cảm cho người mặc. Thiết kế đơn giản hoặc phức tạp tùy thuộc vào sở thích và hoàn cảnh sử dụng.'
	,'vay.jpg'),
	(2,18, 160000,1,N'Túi xách nữ',N'Chiếc túi xách nữ này là một sự kết hợp tuyệt vời giữa phong cách cổ điển và hiện đại. Với chất liệu da mềm mại và màu sắc tươi sáng, chiếc túi này có thể dễ dàng kết hợp với nhiều trang phục khác nhau. Thiết kế gọn nhẹ và tiện lợi, túi xách này là một lựa chọn tuyệt vời cho những người phụ nữ đang tìm kiếm một chiếc túi đa năng.'
	,'tuixach.jpg'),
	(3,10, 790000,1,N'Áo khoác nam',N'Chiếc áo khoác nam này với kiểu dáng thời trang và màu sắc trung tính là sự lựa chọn hoàn hảo cho những ngày se lạnh. Với chất liệu vải bền bỉ và lớp lót ấm áp, chiếc áo khoác này giữ ấm cho người mặc trong mùa đông. Thiết kế cổ áo bẻ và túi hai bên tạo nên sự thoải mái và tiện dụng cho người mặc.'
	,'Ao.jpg'),
	(2,3, 430000,1,N'Váy xòe',N'Váy xòe là sản phẩm thời trang dành cho phái đẹp, với đường xòe rộng và vòng eo nhỏ giúp làm nổi bật vóc dáng. Váy xòe có nhiều kiểu dáng và màu sắc khác nhau, thích hợp để mặc trong các dịp đi chơi, tiệc tùng hay đi làm.'
	,'vaychan.jpg'),
	(2,9, 740000,1,N'Áo sơ mi nam',N'Áo sơ mi nam là sản phẩm thời trang không thể thiếu trong tủ quần áo của phái mạnh. Thiết kế cổ tròn hoặc cổ áo, tay dài hoặc tay ngắn, chất liệu vải cotton hoặc lụa, áo sơ mi nam có thể kết hợp với quần tây, quần jeans hay chinos để tạo nên nhiều phong cách khác nhau.'
	,'aosomi.jpg'),
	(3,10, 230000,1,N'Áo khoác bomber',N'Áo khoác bomber là sản phẩm thời trang đang được ưa chuộng trong những năm gần đây. Thiết kế dáng ôm, tay dài, khóa kéo, chất liệu vải nỉ hoặc da cao cấp giúp áo trở nên sang trọng và đẳng cấp. Áo khoác bomber thích hợp để mặc vào mùa đông, đi du lịch hay đi chơi.'
	,'aogia.jpg'),
	(1,3, 7560000,1,N'Váy midi',N'Váy midi là một sản phẩm thời trang dành cho phái đẹp, có chiều dài từ gối đến chân giúp che đi nhược điểm về chân. Váy midi có nhiều kiểu dáng khác nhau như xòe, ôm, suông, với chất liệu vải thô, lụa hoặc ren. Váy midi thích hợp để mặc trong các dịp đi tiệc, đi làm hoặc đi chơi.'
	,'vaychan.jpg'),
	(1,10, 830000,1,N'Áo khoác gió',N'Áo khoác gió là một sản phẩm thời trang dành cho cả nam và nữ, có chất liệu vải thun hoặc nylon, với khóa kéo và túi hai bên. Áo khoác gió thường có màu sắc tươi sáng, thiết kế dáng rộng thoải mái, thích hợp để mặc vào mùa xuân hoặc mùa thu.'
	,'aogia.jpg'),
	(3,15, 7530000,1,N'Giày cao gót',N'Giày cao gót là một sản phẩm thời trang dành cho phái đẹp, giúp làm tôn lên vóc dáng và phong cách. Có nhiều kiểu dáng khác nhau như mũi nhọn, mũi tròn, quai ngang hay quai chéo, với chất liệu da, nhung hoặc nubuck. Giày cao gót thích hợp để mang đi tiệc, đi làm hoặc đi chơi.'
	,'giaycaogot.jpg'),
	(2,5, 45000,1,N'Áo len nam',N'Áo len nam là một sản phẩm thời trang dành cho phái mạnh, có thiết kế dáng ôm hoặc suông, với chất liệu len mềm mại và ấm áp. Áo len nam thường có màu sắc đơn giản như đen, trắng, xám, nâu hoặc xanh, thích hợp để mặc vào mùa đông, đi làm hoặc đi chơi.'
	,'aolen.jpg'),
	(3,18, 7770000,1,N'Túi xách nữ',N'Túi xách nữ là một sản phẩm thời trang không thể thiếu trong bộ sưu tập của phái đẹp. Có nhiều kiểu dáng và kích thước khác nhau, từ túi xách nhỏ để đi dạo phố đến túi xách lớn để đi làm. Chất liệu của túi xách cũng đa dạng, từ da, vải thô, canvas, denim đến nhựa PVC. Túi xách nữ thường có màu sắc tươi sáng và thiết kế đẹp mắt, thích hợp để mang đi làm, đi chơi hay dạo phố.'
	,'tuixach.jpg')


go





go

INSERT INTO ProductDetails
    ( ProductID,StoreID,SizeID,Quantity)
VALUES
    (1,1,1,3 ),
	(1,1,2,4 ),
	(1,1,3,0 ),
	(2,1,1,0 ),
	(2,1,2,1 ),
	(2,1,3,9 ),
	(3,1,1,7 ),
	(3,1,2,6 ),
	(3,1,3,2 ),
	(4,1,1,5 ),
	(4,1,2,2 ),
	(4,1,3,1 ),
	(5,1,1,2 ),
	(5,1,2,4 ),
	(5,1,3,5 ),
	(7,1,1,7 ),
	(7,1,2,6 ),
	(7,1,3,1 ),
	(8,1,1,9 )


go

insert into [Comment] ([UserID],[ProductID],[Date],[Description] ,[Point],[Status])
values
	(3,3,'2021-07-12',N'quá đẹp luôn',5,1),
	(4,1,'2021-05-14',N'quá xấu luôn',1,1),
	(5,5,'2022-06-02',N'cũng ok á',4,1),
	(6,3,'2022-06-17',N'tạm ',3,1),
	(3,4,'2023-01-12',N'10 điểm',5,1),
	(4,5,'2023-01-13',N'quá ok',5,1),
	(5,3,'2023-02-13',N'cũng ok',4,1),
	(6,1,'2023-01-13',N'xấu quá chời',2,1),
	(4,4,'2023-02-23',N'lần này thì cũng tạm',3,1),
	(5,2,'2022-06-02',N'cũng ok ....',2,1),
	(6,6,'2022-06-02',N'quá tốt luôn',5,1),
	(4,5,'2022-06-02',N'không tốt',1,1)

	go

insert into [PaymentMethod] ([PaymentMethod],[Status])
values ('Cash on delivery (COD)',1),
		('Paypal Payment',0)

		go

		insert into [ApprovalStatus] (ApprovalStatusID,ApprovalStatus)
		values(1,N'Chờ xác nhận'),
		(2,N'Đã xác nhận'),
		(3,N'Đã giao hàng'),
		(4,N'Đã hủy')
