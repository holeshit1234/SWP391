<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>

<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">

    <head>
        <title>SWP team project</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link href="asset/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="asset/css/stylehomepage.css">

        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/paginationjs/2.1.4/pagination.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>       
        <style>

            .product-price{
                color: red;

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
            <!---------HEADER-------->
            
<%--            <header>
                <div class="logo">
                    <a href="ShowIdexItemServlet"><img src="asset/images/logo-circle.png"></a>
                </div>
                <div class="menu">
                    <li><a href="showProductByGenderServlet?gender=Nam">Nam</a></li>
                    <li><a href="showProductByGenderServlet?gender=Nữ">Nữ</a></li>
                    <li><a href="showProductByGenderServlet?gender=Unisex">Unisex</a></li>
                        <%--<li class="dropdown">
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
            </header>-->--%>
                        <jsp:include page="header.jsp"/>
                        
                
            <!---------Banner-slider-------->
            <section id="slider">
                <div class="aspect-ratio-169">
                    <img src="asset/images/banner1.jpg">
                    <img src="asset/images/banner2.jpg">
                    <img src="asset/images/banner3.jpg">
                    <img src="asset/images/banner4.jpg">
                    <img src="asset/images/banner5.jpg">
                </div>
                <div class="dot-container">
                    <div class="dot active"></div>
                    <div class="dot"></div>
                    <div class="dot"></div>
                    <div class="dot"></div>
                    <div class="dot"></div>
                </div>
            </section>
            <!---------Item-------->
            <section class="cartegory">
                <div class="container">            
                    <jsp:useBean id="daoBillDetail" class="DVHT.bill.BillDetailDAO"/> 
                    <jsp:useBean id="daoProductDetail" class="DHTV.product.ProductDetailDAO" />
                    <div class="new-prod">
                        <div class="product-list-header" style="margin-top:45px">

                                <h2 class="category-title" style="text-align:center;">New Arrivals</h2>

                        </div>
                        <div class="product-list-container row">
                            <c:forEach var="product" items="${Product}">

                                <div class="cartegory-right-content-item col-md-3 product-item">
                                    <a href="CommentServlet?txtProductID=${product.productID}">
                                        <div class="item-product">
                                            <div>
                                                <img src="asset/images/productpictures/${product.image}">
                                                <c:if test="${daoProductDetail.isOutOfStock(product.getProductID()) == true}">
                                                    <div style=" position: absolute; right: 10px; top:10px; background-color: rgba(0,0,0,0.3); padding: 10px;">
                                                        <font color='red'>
                                                        OUT OF STOCK !
                                                        </font>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <div class="product-name"> 
                                                <h4>
                                                    ${product.getProductName()}
                                                </h4>

                                            </div>
                                            <div class="product-price">
                                                <fmt:formatNumber value="${product.getPrice()}" pattern="#,###,###" />                                  
                                                vnđ </div>
                                            <div>
                                                Đã bán:${daoBillDetail.getQuantityByProductID(product.getProductID())}
                                            </div>

                                        </div>
                                    </a>
                                </div>

                            </c:forEach>
                        </div>
                    </div>


                    <div class="best-sell-prod">

                        <div class="product-list-header" style="margin-top:20px">

                                <h2 class="category-title" style=" text-align:center;">Best Seller</h2>

                        </div>
                        <div class="product-list-container row">
                            <c:forEach var="product1" items="${Top4}">

                                <div class="cartegory-right-content-item col-md-3 product-item">
                                    <a href="CommentServlet?txtProductID=${product1.productID}">
                                        <div class="item-product ">
                                            <div>
                                                <img src="asset/images/productpictures/${product1.image}">
                                                <c:if test="${daoProductDetail.isOutOfStock(product.getProductID()) == true}">
                                                    <div style=" position: absolute; right: 10px; top:10px; background-color: rgba(0,0,0,0.3); padding: 10px;">
                                                        <font color='red'>
                                                        OUT OF STOCK !
                                                        </font>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <div class="product-name"> 
                                                <h4>
                                                    ${product1.getProductName()}
                                                </h4>

                                            </div>
                                            <div class="product-price">
                                                <fmt:formatNumber value="${product1.getPrice()}" pattern="#,###,###" />                                  
                                                vnđ </div>
                                            <div>
                                                Đã bán:${daoBillDetail.getQuantityByProductID(product.getProductID())}
                                            </div>

                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="centered">
                        <div class="view-all-prod">
                            <form action="ShowALLProduct" method="Post">
                                <button  class="view-prod" style="font-size: 25px">View All Product</button>
                                </form>


                            </div>
                        </div>

                    </div>
                </section>
                <!---------Footer-------->
                <footer>
                    <div class="footer-top">
                        <li><a href="">Contact</a></li>
                        <li><a href="">Recruit</a></li>
                        <li><a href="">Introduce</a></li>
                        <li>
                            <a href="https://www.facebook.com/people/VDTH/100090772202536/" class="fa fa-facebook"></a>
                            <a href="" class="fa fa-twitter"></a>
                            <a href="" class="fa fa-youtube"></a>
                        </li>
                    </div>
                    <div class="footer-center">
                        <p>
                            Contact phone number: 0909990099 <br>
                            Registration address: 512 đường Nguyễn Xiển, Phường Long Thạnh Mỹ, Quận 9 <br>
                            Order online: <b>0988377521</b>
                        </p>
                    </div>
                    <div id="map"></div>
                    <div class="footer-bottom">
                        VHTH All rights reserved
                    </div>
                </footer>



            </body>
            <script src="asset/js/Jsort.js"></script>
            <script src="asset/js/Jindex.js"></script>
            <script>
                function initMap() {
                    var myLatLng = {lat: 10.84142, lng: 106.81004};

                    var map = new google.maps.Map(document.getElementById('map'), {
                        zoom: 15,
                        center: myLatLng
                    });

                    var marker = new google.maps.Marker({
                        position: myLatLng,
                        map: map,
                        title: 'My Location'
                    });
                }
            </script>
            <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDc7PnOq3Hxzq6dxeUVaY8WGLHIePl0swY&callback=initMap"></script>


        </html>