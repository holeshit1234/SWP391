USE [OnlineShoppingDTVH]
GO
/****** Object:  Table [dbo].[Address]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Address](
	[AddressID] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[Province] [nvarchar](50) NULL,
	[Ward] [nvarchar](30) NULL,
	[Street] [nvarchar](50) NULL,
	[District] [nvarchar](50) NULL,
	[Status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[AddressID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ApprovalStatus]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ApprovalStatus](
	[ApprovalStatusID] [int] NOT NULL,
	[ApprovalStatus] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[ApprovalStatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Brand]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Brand](
	[BrandID] [int] IDENTITY(1,1) NOT NULL,
	[BrandName] [nvarchar](20) NULL,
	[Status] [bit] NULL,
	[Description] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[BrandID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cart]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cart](
	[CartID] [int] IDENTITY(1,1) NOT NULL,
	[ProductID] [int] NOT NULL,
	[SizeID] [int] NULL,
	[StoreID] [int] NULL,
	[UserID] [int] NOT NULL,
	[Quantity] [int] NULL,
	[Status] [bit] NULL,
	[Price] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[CartID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [nvarchar](100) NULL,
	[Gender] [nvarchar](50) NULL,
	[Description] [nvarchar](200) NULL,
	[Status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comment]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comment](
	[CommentID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[Date] [date] NULL,
	[Description] [nvarchar](200) NULL,
	[Point] [int] NULL,
	[Status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CommentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[PaymentID] [int] NOT NULL,
	[AddressID] [int] NOT NULL,
	[Date] [date] NULL,
	[TotalPrice] [float] NULL,
	[Shippingfee] [float] NULL,
	[ApprovalStatusID] [int] NULL,
	[PaymentStatus] [bit] NULL,
	[point] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[OrderDetailID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[SizeID] [int] NULL,
	[Quantity] [int] NULL,
	[Price] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PaymentMethod]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PaymentMethod](
	[PaymentID] [int] IDENTITY(1,1) NOT NULL,
	[PaymentMethod] [varchar](50) NULL,
	[Status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[PaymentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ProductID] [int] IDENTITY(1,1) NOT NULL,
	[ProductName] [nvarchar](200) NULL,
	[BrandID] [int] NOT NULL,
	[CategoryID] [int] NOT NULL,
	[Price] [float] NULL,
	[Status] [bit] NULL,
	[Description] [nvarchar](800) NULL,
	[Image] [varchar](300) NULL,
PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductDetails]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductDetails](
	[ProductID] [int] NOT NULL,
	[StoreID] [int] NOT NULL,
	[SizeID] [int] NOT NULL,
	[Quantity] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Report]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Report](
	[ReportID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NULL,
	[CommentID] [int] NULL,
	[Date] [date] NULL,
	[Description] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[ReportID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[RoleID] [int] IDENTITY(1,1) NOT NULL,
	[RoleName] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Size]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Size](
	[SizeID] [int] IDENTITY(1,1) NOT NULL,
	[NameSize] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[SizeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Store]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Store](
	[StoreID] [int] IDENTITY(1,1) NOT NULL,
	[Address] [nvarchar](50) NULL,
	[Status] [bit] NULL,
	[Description] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[StoreID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserDetails]    Script Date: 3/26/2023 9:29:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserDetails](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[RoleID] [int] NOT NULL,
	[UserName] [varchar](50) NOT NULL,
	[PassWord] [varchar](50) NULL,
	[Email] [varchar](50) NOT NULL,
	[FullName] [nvarchar](50) NULL,
	[Phone] [varchar](15) NULL,
	[DOB] [date] NULL,
	[Gender] [nvarchar](20) NULL,
	[Picture] [nvarchar](100) NULL,
	[Status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Address] ON 

INSERT [dbo].[Address] ([AddressID], [UserId], [Province], [Ward], [Street], [District], [Status]) VALUES (1, 3, N'Bình Dương', N'bình thạnh', N'3 quang trung', N'binh hoà', 1)
INSERT [dbo].[Address] ([AddressID], [UserId], [Province], [Ward], [Street], [District], [Status]) VALUES (2, 4, N'Dong thap', N'quan 9', N'5 le van viet', N'binh hoà', 1)
SET IDENTITY_INSERT [dbo].[Address] OFF
GO
INSERT [dbo].[ApprovalStatus] ([ApprovalStatusID], [ApprovalStatus]) VALUES (1, N'Chờ xác nhận')
INSERT [dbo].[ApprovalStatus] ([ApprovalStatusID], [ApprovalStatus]) VALUES (2, N'Đã xác nhận')
INSERT [dbo].[ApprovalStatus] ([ApprovalStatusID], [ApprovalStatus]) VALUES (3, N'Đã giao hàng')
INSERT [dbo].[ApprovalStatus] ([ApprovalStatusID], [ApprovalStatus]) VALUES (4, N'Đã hủy')
GO
SET IDENTITY_INSERT [dbo].[Brand] ON 

INSERT [dbo].[Brand] ([BrandID], [BrandName], [Status], [Description]) VALUES (1, N'LV', 1, N'Luon vui tuoi')
INSERT [dbo].[Brand] ([BrandID], [BrandName], [Status], [Description]) VALUES (2, N'GUCCI', 1, N'Guốc chì')
INSERT [dbo].[Brand] ([BrandID], [BrandName], [Status], [Description]) VALUES (3, N'Dior', 1, N'Đì or')
INSERT [dbo].[Brand] ([BrandID], [BrandName], [Status], [Description]) VALUES (4, N'Nike', 1, N'Hãng giày thể thao')
SET IDENTITY_INSERT [dbo].[Brand] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (1, N'Quần ngắn', N'Nam', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (2, N'Quần dài', N'Nam', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (3, N'Váy', N'Nữ', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (4, N'Đầm', N'Nữ', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (5, N'Áo tay dài', N'Nam', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (6, N'Áo tay ngắn', N'Nam', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (7, N'Áo tay dài', N'Nữ', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (8, N'Áo tay ngắn', N'Nữ', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (9, N'Áo sơ mi', N'Nam', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (10, N'Áo khoác', N'Unisex', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (11, N'Áo phông', N'Unisex', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (12, N'Quần dài', N'Unisex', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (13, N'Quần ngắn', N'Unisex', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (14, N'Nón', N'Unisex', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (15, N'Giày', N'Unisex', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (16, N'Dép', N'Unisex', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (17, N'Tất', N'Unisex', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (18, N'Túi', N'Nữ', N'Tạm thời không có mô tả', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (19, N'Giày', N'Nữ', N'chưa có thông tin điền', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (20, N'Giày nam', N'Nam', N'chỉ cho nam', 1)
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Gender], [Description], [Status]) VALUES (21, N'Quần jean nữ', N'Nữ', N'Dành cho nữ', 1)
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Comment] ON 

INSERT [dbo].[Comment] ([CommentID], [UserID], [ProductID], [Date], [Description], [Point], [Status]) VALUES (1, 3, 3, CAST(N'2021-07-12' AS Date), N'quá đẹp luôn', 5, 1)
INSERT [dbo].[Comment] ([CommentID], [UserID], [ProductID], [Date], [Description], [Point], [Status]) VALUES (2, 4, 1, CAST(N'2021-05-14' AS Date), N'quá xấu luôn', 1, 1)
INSERT [dbo].[Comment] ([CommentID], [UserID], [ProductID], [Date], [Description], [Point], [Status]) VALUES (3, 5, 5, CAST(N'2022-06-02' AS Date), N'cũng ok á', 4, 1)
INSERT [dbo].[Comment] ([CommentID], [UserID], [ProductID], [Date], [Description], [Point], [Status]) VALUES (4, 6, 3, CAST(N'2022-06-17' AS Date), N'tạm ', 3, 1)
INSERT [dbo].[Comment] ([CommentID], [UserID], [ProductID], [Date], [Description], [Point], [Status]) VALUES (5, 3, 4, CAST(N'2023-01-12' AS Date), N'10 điểm', 5, 1)
INSERT [dbo].[Comment] ([CommentID], [UserID], [ProductID], [Date], [Description], [Point], [Status]) VALUES (6, 4, 5, CAST(N'2023-01-13' AS Date), N'quá ok', 5, 1)
INSERT [dbo].[Comment] ([CommentID], [UserID], [ProductID], [Date], [Description], [Point], [Status]) VALUES (7, 5, 3, CAST(N'2023-02-13' AS Date), N'cũng ok', 4, 1)
INSERT [dbo].[Comment] ([CommentID], [UserID], [ProductID], [Date], [Description], [Point], [Status]) VALUES (8, 6, 1, CAST(N'2023-01-13' AS Date), N'xấu quá chời', 2, 1)
INSERT [dbo].[Comment] ([CommentID], [UserID], [ProductID], [Date], [Description], [Point], [Status]) VALUES (9, 4, 4, CAST(N'2023-02-23' AS Date), N'lần này thì cũng tạm', 3, 1)
INSERT [dbo].[Comment] ([CommentID], [UserID], [ProductID], [Date], [Description], [Point], [Status]) VALUES (10, 5, 2, CAST(N'2022-06-02' AS Date), N'cũng ok ....', 2, 1)
INSERT [dbo].[Comment] ([CommentID], [UserID], [ProductID], [Date], [Description], [Point], [Status]) VALUES (11, 6, 6, CAST(N'2022-06-02' AS Date), N'quá tốt luôn', 5, 1)
INSERT [dbo].[Comment] ([CommentID], [UserID], [ProductID], [Date], [Description], [Point], [Status]) VALUES (12, 4, 5, CAST(N'2022-06-02' AS Date), N'không tốt', 1, 1)
SET IDENTITY_INSERT [dbo].[Comment] OFF
GO
SET IDENTITY_INSERT [dbo].[Order] ON 

INSERT [dbo].[Order] ([OrderID], [UserID], [PaymentID], [AddressID], [Date], [TotalPrice], [Shippingfee], [ApprovalStatusID], [PaymentStatus], [point]) VALUES (2, 3, 1, 1, CAST(N'2023-03-26' AS Date), 680000, 30000, 3, 1, NULL)
INSERT [dbo].[Order] ([OrderID], [UserID], [PaymentID], [AddressID], [Date], [TotalPrice], [Shippingfee], [ApprovalStatusID], [PaymentStatus], [point]) VALUES (3, 3, 1, 1, CAST(N'2023-03-26' AS Date), 615000, 30000, 3, 1, NULL)
INSERT [dbo].[Order] ([OrderID], [UserID], [PaymentID], [AddressID], [Date], [TotalPrice], [Shippingfee], [ApprovalStatusID], [PaymentStatus], [point]) VALUES (4, 3, 1, 1, CAST(N'2023-03-26' AS Date), 1350000, 30000, 3, 1, NULL)
INSERT [dbo].[Order] ([OrderID], [UserID], [PaymentID], [AddressID], [Date], [TotalPrice], [Shippingfee], [ApprovalStatusID], [PaymentStatus], [point]) VALUES (5, 3, 1, 1, CAST(N'2023-03-26' AS Date), 43500000, 30000, 2, 0, NULL)
INSERT [dbo].[Order] ([OrderID], [UserID], [PaymentID], [AddressID], [Date], [TotalPrice], [Shippingfee], [ApprovalStatusID], [PaymentStatus], [point]) VALUES (6, 3, 1, 1, CAST(N'2023-03-26' AS Date), 175000, 30000, 1, 0, NULL)
INSERT [dbo].[Order] ([OrderID], [UserID], [PaymentID], [AddressID], [Date], [TotalPrice], [Shippingfee], [ApprovalStatusID], [PaymentStatus], [point]) VALUES (7, 3, 1, 1, CAST(N'2023-03-26' AS Date), 295000, 30000, 4, 0, NULL)
INSERT [dbo].[Order] ([OrderID], [UserID], [PaymentID], [AddressID], [Date], [TotalPrice], [Shippingfee], [ApprovalStatusID], [PaymentStatus], [point]) VALUES (8, 3, 1, 1, CAST(N'2023-03-26' AS Date), 8000000, 30000, 1, 0, NULL)
SET IDENTITY_INSERT [dbo].[Order] OFF
GO
SET IDENTITY_INSERT [dbo].[OrderDetail] ON 

INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [ProductID], [SizeID], [Quantity], [Price]) VALUES (2, 2, 55, 3, 1, 500000)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [ProductID], [SizeID], [Quantity], [Price]) VALUES (3, 2, 20, 2, 4, 180000)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [ProductID], [SizeID], [Quantity], [Price]) VALUES (4, 3, 3, 2, 3, 150000)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [ProductID], [SizeID], [Quantity], [Price]) VALUES (5, 3, 6, 3, 1, 35000)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [ProductID], [SizeID], [Quantity], [Price]) VALUES (6, 3, 11, 2, 1, 430000)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [ProductID], [SizeID], [Quantity], [Price]) VALUES (7, 4, 10, 3, 1, 1350000)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [ProductID], [SizeID], [Quantity], [Price]) VALUES (8, 5, 43, 2, 1, 5500000)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [ProductID], [SizeID], [Quantity], [Price]) VALUES (9, 5, 48, 2, 2, 38000000)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [ProductID], [SizeID], [Quantity], [Price]) VALUES (10, 6, 6, 3, 5, 175000)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [ProductID], [SizeID], [Quantity], [Price]) VALUES (11, 7, 20, 2, 3, 135000)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [ProductID], [SizeID], [Quantity], [Price]) VALUES (12, 7, 12, 2, 1, 160000)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [ProductID], [SizeID], [Quantity], [Price]) VALUES (13, 8, 54, 3, 1, 8000000)
SET IDENTITY_INSERT [dbo].[OrderDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[PaymentMethod] ON 

INSERT [dbo].[PaymentMethod] ([PaymentID], [PaymentMethod], [Status]) VALUES (1, N'Cash on delivery (COD)', 1)
INSERT [dbo].[PaymentMethod] ([PaymentID], [PaymentMethod], [Status]) VALUES (2, N'Paypal Payment', 0)
SET IDENTITY_INSERT [dbo].[PaymentMethod] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (1, N'Áo thun coton', 3, 11, 100000, 1, N'Chiếc áo thun là sản phẩm thời trang đơn giản nhưng vô cùng phổ biến. Với chất liệu vải cotton mềm mại và thoáng khí, chiếc áo thun này cực kỳ thoải mái khi mặc. Thiết kế đơn giản với cổ tròn và tay ngắn giúp chiếc áo này trở nên tiện dụng hơn.', N'aothungau.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (2, N'Quần bông', 2, 12, 200000, 1, N'Chiếc quần bông là sản phẩm thời trang phổ biến cho cả nam và nữ. Với chất liệu vải cotton mềm mại và thoải mái, chiếc quần bông này dễ dàng kết hợp với nhiều kiểu áo khác nhau. Thiết kế đơn giản với túi hai bên giúp chiếc quần này trở nên tiện dụng hơn.', N'setbong.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (3, N'Nón lưỡi trai', 2, 14, 50000, 1, N'Chiếc nón lưỡi trai là sản phẩm thời trang phổ biến cho cả nam và nữ. Với thiết kế đơn giản và màu sắc đa dạng, chiếc nón này phù hợp với nhiều phong cách khác nhau. Chất liệu vải bền bỉ và đội nón cực kỳ thoải mái.', N'nonluoitrai.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (4, N'Giày da cao cấp', 1, 15, 1000000, 1, N'Chiếc giày da là sản phẩm thời trang đẳng cấp cho cả nam và nữ. Với chất liệu da cao cấp và màu sắc đa dạng, chiếc giày này phù hợp với nhiều phong cách khác nhau. Thiết kế đơn giản hoặc phức tạp tùy thuộc vào sở thích và hoàn cảnh sử dụng. Đế giày chắc chắn và êm ái giúp chiếc giày này mang lại sự thoải mái khi di chuyển.', N'dayda.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (5, N'Dép cao su', 1, 16, 40000, 1, N'Chiếc dép là sản phẩm thời trang phổ biến trong mùa hè. Với thiết kế đơn giản và màu sắc đa dạng, chiếc dép này phù hợp với nhiều phong cách khác nhau. Chất liệu vải bền bỉ và đế dép êm ái giúp chiếc dép này mang lại sự thoải mái khi mặc.', N'dep.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (6, N'Tất', 3, 17, 35000, 1, N'Chiếc tất là sản phẩm thời trang nhỏ nhưng cực kỳ quan trọng trong việc tạo ra phong cách của bạn. Với chất liệu cotton thoáng khí và màu sắc đa dạng, chiếc tất này giúp bạn thoải mái khi mặc giày và kết hợp với nhiều loại giày và trang phục khác nhau.', N'tat.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (7, N'Áo Phông', 2, 11, 150000, 1, N'Chiếc áo phông là một trong những sản phẩm thời trang phổ biến nhất trên thế giới. Với thiết kế đơn giản, kiểu dáng thoải mái và màu sắc đa dạng, áo phông phù hợp với nhiều phong cách và hoàn cảnh khác nhau. Với chất liệu vải cotton thấm hút mồ hôi và mềm mại, chiếc áo này cực kỳ thoải mái khi mặc.', N'aophong.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (8, N'Hoodie siu xịn', 1, 10, 350000, 1, N'Chiếc áo hoodie unisex này là sự kết hợp giữa phong cách thể thao và đường phố. Với chất liệu vải nhẹ và đàn hồi, chiếc áo này mang lại sự thoải mái', N'hutdi.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (9, N'Quần jean', 3, 2, 200000, 1, N'Chiếc quần jean là một trong những sản phẩm thời trang không thể thiếu trong tủ đồ của bất kỳ ai. Với chất liệu denim bền bỉ và màu sắc đa dạng, chiếc quần jean này phù hợp với nhiều phong cách và hoàn cảnh khác nhau. Thiết kế đơn giản với túi hai bên và túi sau giúp chiếc quần này trở nên tiện dụng hơn.', N'jean.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (10, N'Giày sneaker', 1, 15, 1350000, 1, N'Chiếc giày sneaker là sự kết hợp tuyệt vời giữa phong cách và thoải mái. Với thiết kế đơn giản và màu sắc đa dạng, chiếc giày này phù hợp với nhiều phong cách và hoàn cảnh khác nhau. Chất liệu vải bền bỉ và đế giày đàn hồi giúp chiếc giày này mang lại sự thoải mái khi mặc.', N'giay.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (11, N'Váy đầm', 2, 4, 430000, 1, N'Chiếc váy đầm là sản phẩm thời trang dành cho phụ nữ, với thiết kế đa dạng và màu sắc đa dạng. Với chất liệu vải mềm mại và thoáng khí, chiếc váy này mang lại sự thoải mái và gợi cảm cho người mặc. Thiết kế đơn giản hoặc phức tạp tùy thuộc vào sở thích và hoàn cảnh sử dụng.', N'vay.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (12, N'Túi xách nữ', 2, 18, 160000, 1, N'Chiếc túi xách nữ này là một sự kết hợp tuyệt vời giữa phong cách cổ điển và hiện đại. Với chất liệu da mềm mại và màu sắc tươi sáng, chiếc túi này có thể dễ dàng kết hợp với nhiều trang phục khác nhau. Thiết kế gọn nhẹ và tiện lợi, túi xách này là một lựa chọn tuyệt vời cho những người phụ nữ đang tìm kiếm một chiếc túi đa năng.', N'tuixach.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (13, N'Áo khoác nam', 3, 10, 790000, 1, N'Chiếc áo khoác nam này với kiểu dáng thời trang và màu sắc trung tính là sự lựa chọn hoàn hảo cho những ngày se lạnh. Với chất liệu vải bền bỉ và lớp lót ấm áp, chiếc áo khoác này giữ ấm cho người mặc trong mùa đông. Thiết kế cổ áo bẻ và túi hai bên tạo nên sự thoải mái và tiện dụng cho người mặc.', N'Ao.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (14, N'Váy xòe', 2, 3, 430000, 1, N'Váy xòe là sản phẩm thời trang dành cho phái đẹp, với đường xòe rộng và vòng eo nhỏ giúp làm nổi bật vóc dáng. Váy xòe có nhiều kiểu dáng và màu sắc khác nhau, thích hợp để mặc trong các dịp đi chơi, tiệc tùng hay đi làm.', N'vaychan.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (15, N'Áo sơ mi nam', 2, 9, 740000, 1, N'Áo sơ mi nam là sản phẩm thời trang không thể thiếu trong tủ quần áo của phái mạnh. Thiết kế cổ tròn hoặc cổ áo, tay dài hoặc tay ngắn, chất liệu vải cotton hoặc lụa, áo sơ mi nam có thể kết hợp với quần tây, quần jeans hay chinos để tạo nên nhiều phong cách khác nhau.', N'aosomi.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (16, N'Áo khoác bomber', 3, 10, 230000, 1, N'Áo khoác bomber là sản phẩm thời trang đang được ưa chuộng trong những năm gần đây. Thiết kế dáng ôm, tay dài, khóa kéo, chất liệu vải nỉ hoặc da cao cấp giúp áo trở nên sang trọng và đẳng cấp. Áo khoác bomber thích hợp để mặc vào mùa đông, đi du lịch hay đi chơi.', N'aogia.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (17, N'Váy midi', 1, 3, 7560000, 1, N'Váy midi là một sản phẩm thời trang dành cho phái đẹp, có chiều dài từ gối đến chân giúp che đi nhược điểm về chân. Váy midi có nhiều kiểu dáng khác nhau như xòe, ôm, suông, với chất liệu vải thô, lụa hoặc ren. Váy midi thích hợp để mặc trong các dịp đi tiệc, đi làm hoặc đi chơi.', N'vaychan.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (18, N'Áo khoác gió', 1, 10, 830000, 1, N'Áo khoác gió là một sản phẩm thời trang dành cho cả nam và nữ, có chất liệu vải thun hoặc nylon, với khóa kéo và túi hai bên. Áo khoác gió thường có màu sắc tươi sáng, thiết kế dáng rộng thoải mái, thích hợp để mặc vào mùa xuân hoặc mùa thu.', N'aogia.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (19, N'Giày cao gót', 3, 15, 7530000, 1, N'Giày cao gót là một sản phẩm thời trang dành cho phái đẹp, giúp làm tôn lên vóc dáng và phong cách. Có nhiều kiểu dáng khác nhau như mũi nhọn, mũi tròn, quai ngang hay quai chéo, với chất liệu da, nhung hoặc nubuck. Giày cao gót thích hợp để mang đi tiệc, đi làm hoặc đi chơi.', N'giaycaogot.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (20, N'Áo len nam', 2, 5, 45000, 1, N'Áo len nam là một sản phẩm thời trang dành cho phái mạnh, có thiết kế dáng ôm hoặc suông, với chất liệu len mềm mại và ấm áp. Áo len nam thường có màu sắc đơn giản như đen, trắng, xám, nâu hoặc xanh, thích hợp để mặc vào mùa đông, đi làm hoặc đi chơi.', N'aolen.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (21, N'Túi xách nữ', 3, 18, 7770000, 1, N'Túi xách nữ là một sản phẩm thời trang không thể thiếu trong bộ sưu tập của phái đẹp. Có nhiều kiểu dáng và kích thước khác nhau, từ túi xách nhỏ để đi dạo phố đến túi xách lớn để đi làm. Chất liệu của túi xách cũng đa dạng, từ da, vải thô, canvas, denim đến nhựa PVC. Túi xách nữ thường có màu sắc tươi sáng và thiết kế đẹp mắt, thích hợp để mang đi làm, đi chơi hay dạo phố.', N'tuixach.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (22, N'Túi xách LV', 1, 18, 400000, 0, N'Chiếc túi xách Passy nhỏ gọn bằng vải Monogram với dây xích trượt là tinh hoa của phong cách giản dị sang trọng. Một chiếc túi tiện dụng cho cả ngày đến đêm, nó có các túi bên ngoài trước và sau để lấy nhanh các vật dụng cần thiết cũng như hai túi bên trong. Nó được đóng bằng một khóa LV từ tính bằng nhựa đen với đường viền màu vàng', N'tuiLVnu1.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (23, N'Túi xách LV', 1, 18, 400000, 1, N'Chiếc túi xách Passy nhỏ gọn bằng vải Monogram với dây xích trượt là tinh hoa của phong cách giản dị sang trọng. Một chiếc túi tiện dụng cho cả ngày đến đêm, nó có các túi bên ngoài trước và sau để lấy nhanh các vật dụng cần thiết cũng như hai túi bên trong. Nó được đóng bằng một khóa LV từ tính bằng nhựa đen với đường viền màu vàng', N'tuiLVnu1.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (24, N'Combo váy túi xách LV chính hãng', 1, 3, 900000, 1, N'Váy Louis Vuitton siêu cấp', N'VayLVnu2.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (25, N'Combo váy túi xách LV chính hãng', 1, 3, 900000, 0, N'Váy Louis Vuitton siêu cấp', N'VayLVnu2.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (26, N'Áo sơ mi nam GUCCI', 2, 9, 900000, 1, N'Áo sơ mi nam Gucci là một trong những sản phẩm thời trang cao cấp được thiết kế bởi nhãn hiệu thời trang danh tiếng Gucci. Áo sơ mi nam Gucci thường có kiểu dáng cổ điển và sang trọng, được làm từ chất liệu vải cao cấp như lụa, cotton hoặc vải dệt kim.', N'aosominam.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (27, N'Nón vành rộng cao cấp', 3, 14, 400000, 1, N'Tất cả các sản phẩm giày, túi đều là chất liệu da thật cao cấp nhập khẩu chuẩn VIP', N'NonDiorUnisex.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (28, N'Quần short Gucci nam ', 2, 1, 300000, 1, N'GUCCI TECHNICAL JERSEY SHORTS  MẪU QUẦN SHORT NẰM TRONG BST XUÂN HÈ CỦA GUCCI  PHONG CÁCH THIẾT KẾ ĐẦY CHẤT THỂ THAO ĐƯỜNG PHỐ  ĐIỂM NHẤN SỌC JACQUARD GUCCI DỌC THEO HAI BÊN QUẦN  FORM QUẦN RỘNG RÃI THEO XU HƯỚNG HIỆN ĐẠI  MIX DỄ DÀNG VỚI NHIỀU TRANG PHỤC KHÁC NHAU', N'quanduigucci1.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (29, N'Áo tay ngắn nam', 1, 6, 5000000, 0, N'Áo sơ mi nam Gucci là một trong những sản phẩm thời trang cao cấp được thiết kế bởi nhãn hiệu thời trang danh tiếng Gucci. Áo sơ mi nam Gucci thường có kiểu dáng cổ điển và sang trọng, được làm từ chất liệu vải cao cấp như lụa, cotton hoặc vải dệt kim.', N'aotayngan1.jsp')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (30, N'Áo tay ngắn nam', 1, 1, 5000000, 1, N'Áo sơ mi nam Gucci là một trong những sản phẩm thời trang cao cấp được thiết kế bởi nhãn hiệu thời trang danh tiếng Gucci. Áo sơ mi nam Gucci thường có kiểu dáng cổ điển và sang trọng, được làm từ chất liệu vải cao cấp như lụa, cotton hoặc vải dệt kim.', N'aotayngan.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (31, N'Túi xách nữ', 1, 18, 300000, 1, N'Chiếc túi xách Passy nhỏ gọn bằng vải Monogram với dây xích trượt là tinh hoa của phong cách giản dị sang trọng. Một chiếc túi tiện dụng cho cả ngày đến đêm, nó có các túi bên ngoài trước và sau để lấy nhanh các vật dụng cần thiết cũng như hai túi bên trong. Nó được đóng bằng một khóa LV từ tính bằng nhựa đen với đường viền màu vàng', N'tuiLVnu2.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (32, N'Túi xách LV chấm bi', 1, 3, 300000, 1, N'Chiếc túi xách Passy nhỏ gọn bằng vải Monogram với dây xích trượt là tinh hoa của phong cách giản dị sang trọng. Một chiếc túi tiện dụng cho cả ngày đến đêm, nó có các túi bên ngoài trước và sau để lấy nhanh các vật dụng cần thiết cũng như hai túi bên trong. Nó được đóng bằng một khóa LV từ tính bằng nhựa đen với đường viền màu vàng', N'tuiLVnu3.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (33, N'Đầm ', 1, 4, 9000000, 1, N'Chiếc đầm này có kiểu dáng dáng xòe, phù hợp với nhiều dáng người và mang lại cảm giác nữ tính, thanh lịch. Chất liệu vải được sử dụng là một loại vải nhẹ, thoáng mát và dễ chịu khi mặc.', N'damlv1.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (34, N'Giày GUCCI chính hãng', 2, 10, 19000000, 0, N'Tất cả các sản phẩm giày, túi đều là chất liệu da thật cao cấp nhập khẩu chuẩn VIP', N'giaygucci2.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (35, N'Giày GUCCI chính hãng 2023', 1, 15, 19500000, 1, N'Tất cả các sản phẩm giày, túi đều là chất liệu da thật cao cấp nhập khẩu chuẩn VIP', N'giaygucci1.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (36, N'Giày GUCCI chính hãng 2022', 2, 15, 19000000, 1, N'Tất cả các sản phẩm giày, túi đều là chất liệu da thật cao cấp nhập khẩu chuẩn VIP', N'giaygucci2.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (37, N'Túi xách LV', 3, 18, 8900000, 0, N'Chất lượng like authentic sử dụng chất liệu da nguyên bản như chính hãng sản xuất hoàn toàn bằng thủ công cam kết chất lượng chuẩn 99% so với chính hãng full box và phụ kiện', N'tuidior.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (38, N'Túi xách Dior', 3, 18, 5000000, 1, N'Chất lượng like authentic sử dụng chất liệu da nguyên bản như chính hãng sản xuất hoàn toàn bằng thủ công cam kết chất lượng chuẩn 99% so với chính hãng full box và phụ kiện', N'tuidior.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (39, N'Túi xách Dior 2018 limited', 3, 18, 40000000, 1, N'Chất lượng like authentic sử dụng chất liệu da nguyên bản như chính hãng sản xuất hoàn toàn bằng thủ công cam kết chất lượng chuẩn 99% so với chính hãng full box và phụ kiện', N'tuidior1.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (40, N'Túi xách Dior 2020', 3, 18, 50000000, 1, N'Chất lượng like authentic sử dụng chất liệu da nguyên bản như chính hãng sản xuất hoàn toàn bằng thủ công cam kết chất lượng chuẩn 99% so với chính hãng full box và phụ kiện', N'tuidior3.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (41, N'Áo khoác hàng hiệu LV', 1, 10, 3000000, 1, N'Sử dụng công nghệ Formotion: giúp người mặc dễ dàng vận động và tạo cảm giác thoải mái khi mặc  Áo thông thoáng mồ hôi tốt, giải phóng nhiệt cơ thể ra bên ngoài nhanh.  Chất liệu: Vải nỉ 100% Polyester . mềm mại có thể giữ ấm tốt .  Có 2 túi áo bên cạnh, kết hợp với thiết kế dây kéo đen áo màu trắng đâm phong cách .  Có khóa kéo lên tới cổ áo.  Cổ tay áo đàn hồi co dãn.  Logo thương hiệu nổi bật được thể hiện trên áo .', N'aokhoac.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (42, N'Áo khoác hàng hiệu LV mới nhất', 1, 10, 4000000, 1, N'Sử dụng công nghệ Formotion: giúp người mặc dễ dàng vận động và tạo cảm giác thoải mái khi mặc  Áo thông thoáng mồ hôi tốt, giải phóng nhiệt cơ thể ra bên ngoài nhanh.  Chất liệu: Vải nỉ 100% Polyester . mềm mại có thể giữ ấm tốt .  Có 2 túi áo bên cạnh, kết hợp với thiết kế dây kéo đen áo màu trắng đâm phong cách .  Có khóa kéo lên tới cổ áo.  Cổ tay áo đàn hồi co dãn.  Logo thương hiệu nổi bật được thể hiện trên áo .', N'aokhoac1.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (43, N'Áo khoác hàng hiệu LV cho cả nam lẫn nữ', 1, 10, 5500000, 1, N'Sử dụng công nghệ Formotion: giúp người mặc dễ dàng vận động và tạo cảm giác thoải mái khi mặc  Áo thông thoáng mồ hôi tốt, giải phóng nhiệt cơ thể ra bên ngoài nhanh.  Chất liệu: Vải nỉ 100% Polyester . mềm mại có thể giữ ấm tốt .  Có 2 túi áo bên cạnh, kết hợp với thiết kế dây kéo đen áo màu trắng đâm phong cách .  Có khóa kéo lên tới cổ áo.  Cổ tay áo đàn hồi co dãn.  Logo thương hiệu nổi bật được thể hiện trên áo .', N'aokhoaclv.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (44, N'Giày LV mới nhất 2023', 1, 15, 9000000, 1, N'Tất cả các sản phẩm giày, túi đều là chất liệu da thật cao cấp nhập khẩu chuẩn VIP', N'giaylv3.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (45, N'Giày LV mới nhất tháng 3', 1, 15, 5000000, 1, N'Tất cả các sản phẩm giày, túi đều là chất liệu da thật cao cấp nhập khẩu chuẩn VIP', N'giaylv4.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (46, N'Giày LV mới nhất tháng 1', 1, 15, 4000000, 1, N'Tất cả các sản phẩm giày, túi đều là chất liệu da thật cao cấp nhập khẩu chuẩn VIP', N'giaylv.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (47, N'Áo thun mới của DIOR', 3, 6, 5000000, 1, N'Sử dụng công nghệ Formotion: giúp người mặc dễ dàng vận động và tạo cảm giác thoải mái khi mặc  Áo thông thoáng mồ hôi tốt, giải phóng nhiệt cơ thể ra bên ngoài nhanh.  Chất liệu: Vải nỉ 100% Polyester . mềm mại có thể giữ ấm tốt .  Có 2 túi áo bên cạnh, kết hợp với thiết kế dây kéo đen áo màu trắng đâm phong cách .  Có khóa kéo lên tới cổ áo.  Cổ tay áo đàn hồi co dãn.  Logo thương hiệu nổi bật được thể hiện trên áo .', N'aothun.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (48, N'Giày NIKE cao cấp', 1, 20, 19000000, 1, N'Chất lượng like authentic sử dụng chất liệu da nguyên bản như chính hãng sản xuất hoàn toàn bằng thủ công cam kết chất lượng chuẩn 99% so với chính hãng full box và phụ kiện', N'nikeair.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (49, N'Giày nữ hồng mới của LV', 1, 19, 9000000, 1, N'Sử dụng công nghệ Formotion: giúp người mặc dễ dàng vận động và tạo cảm giác thoải mái khi mặc  Áo thông thoáng mồ hôi tốt, giải phóng nhiệt cơ thể ra bên ngoài nhanh.  Chất liệu: Vải nỉ 100% Polyester . mềm mại có thể giữ ấm tốt .  Có 2 túi áo bên cạnh, kết hợp với thiết kế dây kéo đen áo màu trắng đâm phong cách .  Có khóa kéo lên tới cổ áo.  Cổ tay áo đàn hồi co dãn.  Logo thương hiệu nổi bật được thể hiện trên áo .', N'giaylv2.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (50, N'Áo thun giới trẻ', 1, 11, 500000, 1, N'Áo thun nam nữ oversize sử dụng chất vải cotton 65/35 co giãn 4 chiều. Là loại vải có đặc điểm mềm mịn, độ co giãn cao, khả năng thấm hút tốt và được dệt hoàn toàn từ sợi cây bông tự nhiên. Chất vải cotton cực kỳ thân thiện với làn da.', N'aotayngan2.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (51, N'Váy LV mẫu mới 2023', 1, 3, 400000, 1, N'Váy Louis Vuitton siêu cấp', N'vay.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (52, N'Váy ngắn siêu đẹp', 1, 3, 5000000, 1, N'Váy Louis Vuitton siêu cấp', N'vay2.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (53, N'Quần jean nữ thời trang', 2, 21, 600000, 1, N'Quần jean xịn cho phái nữ.', N'quanjean.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (54, N'Quần jean nam thời trang 2022', 1, 2, 8000000, 1, N'Quần jean xịn cho nam giới.', N'quanjean2.jpg')
INSERT [dbo].[Product] ([ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image]) VALUES (55, N'Quần thể thao chính hãng Nike', 4, 10, 500000, 1, N'Chất vải co giãn, đẹp, bền, không phai, không xù, không bám dính. Đường may tinh tế, chỉnh chu, khéo léo. Màu sắc đa dạng, trẻ trung', N'quannike.jpg')
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (1, 1, 1, 3)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (1, 1, 2, 4)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (1, 1, 3, 0)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (2, 1, 1, 0)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (2, 1, 2, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (2, 1, 3, 9)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (3, 1, 1, 7)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (3, 1, 2, 3)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (3, 1, 3, 2)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (4, 1, 1, 5)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (4, 1, 2, 2)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (4, 1, 3, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (5, 1, 1, 2)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (5, 1, 2, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (5, 1, 3, 5)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (7, 1, 1, 7)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (7, 1, 2, 6)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (7, 1, 3, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (8, 1, 1, 9)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (22, 1, 1, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (23, 1, 1, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (24, 1, 1, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (25, 1, 1, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (26, 1, 1, 16)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (26, 1, 2, 12)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (26, 1, 3, 4)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (27, 1, 2, 9)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (28, 1, 2, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (28, 1, 3, 162)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (29, 1, 1, 22)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (29, 1, 2, 46)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (29, 1, 3, 38)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (30, 1, 1, 16)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (30, 1, 2, 162)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (31, 1, 2, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (31, 1, 3, 33)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (32, 1, 2, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (32, 1, 3, 162)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (33, 1, 1, 123)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (33, 1, 2, 162)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (34, 1, 1, 24)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (34, 1, 2, 22)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (34, 1, 3, 21)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (35, 1, 1, 29)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (35, 1, 2, 28)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (35, 1, 3, 24)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (36, 1, 1, 12)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (36, 1, 2, 13)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (36, 1, 3, 56)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (37, 1, 2, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (38, 1, 1, 123)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (39, 1, 1, 50)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (40, 1, 1, 58)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (41, 1, 1, 22)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (41, 1, 2, 42)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (42, 1, 1, 224)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (42, 1, 3, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (43, 1, 2, 0)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (44, 1, 2, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (45, 1, 1, 162)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (45, 1, 3, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (46, 1, 2, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (47, 1, 3, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (48, 1, 1, 11)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (48, 1, 2, 11)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (48, 1, 3, 33)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (49, 1, 1, 99)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (50, 1, 1, 12)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (50, 1, 2, 12)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (50, 1, 3, 100)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (51, 1, 1, 20)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (52, 1, 2, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (53, 1, 1, 30)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (54, 1, 2, 1)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (54, 1, 3, 15)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (55, 1, 3, 0)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (6, 1, 1, 50)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (6, 1, 3, 54)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (9, 1, 1, 20)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (9, 1, 2, 10)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (9, 1, 3, 40)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (10, 1, 3, 49)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (11, 1, 1, 20)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (11, 1, 2, 29)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (12, 1, 2, 49)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (12, 1, 3, 10)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (13, 1, 3, 30)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (14, 1, 2, 40)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (15, 1, 3, 60)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (16, 1, 3, 20)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (17, 1, 1, 10)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (17, 1, 2, 20)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (18, 1, 1, 50)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (18, 1, 2, 10)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (19, 1, 2, 10)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (20, 1, 1, 40)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (20, 1, 2, 13)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (20, 1, 3, 30)
INSERT [dbo].[ProductDetails] ([ProductID], [StoreID], [SizeID], [Quantity]) VALUES (21, 1, 2, 10)
GO
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([RoleID], [RoleName]) VALUES (1, N'ADMIN')
INSERT [dbo].[Role] ([RoleID], [RoleName]) VALUES (2, N'STAFF')
INSERT [dbo].[Role] ([RoleID], [RoleName]) VALUES (3, N'CUSTOMER')
SET IDENTITY_INSERT [dbo].[Role] OFF
GO
SET IDENTITY_INSERT [dbo].[Size] ON 

INSERT [dbo].[Size] ([SizeID], [NameSize]) VALUES (1, N'S')
INSERT [dbo].[Size] ([SizeID], [NameSize]) VALUES (2, N'M')
INSERT [dbo].[Size] ([SizeID], [NameSize]) VALUES (3, N'L')
SET IDENTITY_INSERT [dbo].[Size] OFF
GO
SET IDENTITY_INSERT [dbo].[Store] ON 

INSERT [dbo].[Store] ([StoreID], [Address], [Status], [Description]) VALUES (1, N'54 Điện Biên Phủ', 1, N'T?ng kho')
INSERT [dbo].[Store] ([StoreID], [Address], [Status], [Description]) VALUES (2, N'222 Duong Ba Trac, P1, Quận 9', 1, N'Chi nhanh 1')
INSERT [dbo].[Store] ([StoreID], [Address], [Status], [Description]) VALUES (3, N'123 Nguyễn Xuyển, P.PLA, Quận 9', 1, N'Chi nhanh 2')
INSERT [dbo].[Store] ([StoreID], [Address], [Status], [Description]) VALUES (4, N'543 Lê Văn Việt, P.TNP, Quận 9', 1, N'Chi nhanh 3')
SET IDENTITY_INSERT [dbo].[Store] OFF
GO
SET IDENTITY_INSERT [dbo].[UserDetails] ON 

INSERT [dbo].[UserDetails] ([UserID], [RoleID], [UserName], [PassWord], [Email], [FullName], [Phone], [DOB], [Gender], [Picture], [Status]) VALUES (1, 1, N'dunghh', N'12345', N'hominhdund@gmail.com', N'Hồ Minh Dũng', N'963697057', CAST(N'2002-04-21' AS Date), N'Nam', N'logo.png', 1)
INSERT [dbo].[UserDetails] ([UserID], [RoleID], [UserName], [PassWord], [Email], [FullName], [Phone], [DOB], [Gender], [Picture], [Status]) VALUES (2, 2, N'dunghh1', N'12345', N'hominhdund1@gmail.com', N'Hồ Minh Dũng', N'963694557', CAST(N'2002-04-22' AS Date), N'Nam', N'logo.png', 1)
INSERT [dbo].[UserDetails] ([UserID], [RoleID], [UserName], [PassWord], [Email], [FullName], [Phone], [DOB], [Gender], [Picture], [Status]) VALUES (3, 3, N'dunghh2', N'12345', N'hominhdund2@gmail.com', N'Hồ Minh Dũng', N'967897057', CAST(N'2002-04-23' AS Date), N'Nam', N'logo.png', 1)
INSERT [dbo].[UserDetails] ([UserID], [RoleID], [UserName], [PassWord], [Email], [FullName], [Phone], [DOB], [Gender], [Picture], [Status]) VALUES (4, 3, N'dinh', N'12345', N'vinhtc191@gmail.com', N'Trần Công Vinh', N'909321452', CAST(N'2002-04-12' AS Date), N'Nam', N'logo.png', 1)
INSERT [dbo].[UserDetails] ([UserID], [RoleID], [UserName], [PassWord], [Email], [FullName], [Phone], [DOB], [Gender], [Picture], [Status]) VALUES (5, 3, N'thinh', N'12345', N'hthinhnd2@gmail.com', N'Trường Thịnh', N'95427057', CAST(N'2003-04-23' AS Date), N'Nam', N'logo.png', 1)
INSERT [dbo].[UserDetails] ([UserID], [RoleID], [UserName], [PassWord], [Email], [FullName], [Phone], [DOB], [Gender], [Picture], [Status]) VALUES (6, 3, N'hieu', N'12345', N'hieu2@gmail.com', N'Hiếu', N'9674327057', CAST(N'2002-03-23' AS Date), N'Nam', N'logo.png', 1)
SET IDENTITY_INSERT [dbo].[UserDetails] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__UserDeta__A9D105344F6AA73E]    Script Date: 3/26/2023 9:29:14 PM ******/
ALTER TABLE [dbo].[UserDetails] ADD UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__UserDeta__C9F284562F016D32]    Script Date: 3/26/2023 9:29:14 PM ******/
ALTER TABLE [dbo].[UserDetails] ADD UNIQUE NONCLUSTERED 
(
	[UserName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Address]  WITH CHECK ADD  CONSTRAINT [fk_Address_UserDetails] FOREIGN KEY([UserId])
REFERENCES [dbo].[UserDetails] ([UserID])
GO
ALTER TABLE [dbo].[Address] CHECK CONSTRAINT [fk_Address_UserDetails]
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [fk_Cart_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [fk_Cart_Product]
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [fk_Cart_Size] FOREIGN KEY([SizeID])
REFERENCES [dbo].[Size] ([SizeID])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [fk_Cart_Size]
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [fk_Cart_Store] FOREIGN KEY([StoreID])
REFERENCES [dbo].[Store] ([StoreID])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [fk_Cart_Store]
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [fk_Cart_UserDetails] FOREIGN KEY([UserID])
REFERENCES [dbo].[UserDetails] ([UserID])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [fk_Cart_UserDetails]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [fk_Comment_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [fk_Comment_Product]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [fk_UserDetails_Comment] FOREIGN KEY([UserID])
REFERENCES [dbo].[UserDetails] ([UserID])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [fk_UserDetails_Comment]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [fk_Order_Address] FOREIGN KEY([AddressID])
REFERENCES [dbo].[Address] ([AddressID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [fk_Order_Address]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [fk_Order_Approval] FOREIGN KEY([ApprovalStatusID])
REFERENCES [dbo].[ApprovalStatus] ([ApprovalStatusID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [fk_Order_Approval]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [fk_Order_Payment] FOREIGN KEY([PaymentID])
REFERENCES [dbo].[PaymentMethod] ([PaymentID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [fk_Order_Payment]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [fk_Order_UserDetails] FOREIGN KEY([UserID])
REFERENCES [dbo].[UserDetails] ([UserID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [fk_Order_UserDetails]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [fk_OrderDetails_Order] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [fk_OrderDetails_Order]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [fk_OrderDetails_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [fk_OrderDetails_Product]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [fk_Product_Brand] FOREIGN KEY([BrandID])
REFERENCES [dbo].[Brand] ([BrandID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [fk_Product_Brand]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [fk_Product_Category] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Category] ([CategoryID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [fk_Product_Category]
GO
ALTER TABLE [dbo].[ProductDetails]  WITH CHECK ADD  CONSTRAINT [fk_ProductDetails_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[ProductDetails] CHECK CONSTRAINT [fk_ProductDetails_Product]
GO
ALTER TABLE [dbo].[ProductDetails]  WITH CHECK ADD  CONSTRAINT [fk_ProductDetails_Size] FOREIGN KEY([SizeID])
REFERENCES [dbo].[Size] ([SizeID])
GO
ALTER TABLE [dbo].[ProductDetails] CHECK CONSTRAINT [fk_ProductDetails_Size]
GO
ALTER TABLE [dbo].[ProductDetails]  WITH CHECK ADD  CONSTRAINT [fk_ProductDetails_Store] FOREIGN KEY([StoreID])
REFERENCES [dbo].[Store] ([StoreID])
GO
ALTER TABLE [dbo].[ProductDetails] CHECK CONSTRAINT [fk_ProductDetails_Store]
GO
ALTER TABLE [dbo].[Report]  WITH CHECK ADD  CONSTRAINT [fk_Report_Comment] FOREIGN KEY([CommentID])
REFERENCES [dbo].[Comment] ([CommentID])
GO
ALTER TABLE [dbo].[Report] CHECK CONSTRAINT [fk_Report_Comment]
GO
ALTER TABLE [dbo].[Report]  WITH CHECK ADD  CONSTRAINT [fk_Report_UserDetails] FOREIGN KEY([UserID])
REFERENCES [dbo].[UserDetails] ([UserID])
GO
ALTER TABLE [dbo].[Report] CHECK CONSTRAINT [fk_Report_UserDetails]
GO
ALTER TABLE [dbo].[UserDetails]  WITH CHECK ADD  CONSTRAINT [fk_UserDetails_Role] FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([RoleID])
GO
ALTER TABLE [dbo].[UserDetails] CHECK CONSTRAINT [fk_UserDetails_Role]
GO
