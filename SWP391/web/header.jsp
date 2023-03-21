<%-- 
    Document   : header
    Created on : Feb 14, 2023, 6:42:34 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap -->
        <!--<link href="asset/css/bootstrap.min.css" rel="stylesheet">-->

        <link rel="stylesheet" href="asset/css/styleheader.css">


        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">

        <title>JSP Page</title>
    </head>
    <body>

        <style>
            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropbtn {
                /*                padding: 12px 16px;*/
                border: none;
                cursor: pointer;
            }
            .brand-content{
                background-color:#e1c5a7 ;
            }
        </style>
        <header>
            <div class="logo">
                <a href="ShowIdexItemServlet"><img src="asset/images/logo-circle.png"></a>
            </div>
            <jsp:useBean id="brand" class="DHTV.brand.BrandDAO" />
            ${brand.listBrand()}
            <div class="menu">
                <li><a href="showProductByGenderServlet?gender=Nam">Nam</a></li>
                <li><a href="showProductByGenderServlet?gender=Nữ">Nữ</a></li>
                <li><a href="showProductByGenderServlet?gender=Unisex">Unisex</a></li>
                <li class="dropdown">
                    <a class="dropbtn">Brand</a>
                    <div class="dropdown-content">
                        <c:forEach var="bl" items="${brand.getBrandList()}">
                            <a class="brand-content" href="ShowProuductByBrandID?brandID=${bl.getBrandId()}">${bl.getBrandName()}</a>
                        </c:forEach>
                    </div>
                </li>
            </div>
            <jsp:useBean id="daoCart" class="DHTV.cart.CartDAO"/>  
            <div class="orther">

                <li>
                    <form action="SearchServlet">
                        <input placeholder="Search" type="text" name="txtSearch" value=""> <i class="fa fa-search"></i>                        
                    </form>
                </li>

                <c:url var="urlprofile" value="ShowProfileServlet" >
                    <c:param name="btAction" value="show" />
                </c:url>
                <c:if test="${empty sessionScope.USER}">
                    <li><a class="fa fa-user" href="login.jsp" ></a></li>
                    </c:if>


                <c:if test="${not empty sessionScope.USER}">
                    <li><a class="fa fa-user" href="${urlprofile}" ></a></li>
                    </c:if>

                <li>
                    <div style="position: relative; padding-right: 15px;">

                        <a class="fa fa-shopping-bag" href="ViewCartServlet">
                            <c:if test="${not empty sessionScope.USER}">
                                <div style=" position: absolute; right: 0; top:0;">
                                    <sup style="background: red; border-radius: 50%; padding:0 0.3em 0 0.3em; color: #fff;">

                                        ${daoCart.getQuantityCartOfUser(sessionScope.USER.userID)}

                                    </sup>
                                </div>
                            </c:if>
                        </a>

                    </div>
                </li>
                <c:if test="${not empty sessionScope.USER}">
                    <li>
                        <jsp:include page="logout.jsp"/>
                    </li>
                </c:if>
            </div>

        </header>

    </body>
</html>
