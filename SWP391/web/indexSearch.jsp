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

        <link rel="stylesheet" href="asset/css/styleForAllIndex.css">

        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/paginationjs/2.1.4/pagination.min.js"></script>      
               
    </head>

    <body>
        <!---------HEADER-------->
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
            <div class="orther">

                <li>
                    <form action="SearchServlet">
                        <input placeholder="Search" type="text" name="txtSearch" value="${param.txtSearch}"> <i class="fa fa-search"></i>                                                                                 
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

        <!---------Item-------->
        <jsp:useBean id="daoProductDetail" class="DHTV.product.ProductDetailDAO" />
        <section class="cartegory" style="margin-top: 150px">
            <div class="container">

                <div class="product-list-header">
                    <h2 class="category-title">All products</h2>
                    <div class="category-sort-cotainer">
                        <select id="sort-by" class="category-sort-options">
                            <option value="price-asc">Ascending Price</option>
                            <option value="price-des">Descending Price</option>
                            <option value="name-a-z">Name: A - Z</option>
                            <option value="name-z-a">Name: Z - A</option>
                        </select>
                    </div>
                </div>

                <div class="product-list-container row">
                    <c:forEach var="product" items="${PAGING_RESULT}">

                        <div class="cartegory-right-content-item col-md-3 product-item">
                            <a href="CommentServlet?txtProductID=${product.productID}">
                                <div class="item-product ">
                                    <div>
                                        <img src="asset/images/productpictures/${product.image}">
                                        <c:if test="${daoProductDetail.isOutOfStock(product.getProductID()) == true}">
                                            <div style=" position: absolute; right: 10px; top:10px; background-color: rgba(0,0,0,0.3); padding: 10px;">
                                                <font color='red'>
                                                OUT OF STOCK !
                                                </font>
                                            </div>
                                        </c:if>F
                                    </div>
                                    <div class="product-name"> ${product.getProductName()}</div>
                                    <div class="product-price">
                                        <fmt:formatNumber value="${product.getPrice()}" pattern="#,###,###" />                                  
                                        vnđ 
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
                <div class="pagination">
                    <c:forEach begin="1" end="${END_PAGE}" var="i">
                        <a href="SearchServlet?index=${i}&txtSearch=${param.txtSearch}">${i}</a> 
                    </c:forEach>
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



    </body>
    <script src="asset/js/Jsort.js"></script>
    <script src="asset/js/Jindex.js"></script>
</html>