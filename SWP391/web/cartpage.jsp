<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>SWP team project</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
              crossorigin="anonymous">

        <link rel="stylesheet" href="asset/css/catpage.css">

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
                box-shadow: 0 0 10px rgba(102,175,233,.6);
            }

        </style>
    </head>
    <!---------HEADER-------->
    <jsp:include page="header.jsp"/>
    <!---------cart-------->
    <div class="cart">
        <div class="container">
            <div class="cart-top-wrap">
                <div class="cart-top">
                    <div class="cart-top-cart cart-top-item">
                        <i class="fa fa-shopping-cart"></i>
                    </div>
                    <div class="cart-top-address cart-top-item">
                        <i class="fa fa-map"></i>
                    </div>
                    <div class="cart-top-payment cart-top-item">
                        <i class="fa fa-money"></i>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="cart-content row">
                <div class="cart-content-left">
                    <table>
                        <tr>
                            <th>Product</th>
                            <th>Name product</th>              
                            <th>Size</th>
                            <th>Number</th>
                            <th>Price</th>
                            <th>Delete</th>
                        </tr>

                        <c:if test="${not empty requestScope.CART_RESULT}">
                            <jsp:useBean id="daoProduct" class="DHTV.product.ProductDAO"/>  
                            <jsp:useBean id="daoSize" class="DHTV.size.SizeDAO"/>  
                            <c:forEach var="dto" items="${requestScope.CART_RESULT}" >
                                <tr>
                                    <td><img src="asset/images/productpictures/${daoProduct.getInfoProductByProductID(dto.getProductID()).getImage()}" alt="" srcset=""></td>
                                    <td>${daoProduct.getInfoProductByProductID(dto.getProductID()).getProductName()}</td>
                                    <td><p>${daoSize.getInfoSizeBySizeID(dto.getSizeID()).getSizeName()}</p></td>
                                    <td>
                                        <div class="your-cart-body-left-product-detail-left-count">
                                            <a class="your-cart-body-left-product-detail-left-minus" 
                                               href=" UpdateCartServlet?action=des&cartId=${dto.getCartID()}&productId=${dto.getProductID()}">-</a>
                                            <span class="your-cart-body-left-product-detail-left-count-update" 
                                                  id="quantity${dto.getProductID()}">${dto.getQuantity()}</span>
                                            <a class="your-cart-body-left-product-detail-left-plus" 
                                               href="UpdateCartServlet?action=inc&cartId=${dto.getCartID()}&productId=${dto.getProductID()}">+</a>
                                        </div>
                                    </td>
                                    <td>
                                <fmt:formatNumber var="price" value="${dto.getPrice()}" pattern="#,###"/>
                                <span id="price">${dto.getPrice()}</span>₫
                                </td> 
                                <td>
                                    <c:url var="cartURL" value="DeleteCartServlet">
                                        <c:param name="txtCartID" value="${dto.getCartID()}" />
                                    </c:url>

                                    <button type="button" onclick="confirmDeleteProductInCart('${cartURL}')">
                                        <a class="fa fa-trash"></a>
                                    </button>
                                </td>
                                </tr>
                            </c:forEach>
                        </c:if>                        

                    </table>
                </div>

                <div class="cart-content-right">
                    <table>

                        <tr>
                            <c:if test="${empty requestScope.CART_RESULT}">

                                <th colspan="2">Your cart is empty !</th>                                

                            </c:if>
                            <c:if test="${not empty requestScope.CART_RESULT}">
                                <th colspan="2">Tóm tắt giỏ hàng</th>
                                </c:if>
                        </tr>
                        <tr>
                            <c:if test="${not empty requestScope.QUANTITIES}">
                                <c:set var="quantity" value="${requestScope.QUANTITIES}"/>
                                <td>Tổng số lượng sản phẩm</td>
                                <td>${quantity}</td>                               
                            </c:if>
                        </tr>
                        <tr>
                            <c:if test="${not empty requestScope.TOTAL_PRICE}">
                                <c:set var="total" value="${requestScope.TOTAL_PRICE}"/>
                                <td>Tổng phí thanh toán</td>
                                <td><p>${total}<sup>vnd</sup></p></td>
                            </c:if>
                        </tr>
                        <tr>
                            <td>Phí shipping</td>
                            <td><p >30000<sup>vnd</sup></p></td>
                        </tr>
                        <tr>
                            <c:if test="${not empty requestScope.TOTAL_PRICE}">
                                <c:set var="total" value="${requestScope.TOTAL_PRICE}"/>
                                <td>Tạm tính</td>
                                <td><p id="total-price" style="color: black; font-weight: bold;">${total+30000}<sup>vnd</sup></p></td>
                            </c:if>
                        </tr>
                    </table>
                    <div class="cart-content-right-buttom row">
                        <div class="col-md-6" >
                            <button onclick="submitForm()" >Quay lại mua hàng</button>
                        </div>

                        <div class="col-md-6">
                            <form action="CheckOutServlet">
                                <button>Thanh toán</button>
                            </form>
                        </div>                      
                    </div>
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
            Â©IVYmoda All rights reserved
        </div>
    </footer>

    <form id="my-form" action="ShowIdexItemServlet" method="post">
    </form>
    <script>
        function submitForm() {
            document.getElementById("my-form").submit();
        }
    </script>
    <script>
        function confirmDeleteProductInCart(url) {
            var confirmBox = document.createElement('div');
            confirmBox.classList.add('confirm-box');
            confirmBox.classList.add('alert'); // Add alert class


            var message = document.createElement('p');
            message.innerHTML = 'Are you sure you want to delete this product?';
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
    </script>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script>
        window.onload = function () {
            showAlertFullItem();
            emptyCart();
        };
    </script>
    <script>
        function showAlertFullItem() {
            const addToCart = "${requestScope.FULL}";
            if (addToCart != null && addToCart !== '') {
                alert('You have taken all the products in the store.');
            }
        }
    </script>
    <script>
        function emptyCart() {
            const addToCart = "${requestScope.EMPTYCART}";
            if (addToCart != null && addToCart !== '') {
                alert('You must have some products to take the next step.');
            }
        }
    </script>
</html>