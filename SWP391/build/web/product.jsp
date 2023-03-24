<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">

    <head>
        <title>SWP team project</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link href="http://use.fontawesome.com/releases/v5.0.8/css/all.css" rel="stylesheet">
        <link rel="stylesheet" href="asset/css/styleproduct.css">
        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.6.3.min.js"
        integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>   
        <style>
            .confirm-box {
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                z-index: 9999;
                background-color: white; /* update to white */
                padding: 20px;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.5);
            }


            .button-container {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }

            .ok-button, .cancel-button {
                background-color: #E7D0C4;
                color: #000;
                border: none;
                padding: 10px 20px;
                margin-right: 10px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .ok-button:hover, .cancel-button:hover {
                background-color: #E7C4B2;
            }
            .alert{
                color: black;
            }
        </style>
    </head>

    <body>
        <!---------HEADER-------->
        <jsp:include page="header.jsp"/>
        <!---------Banner-slider-------->
        <section>
            <div class="container flex">
                <jsp:useBean id="daoProduct" class="DHTV.product.ProductDAO" />

                <div class="left">
                    <div class="main-image" >

                        <a href="#popup1">
                            <img   
                                src="asset/images/productpictures/${daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getImage()}"
                                alt="" class="slide">

                            <img   id="myImage"
                                   src="asset/images/productpictures/${daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getImage()}"
                                   alt="" class="slide">
                        </a>

                        <div id="popup1" class="popup">
                            <a href="#" class="close">&times;</a>
                            <img   id="myImage2"
                                   src="asset/images/productpictures/${daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getImage()}"
                                   alt="" class="slide">
                        </div>                     
                    </div>

                </div>
                <jsp:useBean id="dao" class="DVHT.comment.CommentDAO" />
                <jsp:useBean id="daoBrand" class="DHTV.brand.BrandDAO" />
                <jsp:useBean id="daoCategory" class="DHTV.category.CategoryDAO" />
                <jsp:useBean id="daoSize" class="DHTV.size.SizeDAO" />
                <jsp:useBean id="daoProductDetail" class="DHTV.product.ProductDetailDAO" />


                <div class="right">
                    <form action="AddToCartServlet" method="POST">
                        <h3>${daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getProductName()} -
                            <%--
                            <c:set var="avgRate" value="
                                   ${ 
                                   (   (dao.countCommentListByStar(requestScope.PRODUCTID,5)) * 5 +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,4)) * 4 +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,3)) * 3 +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,2)) * 2 +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,1)) * 1 
                                       ) 
                                       /
                                       (   (dao.countCommentListByStar(requestScope.PRODUCTID,5)) +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,4)) +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,3)) +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,2)) +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,1)) 
                                       )
                                   }
                                   " />
                            <jsp:useBean id="daoUtil" class="DVHT.utils.Util" />
                            ${daoUtil.roundingFunction(avgRate)}
                            --%>
                            <jsp:useBean id="daoUtil" class="DVHT.utils.Util" />
                            <c:set var="avgRate" value="${daoUtil.getAvgRate(requestScope.PRODUCTID)}" />

                            <starts-review value="${daoUtil.roundingFunction(avgRate)}" max="5"></starts-review>


                        </h3>

                        <h4> <fmt:formatNumber value="${daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getPrice()}" pattern="#,###,###" />
                            <small>vnd</small>
                        </h4>
                        <input type="hidden" name="txtPrice"
                               value="${daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getPrice()}" />
                        <p> ${daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getDescription()} </p>
                        Brand:
                        <p>
                            ${daoBrand.getInfoBrandByBrandID(daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getBrandID()).getBrandName()}
                        </p>
                        Category:
                        <p>
                            ${daoCategory.getInfoCategoryByCategoryID(daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getCategoryID()).getCategoryName()}
                        </p>
                        Size :
                        <br>
                        <input type="hidden" value=" ${daoProductDetail.getSizeIdById(requestScope.PRODUCTID)}"/>
                        <c:if test="${empty daoProductDetail.getDetailList()}">
                            The product is temporarily out of stock!
                        </c:if>
                        <div class="add flex1">
                            <c:forEach var="dto" items="${daoProductDetail.getDetailList()}">
                                <c:if test="${dto.getQuantity() != 0}">
                                    <input type="radio" name="txtSizeID" value="${dto.getSizeID()}" onchange="showQuantity(this)" data-maxquantity="${dto.getQuantity()}">
                                    <label for="age1">${daoSize.getInfoSizeBySizeID(dto.getSizeID()).getSizeName()}</label>
                                    <input type="hidden" name="maxQuantity" value="${dto.getQuantity()}" />
                                </c:if>
                            </c:forEach>
                        </div>
                        <c:if test="${daoProductDetail.isOutOfStock(requestScope.PRODUCTID) == false}">

                            Số lượng :

                            <div class="quantity-control">
                                <div class="desins">
                                    <button id="btn-di" type="button" onclick="decrementValue()">-</button>
                                </div>
                                <input  id="inputQuantity" type="text" name="txtQuantity" value="1" min="1" max="maxQuantity" step="1" oninput="checkQuantity()">
                                <div class="desins">
                                    <button id="btn-di" type="button" onclick="incrementValue()">+</button>
                                </div>
                            </div>

                            <div style="margin-top: 15px;">
                                <span id="maxQuantity"></span> sản phẩm có sẵn
                            </div>
                        </c:if>
                        <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                        <input type="hidden" name="txtStoreID" value="1" />
                        <input type="hidden" name="txtUserID" value="${requestScope.USERID}" />

                        <div class="enter-comment">

                            <c:if test="${daoProductDetail.isOutOfStock(requestScope.PRODUCTID) == false}">

                                <button type="submit" class="btn btn-secondary">Add to cart</button>
                            </c:if>

                        </div>

                        <c:if test="${not empty requestScope.ADDTOCART}">
                            ${requestScope.ADDTOCART}
                        </c:if>

                        <c:if test="${not empty requestScope.STOCK}">
                            <font color='red'>
                            ${requestScope.STOCK}
                            </font><br />
                        </c:if>
                        <c:if test="${not empty requestScope.NULLSIZE}">
                            <font color='red'>
                            ${requestScope.NULLSIZE}
                            </font><br />
                        </c:if>
                        <script>
                            function showAlertAddToCart() {
                                const addToCart = "${requestScope.ADDTOCART}";
                                if (addToCart != null && addToCart !== '') {
                                    var alertDiv = document.createElement("div");
                                    alertDiv.classList.add("alert");
                                    alertDiv.innerHTML = "Item has been added to your cart.";
                                    document.body.appendChild(alertDiv);
                                    alertDiv.style.display = 'block';
                                    setTimeout(function () {
                                        alertDiv.style.display = 'none';
                                    }, 2000);
                                }
                            }
                        </script>

                    </form>
                </div>
            </div>
        </section>
        <!---------comment-------->
        <section id="testimonials">
            <div class="testimonial-heading">
                <h1>Product Reviews</h1>


                <div class="footer-top">
                    <li>
                        <form action="CommentServlet" method="POST">
                            <button type="submit" class="btn btn-secondary">
                                All
                                (${dao.countCommentListByStar(requestScope.PRODUCTID,5) +
                                   dao.countCommentListByStar(requestScope.PRODUCTID,4) +
                                   dao.countCommentListByStar(requestScope.PRODUCTID,3) +
                                   dao.countCommentListByStar(requestScope.PRODUCTID,2) +
                                   dao.countCommentListByStar(requestScope.PRODUCTID,1)})
                                <input type="hidden" name="star" value="0" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    
                    <li>
                        <form action="CommentServlet" method="POST">
                            <button type="submit" class="btn btn-secondary">
                                Gần nhất
                                <input type="hidden" name="star" value="6" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="POST">
                            <button type="submit" class="btn btn-secondary">
                                Lâu nhất
                                <input type="hidden" name="star" value="7" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="POST">
                            <button type="submit" class="btn btn-secondary">
                                Your comment
                                <input type="hidden" name="star" value="8" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                </div>
            </div>

            <!--testimonials-box-container------------->
            <c:if test="${not empty requestScope.REPORTED}">
                <font color='red'>
                ${requestScope.REPORTED}
                </font>
            </c:if>

            <!--box-1------------->
            <div class="testimonials-box">
                <div class="box-top">
                    <!--profile------------->
                    <div class="profile">
                        <c:if test="${not empty sessionScope.USER}">
                            <c:set var="user" value="${sessionScope.USER}"/>
                            <div class="profile-img">
                                <img src="asset/images/useravatar/${user.getPicture()}">
                            </div>
                        </c:if>
                        <c:if test="${empty sessionScope.USER}">                         
                            <div class="profile-img">
                                <img src="asset/images/useravatar/logo.png">
                            </div>
                        </c:if>
                        <div class="name-user">
                            <c:set var="productID" value="${requestScope.PRODUCTID}" />
                            <%--ProductID = ${productID}--%>
                            <form action="CommentServlet" method="POST">
                                <input type="hidden" name="txtProductID" value="${productID}" />
                                <div class="enter-comment">
                                    <li>
                                        <input placeholder="Enter your comment" type="text" name="txtDescription">
                                    </li>
                                </div>
                                
                                <c:if test="${not empty requestScope.MESSAGE}">
                                    <font color='red'>
                                    ${requestScope.MESSAGE}
                                    </font><br />
                                </c:if>
                                <c:if test="${not empty requestScope.MESSAGE2}">
                                    <font color='red'>
                                    ${requestScope.MESSAGE2}
                                    </font><br />
                                </c:if>
                                <div class="enter-comment">
                                    <li>
                                        <button type="submit" class="btn btn-secondary" name="txtSubmitComment"
                                                value="submit">Submit</button>
                                    </li>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-----------------------------------------box-2-------------------------------------->
            <c:if test="${not empty sessionScope}">
                <c:set var="list" value="${sessionScope.INFOCOMMENT}" />
                <c:if test="${not empty list}">
                    <c:forEach var="dto" items="${sessionScope.INFOCOMMENT}">
                        <div>
                            <div class="col-lg-12">
                                <div class="testimonials-box2">
                                    <div class="box-top">
                                        <!--profile------------->
                                        <div class="profile">
                                            <jsp:useBean id="daoUser" class="DVHT.userdetails.UserDetailsDAO" />
                                            <div class="profile-img">
                                                <img
                                                    src="asset/images/useravatar/${daoUser.getInfoUser(dto.getUserID()).getPicture()}">
                                            </div>
                                            <div class="name-user">

                                                <strong>${daoUser.getInfoUser(dto.getUserID()).getFullName()}</strong>
                                                ${dto.getDate()}
                                                <br>

                                                <p>
                                                    Point of this product: ${dto.getPoint()} <i
                                                        class="fa fa-star"></i>
                                                </p>
                                            </div>
                                        </div>
                                        <!--review------------->
                                        <c:set var="user" value="${sessionScope.USER}" />
                                        <div class="review">
                                            <c:set var="cmtID" value="${dto.getCommentID()}" />

                                            <!------------------------------------------->
                                            <!-- The modal delete----------------------------------->
                                            <c:if test="${user.getUserID() == dto.getUserID()}">
                                                <c:url var="deleteURL" value="DeleteCommentServlet">
                                                    <c:param name="txtCommentID" value="${cmtID}" />
                                                    <c:param name="txtProductID"
                                                             value="${requestScope.PRODUCTID}" />
                                                    <c:param name="txtUserID" value="${user.getUserID()}" />
                                                </c:url>
                                                <button type="button" onclick="confirmDelete('${deleteURL}')">
                                                    <a class="fa fa-trash"></a>
                                                </button>
                                            </c:if>
                                            <!------------------------------------------->
                                            <!-- The modal flag----------------------------------->
                                            <c:if test="${user.getUserID() != dto.getUserID()}">
                                                <c:url var="flagURL" value="FlagCommentServlet">
                                                    <c:param name="txtCommentID" value="${cmtID}" />
                                                    <c:param name="txtProductID"
                                                             value="${requestScope.PRODUCTID}" />
                                                    <c:param name="txtUserID" value="${user.getUserID()}" />
                                                </c:url>
                                                <button type="button" onclick="confirmFlag('${flagURL}')">
                                                    <a class="fa fa-flag"></a>
                                                </button>
                                            </c:if>
                                            <!------------------------------------------->
                                            <!-- The modal edit----------------------------------->
                                            <c:if test="${user.getUserID() == dto.getUserID()}">
                                                <c:url var="editURL" value="EditCommentServlet">
                                                    <c:param name="txtCommentID" value="${cmtID}" />
                                                    <c:param name="txtProductID"
                                                             value="${requestScope.PRODUCTID}" />
                                                    <c:param name="txtUserID" value="${user.getUserID()}" />
                                                </c:url>
                                                <button type="button" onclick="confirmEdit('${editURL}')">
                                                    <a class="fa fa-pencil-square"></a>
                                                </button>
                                            </c:if>
                                        </div>
                                    </div>
                                    <!--comment------------->
                                    <div class="client-comment">

                                        <p>
                                            ${dto.getDescription()}
                                        </p>

                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>

                    <button class="btn center-block loadMore">
                        Load more
                    </button>

                    <script src="asset/js/jquery-latest.min.js"></script>

                </c:if>


            </c:if>
            <c:if test="${empty sessionScope.INFOCOMMENT}">
                <p>Do not have any review</p>
            </c:if>
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


        <script>
                                                    window.onload = function () {
                                                        showAlertAddToCart();
                                                    };
        </script>

        <script src="asset/js/JProduct.js"></script>
        <script src="asset/js/desins.js"></script>
    </body>

</html>