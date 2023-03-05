<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SWP team project</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
                  integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
                  crossorigin="anonymous">
        
        <link rel="stylesheet" href="asset/css/ordertracking.css">
        <link rel="stylesheet" href="asset/css/styleindex.css">
        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
    </head>
    <!---------HEADER-------->
<header>
            <div class="logo">
                <a href="ShowIdexItemServlet"><img src="asset/images/logo-circle.png"></a>
            </div>
            <div class="menu">
                <!--        <li><a href="">Male</a>
                            <ul class="sub-menu">
                                <li><a href="">New products</a></li>
                                <li><a href="">Collection</a></li>
                                <li><a href="">Men's shirt</a>
                                    <ul>
                                        <li><a href="">Shirt</a></li>
                                        <li><a href="">T-shirt</a></li>
                                        <li><a href="">Vest</a></li>
                                        <li><a href="">Sweater</a></li>
                                        <li><a href="">Coat</a></li>
                                    </ul>					
                                </li>
                                <li><a href="">Men's pants</a>
                                    <ul>
                                        <li><a href="">Jeans</a></li>
                                        <li><a href="">Short pant</a></li>
                                        <li><a href="">Trouser</a></li>
                                    </ul>					
                                </li>
                            </ul>
                        
                        </li>
                        <li><a href="">Female</a></li>
                        <li><a href="">Children</a></li>
                        <li><a href="">Sale</a></li>
                        <li><a href="">Collection</a></li>
                        <li><a href="">Information</a></li>-->
                <li><a href="SearchServlet">Search Page</a> </li>
            </div>
            <div class="orther">

                <li>
                    <form action="SearchServlet">
                        <input placeholder="Search" type="text" name="txtSearch" value=""> <i class="fa fa-search"></i>                        
                    </form>
                </li>

                <c:url var="urlprofile" value="DispatchController" >
                    <c:param name="btAction" value="show" />
                </c:url>
                <c:if test="${not empty sessionScope.USER}">
                    <li><a class="fa fa-user" href="${urlprofile}" ></a></li>
                    </c:if>

                <c:if test="${empty sessionScope.USER}">
                    <li><a class="fa fa-user" href="login.jsp"></a></li>
                    </c:if>

                <li><a class="fa fa-shopping-bag" href="ViewCartServlet"></a></li>
                    <c:if test="${not empty sessionScope.USER}">
                    <!--<li> <a href="LogoutAccountServlet">(Logout)</a>  </li>-->
                    <jsp:include page="logout.jsp"/>
                </c:if>
            </div>
        </header>
<!---------cart-------->
<div class="cart">

    <div class="container">
        <div class="order-content row">
            <div class="order-content-left" style="display: block;">
                <div class="content-left" style="margin-left: -150px;">
                    <div class="user">
                        <img src="images/296059556_584764606675134_7640748425626229317_n.jpg" alt="">
                        <h3 class="name">Nguyễn Trung Hiếu</h3>
                        <p class="post">Customer</p>
                    </div>
            
                    <nav class="navbar" style="margin-left: 65px;">
                        <ul>
                            <li><a href="#">Account information</a></li>
                            <li><a href="#">Order management</a></li>
                            <li><a href="address.html">Address number</a></li>
                            <li><a href="#">Contact us</a></li>
            
                        </ul>
                    </nav>
                </div>
            </div>
            
            <div class="order-content-right">
                <div class="title" style="display: flex; align-items: center; justify-content: space-between;">
                    <h2 style="font-size: 25px; padding-bottom: 30px;">Order management</h2>
                    <div class="filter" style="margin-bottom: 1.5rem;">
                        <label>Order Status:</label>
                        <select class="form-filter">
                            <option value="">All</option>
                            <option value="">Order Success</option>
                            <option value="">Processing</option>
                            <option value="">Waiting for delivery</option>
                            <option value="">Sent</option>
                            <option value="">Has received the goods</option>
                            <option value="">Cancelled</option>
                            <option value="">Returns</option>
                        </select>
                    </div>
                </div>
                <table>
                    <tr>
                        <th>MÃ ĐƠN HÀNG</th>
                        <th>NGÀY</th>
                        <th>TRẠNG THÁI</th>
                        <th>SẢN PHẨM</th>
                        <th>Total</th>
                    </tr>
                    <tr>
                        <td><p>IVM6207515</p></td>
                        <td><p>28/2/2023</p></td>
                        <td><p style="color: red;">Đang vận chuyển...</p></td>
                        <td>
                            <p>1x Đầm lụa xòe cổ buộc nơ</p>
                            <p>1x Đầm lụa xòe cổ rgdrtrbuộc nơ</p>
                            <p>1x Đầm lụa xòe cổ rgdrtrbuộc nơ</p>
                            <p>1x Đầm lụa xòe cổ rgdrtrbuộc nơ</p>
                        </td>
                        <td><p>400000 <sup>vnd</sup></p></td>
                    </tr>
                </table>
            </div>
        </div>
    </div> 
</div> 
<!---------Footer-------->
<footer>
	<div class="footer-top">
		<li><a href="">Contact</a></li>
		<li><a href="">Recruit</a></li>
		<li><a href="">Introduce</a></li>
		<li>
			<a href="" class="fa fa-facebook"></a>
			<a href="" class="fa fa-twitter"></a>
			<a href="" class="fa fa-youtube"></a>
		</li>
	</div>
	<div class="footer-center">
	<p>
		Contact phone number: 0111111111 <br>
		Registration address: ??????????? <br>
		Order online: <b>022222222</b>
	</p>
	</div>
	<div class="footer-bottom">
		©IVYmoda All rights reserved
	</div>
</footer>

    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>