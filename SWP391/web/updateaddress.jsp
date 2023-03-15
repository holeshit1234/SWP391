<%-- 
    Document   : updateaddress
    Created on : Feb 24, 2023, 9:56:02 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <link rel="stylesheet" href="asset/css/styleUpdateAdd.css">

        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        <title>JSP Page</title>
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
        </style>
    </head>
    <body>

        <header>
            <div class="logo">
                <img src="asset/images/logo-circle.png">
            </div>
            <div class="menu">
                <li><a href="showProductByGenderServlet?gender=Nam">Nam</a></li>
                <li><a href="showProductByGenderServlet?gender=Nữ">Nữ</a></li>
                <li><a href="showProductByGenderServlet?gender=Unisex">Unisex</a></li>
                <li class="dropdown">
                    <a class="dropbtn">Brand</a>
                    <div class="dropdown-content">
                        <c:forEach var="bl" items="${requestScope.BRAND_RESULT}">
                            <a href="ShowProuductByBrandID?brandID=${bl.getBrandId()}">${bl.getBrandName()}</a>
                        </c:forEach>
                    </div>
                </li>
            </div>
            <div class="orther">
                <li>
                    <form action="SearchServlet">
                        <input placeholder="Search" type="text" name="txtSearch" value=""> <i class="fa fa-search"></i>                        
                    </form>
                </li>


                <c:url var="urlprofile" value="ShowProfileServlet" >
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
                        <jsp:include page="logout.jsp"/>
                    </c:if>
            </div>
        </header>
        <!-- adaddress -->
        <div class="body">
            <div class="container">
                <div class="title">Update address</div>
                <form action="UpdateAddressServlet" method="POST">
                    <c:set var="user" value="${sessionScope.USER}" />
                    <c:if test="${not empty requestScope.ADDRESS}">
                        <c:set var="add" value="${requestScope.ADDRESS}" />
                        <input type="hidden" name="txtaddressid" value="${add.getAddressID()}" />

                        <div class="user-detail">
                            <div class="input-box">
                                <span class="detail">Full name</span>
                                <input type="text" placeholder="Enter your name" name="txtfullName"
                                       value="${user.getFullName()}" disabled="disabled">
                            </div>
                            <div class="input-box">
                                <span class="detail">Phone</span>
                                <input type="number" placeholder="Enter your phone" name="txtPhone" value="${user.getPhone()}"
                                       disabled="disabled">
                            </div>

                            <div class="input-box">
                                <span class="detail">Province</span>
                                <input type="text" placeholder="Enter your province" name="txtProvince"
                                       value="${add.getDistrict()}" required>
                            </div>
                            <div class="input-box">
                                <span class="detail">District</span>
                                <input type="text" placeholder="Enter your district" name="txtDistrict"
                                       value="${add.getDistrict()}" required>
                            </div>
                            <div class="input-box">
                                <span class="detail">Ward</span>
                                <input type="text" placeholder="Enter your ward" name="txtWard" value="${add.getWard()}"
                                       required>
                            </div>
                            <div class="input-box">
                                <span class="detail">Address</span>
                                <input type="text" placeholder="Enter your address" name="txtAddress" value="${add.getStreet()}"
                                       required>
                            </div>
                        </div>

                        <div class="button">
                            <input type="submit" value="Update" name="btAction" />
                        </div>
                    </form>
                </c:if>
            </div>
        </div>
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
        <script src="https://code.jquery.com/jquery-3.6.3.min.js"
        integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
    </body>
</html>
