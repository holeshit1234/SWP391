<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <title>SWP team project</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
          crossorigin="anonymous">

    <link rel="stylesheet" href="asset/css/codeproduct.css">

    <link rel="shortcut icon" href="asset/images/logo.png">
    <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<!-------Header---------->
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
<!----code------>
<div class="body">
    <div class="container">
        <div class="cart-icon">
            <i class="fa fa-check-circle-o "></i>
        </div>
        <div class="title-content">
            <h2>Cám ơn bạn đã mua hàng.</h2>
            <br>
        </div>
        <div class="content">
            <p>Cảm ơn quý khách hàng đã tin tưởng và mua sản phẩm của <strong>VDTH Shop</strong>. Chúng tôi sẽ luôn cố gắng để đáp ứng và vượt qua sự mong đợi của quý khách hàng. </p>
            <p> Rất mong được phục vụ quý khách hàng trong những lần mua sắm tiếp theo.</p>
        </div>
        <div class="button">
            <a class="btn " href="ShowIdexItemServlet">Tiếp tục mua sắm</a>
            <a class="btn" href="">Theo dõi đơn hàng</a>
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
        Â©IVYmoda All rights reserved
    </div>
</footer>




<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>