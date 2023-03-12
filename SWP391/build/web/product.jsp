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
        <link rel="stylesheet" href="asset/css/styleproduct.css">
        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        <style>
            .confirm-box {
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: white;
                border: 1px solid black;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
                z-index: 9999;
                max-width: 500px;
                width: 100%;
            }

            .confirm-box p {
                margin-top: 0;
            }

            .button-container {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }

            .ok-button,
            .cancel-button {
                padding: 10px 20px;
                border-radius: 5px;
                border: none;
                cursor: pointer;
            }

            .ok-button {
                background-color: #EEDBD0;
                color: black;
                margin-right: 10px;
            }

            .cancel-button {
                background-color: #E7D0C4;
                color: black;
            }

            .ok-button:hover,
            .cancel-button:hover {
                background-color: #D5C0A7;
            }

            .confirm-box input[type="text"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
                margin-top: 10px;
            }

            .confirm-box input[type="text"]:focus {
                border-color: #66afe9;
                outline: 0;
                box-shadow: 0 0 10px rgba(102, 175, 233, .6);

            }

            .alert {
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                padding: 20px;
                background-color: #f44336;
                color: white;
                font-size: 18px;
            }
        </style>
    </head>

    <body>
        <!---------HEADER-------->
        <header>
            <div class="logo">
                <a href="ShowIdexItemServlet"><img src="asset/images/logo-circle.png"></a>
            </div>
            <div class="menu">
                <li><a href="showProductByGenderServlet?gender=Nam">Nam</a></li>
                <li><a href="showProductByGenderServlet?gender=Nữ">Nữ</a></li>
                <li><a href="showProductByGenderServlet?gender=Unisex">Unisex</a></li>
            </div>
            <div class="orther">

                <li>
                    <form action="SearchServlet">
                        <input placeholder="Search" type="text" name="txtSearch" value=""> <i
                            class="fa fa-search"></i>
                    </form>
                </li>

                <c:url var="urlprofile" value="ShowProfileServlet">
                    <c:param name="btAction" value="show" />
                </c:url>
                <c:if test="${not empty sessionScope.USER}">
                    <li><a class="fa fa-user" href="${urlprofile}"></a></li>
                    </c:if>

                <c:if test="${empty sessionScope.USER}">
                    <li><a class="fa fa-user" href="login.jsp"></a></li>
                    </c:if>

                <li><a class="fa fa-shopping-bag" href="ViewCartServlet"></a></li>
                    <c:if test="${not empty sessionScope.USER}">
                    <!--<li> <a href="LogoutAccountServlet">(Logout)</a>  </li>-->
                    <jsp:include page="logout.jsp" />
                </c:if>
            </div>
        </header>
        <!---------Banner-slider-------->
        <section>
            <div class="container flex">
                <jsp:useBean id="daoProduct" class="DHTV.product.ProductDAO" />

                <div class="left">
                    <div class="main-image" id="test">
                        <img id="myImage" class="center"
                             src="asset/images/productpictures/${daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getImage()}"
                             alt="" class="slide">
                    </div>

                </div>
                <jsp:useBean id="dao" class="DVHT.comment.CommentDAO" />
                <jsp:useBean id="daoBrand" class="DHTV.brand.BrandDAO" />
                <jsp:useBean id="daoCategory" class="DHTV.category.CategoryDAO" />
                <jsp:useBean id="daoSize" class="DHTV.size.SizeDAO" />
                <jsp:useBean id="daoProductDetail" class="DHTV.product.ProductDetailDAO" />


                <div class="right">
                    <form action="AddToCartServlet" method="GET">
                        <h3>${daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getProductName()} -
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
                            <i class="fa fa-star"></i>
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
                        ${daoProductDetail.showSizeListByProductID(requestScope.PRODUCTID)}
                        <c:if test="${empty daoProductDetail.getSizeList()}">
                            The product is temporarily out of stock!
                        </c:if>
                        <div class="add flex1">
                            <c:forEach var="dto" items="${daoProductDetail.getSizeList()}">
                                <input type="radio" name="txtSizeID" value="${dto.getSizeID()}">
                                <label
                                    for="age1">${daoSize.getInfoSizeBySizeID(dto.getSizeID()).getSizeName()}</label><br>
                            </c:forEach>
                        </div>
                        <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                        <input type="hidden" name="txtStoreID" value="1" />
                        <input type="hidden" name="txtUserID" value="${requestScope.USERID}" />

                        <div class="enter-comment">
                            <button type="submit" class="btn btn-secondary">Add to cart</button>
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
                        <form action="CommentServlet" method="GET">
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
                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary">
                                5 <i class="fa fa-star"></i>

                                (${dao.countCommentListByStar(requestScope.PRODUCTID,5)})
                                <input type="hidden" name="star" value="5" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>

                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary">
                                4 <i class="fa fa-star"></i>

                                (${dao.countCommentListByStar(requestScope.PRODUCTID,4)})
                                <input type="hidden" name="star" value="4" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary">
                                3 <i class="fa fa-star"></i>

                                (${dao.countCommentListByStar(requestScope.PRODUCTID,3)})
                                <input type="hidden" name="star" value="3" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary">
                                2 <i class="fa fa-star"></i>

                                (${dao.countCommentListByStar(requestScope.PRODUCTID,2)})
                                <input type="hidden" name="star" value="2" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary">
                                1 <i class="fa fa-star"></i>

                                (${dao.countCommentListByStar(requestScope.PRODUCTID,1)})
                                <input type="hidden" name="star" value="1" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary">
                                Gần nhất
                                <input type="hidden" name="star" value="6" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary">
                                Lâu nhất
                                <input type="hidden" name="star" value="7" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="GET">
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
                        <div class="profile-img">
                            <img src="asset/images/296059556_584764606675134_7640748425626229317_n.jpg">
                        </div>

                        <div class="name-user">
                            <c:set var="productID" value="${requestScope.PRODUCTID}" />
                            ProductID = ${productID}
                            <form action="CommentServlet" method="GET">
                                <input type="hidden" name="txtProductID" value="${productID}" />
                                <div class="enter-comment">
                                    <li>
                                        <input placeholder="Enter your comment" type="text" name="txtDescription">

                                    </li>
                                </div>
                                <div class="rating-cmt">
                                    <div class="eva-cmt">

                                        <li class="eva-star-cmt">

                                            <input type="radio" name="rating" value="5" id="star5">
                                            <label for="star5"></label>
                                            <input type="radio" name="rating" value="4" id="star4">
                                            <label for="star4"></label>
                                            <input type="radio" name="rating" value="3" id="star3">
                                            <label for="star3"></label>
                                            <input type="radio" name="rating" value="2" id="star2">
                                            <label for="star2"></label>
                                            <input type="radio" name="rating" value="1" id="star1">
                                            <label for="star1"></label>
                                        </li>
                                    </div>
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
                                            <div class="profile-img">
                                                <img
                                                    src="asset/images/296059556_584764606675134_7640748425626229317_n.jpg">
                                            </div>
                                            <div class="name-user">
                                                <jsp:useBean id="daoUser" class="DVHT.userdetails.UserDetailsDAO" />
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
                                                    $(".col-lg-12").slice(0, 3).show();
                                                    $(".loadMore").on("click", function () {
                                                        $(".col-lg-12:hidden").slice(0, 3).show();
                                                        if ($(".col-lg-12:hidden").length == 0) {
                                                            $(".loadMore").fadeOut();
                                                        }
                                                    })

        </script>
        <script>
            function confirmDelete(url) {
                var confirmBox = document.createElement('div');
                confirmBox.classList.add('confirm-box');
                confirmBox.classList.add('alert'); // Add alert class


                var message = document.createElement('p');
                message.innerHTML = 'Are you sure you want to delete this comment?';
                confirmBox.appendChild(message);

                var buttons = document.createElement('div');
                buttons.classList.add('button-container'); // Add button container class

                var okButton = document.createElement('button');
                okButton.innerHTML = 'OK';
                okButton.classList.add('ok-button'); // Add OK button class
                okButton.addEventListener('click', function () {
                    window.location.href = url;
                    confirmBox.parentNode.removeChild(confirmBox);
                });
                buttons.appendChild(okButton);

                var cancelButton = document.createElement('button');
                cancelButton.innerHTML = 'Cancel';
                cancelButton.classList.add('cancel-button'); // Add cancel button class
                cancelButton.addEventListener('click', function () {
                    confirmBox.parentNode.removeChild(confirmBox);
                });
                buttons.appendChild(cancelButton);

                confirmBox.appendChild(buttons);

                document.body.appendChild(confirmBox);
            }

            function confirmFlag(url) {
                var confirmBox = document.createElement('div');
                confirmBox.classList.add('confirm-box');
                confirmBox.classList.add('alert'); // Add alert class

                var message = document.createElement('p');
                message.innerHTML = 'Please tell us the reason you report this comment.';
                confirmBox.appendChild(message);

                var inputField = document.createElement('input');
                inputField.type = 'text';
                inputField.name = 'reason';
                inputField.placeholder = 'Enter reason for reporting...';
                confirmBox.appendChild(inputField);

                var buttons = document.createElement('div');
                buttons.classList.add('button-container'); // Add button container class

                var okButton = document.createElement('button');
                okButton.innerHTML = 'OK';
                okButton.classList.add('ok-button'); // Add OK button class
                okButton.addEventListener('click', function () {
                    var reason = inputField.value;
                    window.location.href = url + '&reason=' + encodeURIComponent(reason);
                    confirmBox.parentNode.removeChild(confirmBox);
                });
                buttons.appendChild(okButton);

                var cancelButton = document.createElement('button');
                cancelButton.innerHTML = 'Cancel';
                cancelButton.classList.add('cancel-button'); // Add cancel button class
                cancelButton.addEventListener('click', function () {
                    confirmBox.parentNode.removeChild(confirmBox);
                });
                buttons.appendChild(cancelButton);

                confirmBox.appendChild(buttons);

                document.body.appendChild(confirmBox);
            }
            function confirmEdit(url) {
                var confirmBox = document.createElement('div');
                confirmBox.classList.add('confirm-box');
                confirmBox.classList.add('alert'); // Add alert class

                var message = document.createElement('p');
                message.innerHTML = 'Are you sure you want to edit this item?';
                confirmBox.appendChild(message);

                var inputField = document.createElement('input');
                inputField.type = 'text';
                inputField.name = 'description';
                inputField.placeholder = 'Enter new description...';
                confirmBox.appendChild(inputField);

                var buttons = document.createElement('div');
                buttons.classList.add('button-container'); // Add button container class

                var okButton = document.createElement('button');
                okButton.innerHTML = 'OK';
                okButton.classList.add('ok-button'); // Add OK button class
                okButton.addEventListener('click', function () {
                    var description = inputField.value;
                    window.location.href = url + '&description=' + encodeURIComponent(description);
                    confirmBox.parentNode.removeChild(confirmBox);
                });
                buttons.appendChild(okButton);

                var cancelButton = document.createElement('button');
                cancelButton.innerHTML = 'Cancel';
                cancelButton.classList.add('cancel-button'); // Add cancel button class
                cancelButton.addEventListener('click', function () {
                    confirmBox.parentNode.removeChild(confirmBox);
                });
                buttons.appendChild(cancelButton);

                confirmBox.appendChild(buttons);

                document.body.appendChild(confirmBox);
            }
        </script>
        <script>
            window.onload = function () {
                showAlertAddToCart();
            };
        </script>

    </body>

</html>