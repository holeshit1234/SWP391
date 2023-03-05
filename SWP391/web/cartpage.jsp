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
    </head>
    <!---------HEADER-------->
    <header>
        <div class="logo">
            <img src="asset/images/logo-circle.png">
        </div>
        <div class="menu">
            <li><a href="">Male</a>
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
            <li><a href="">Information</a></li>
        </div>
        <div class="orther">
            <li><input placeholder="Search" type="text"><i class="fa fa-search"></i></li>
            <li><a class="fa fa-user" href="login.html"></a></li>
            <li><a class="fa fa-shopping-bag" href=""></a></li>
        </div>
    </header>
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
                            <th>Color</th>
                            <th>Size</th>
                            <th>Number</th>
                            <th>Total</th>
                            <th>Delete</th>
                        </tr>
                        


                            
                        <c:if test="${not empty requestScope.CART_RESULT}">
                            <jsp:useBean id="daoProduct" class="DHTV.product.ProductDAO"/>  
                            <c:forEach var="dto" items="${requestScope.CART_RESULT}" >
                                <tr>
                                <td><img src="${daoProduct.getInfoProductByProductID(dto.getProductID()).getImage()}" alt="" srcset=""></td>
                                <td>${daoProduct.getInfoProductByProductID(dto.getProductID()).getProductName()}</td>
                                <td><img src="images/Screenshot 2023-02-22 203724.png" alt=""></td>
                                <td><p>L</p></td>
                                <td><input type="number" value="1" min="1"></td>
                                <td><p>400000 <sup>vnd</sup></p></td>
                                <td><i class="fa fa-trash"></i></td>
                                </tr>
                            </c:forEach> 
                        </c:if>
                        
                    </table>
                </div>
                <div class="cart-content-right">
                    <table>
                        <tr>
                            <th colspan="2">TÃ´Ìng tiÃªÌn saÌn phÃ¢Ìm</th>
                        </tr>
                        <tr>
                            <td>TÃ´Ìng saÌn phÃ¢Ìm</td>
                            <td>2</td>
                        </tr>
                        <tr>
                            <td>TÃ´Ìng tiÃªÌn haÌng</td>
                            <td><p>400000<sup>vnd</sup></p></td>
                        </tr>
                        <tr>
                            <td>ThaÌnh tiÃªÌn</td>
                            <td><p>400000<sup>vnd</sup></p></td>
                        </tr>
                        <tr>
                            <td>TaÌ£m tiÌnh</td>
                            <td><p style="color: black; font-weight: bold;">400000<sup>vnd</sup></p></td>
                        </tr>
                    </table>
                    <div class="cart-content-right-buttom">
                        <button>TÃªÌp tuÌ£c mua haÌng</button>
                        <button>Thanh toaÌn</button>
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

    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>