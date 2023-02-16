<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>SWP team project</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link href="asset/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="asset/css/styleindex.css">

        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
    </head>

    <body>
        <!---------HEADER-------->
        <header>
            <div class="logo">
                <a href="index.jsp"><img src="asset/images/logo-circle.png"></a>
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
                <li><a class="fa fa-user" href="login.jsp"></a></li>
                <li><a class="fa fa-shopping-bag" href=""></a></li>
                <li>
                    <form action="viewCartPage" method="POST">
                        <input type="submit" value="view_your_cart" name="btAction" />
                    </form>
                </li>
            </div>
        </header>
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
                <div class="row">
                    <%--    <div class="cartegory-left">
                        <ul>
                            <li class="cartegory-left-li"><a href="#">Female</a>
                                <ul>
                                    <li><a href="">New arrivals women's goods</a></li>
                                    <li><a href="">Female jeans</a></li>
                                    <li><a href="">Jeans for joy</a></li>
                                </ul>
                            </li>
                            <li class="cartegory-left-li"><a href="#">Male</a>
                                <ul>
                                    <li><a href="">New arrivals men's goods</a></li>
                                    <li><a href="">Male jeans</a></li>
                                    <li><a href="">Jeans for joy</a></li>
                                </ul>
                            </li>
                            <li class="cartegory-left-li"><a href="">Children</a></li>
                            <li class="cartegory-left-li"><a href="">Sale</a></li>
                        </ul>
                    </div>--%>
                    <c:set var="result" value="${requestScope.ITEMS_RESULT}"/>
                    <c:if test="${not empty result}"> 
                        <div class="cartegory-right-content row">
                            <c:forEach var="dto" items="${result}" >
                                <div class="cartegory-right-content-item">
                                    <form action="addToCartController" method="POST">             
                                        <a href="showDetailServlet?pID=${dto.productID}" > <img src="${dto.image}"></a>
                                        <h1><a  href="showDetailServlet?pID=${dto.productID}" >${dto.productName}</a>
                                            <input type="hidden" name="txtProductName" 
                                                   value="${dto.productName}"/>        
                                        </h1>
                                        <h1><a>${dto.price}</a>
                                            <input type="hidden" name="txtPrice"
                                                   value="${dto.price}"/>
                                        </h1>
                                        <input type="text" name="txtQuantity" value="1" />
                                        <input type="submit" value="AddToCart"/> 

                                    </form>
                                </div>
                            </c:forEach>                                
                        </div>
                    </c:if>
                    <c:if test="${ empty result}">  
                        Dang lam sai
                    </c:if>

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
    <script>
        //---------------------sticky-header---------------
        const header = document.querySelector("header")
        window.addEventListener("scroll", function () {
            x = window.pageYOffset
            if (x > 0) {
                header.classList.add("sticky")
            } else {
                header.classList.remove("sticky")
            }
            //console.log(x)
        })

        //---------------------sliderbanner-dotcontroller---------------
        const imgPosition = document.querySelectorAll(".aspect-ratio-169 img")
        const imgContainer = document.querySelector('.aspect-ratio-169')
        const dotItem = document.querySelectorAll(".dot")
        let imgNumber = imgPosition.length
        let index = 0
        //console.log(imgPosition)
        imgPosition.forEach(function (images, index) {
            images.style.left = index * 100 + "%"
            dotItem[index].addEventListener("click", function () {
                slider(index)
            })
        })
        function imgSlide() {
            index++;
            console.log(index)
            if (index >= imgNumber) {
                index = 0
            }
            slider(index)
        }
        function slider(index) {
            imgContainer.style.left = "-" + index * 100 + "%"
            const dotActive = document.querySelector('.active')
            dotActive.classList.remove("active")
            dotItem[index].classList.add("active")
        }
        setInterval(imgSlide, 5000)
    </script>
</html>