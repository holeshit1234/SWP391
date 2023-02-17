<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

    <head>
        <title>SWP team project</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link href="asset/css/bootstrap.min.css" rel="stylesheet">


        <link rel="stylesheet" href="asset/css/styleproduct.css">
        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <!---------HEADER-------->
        <header>
            <div class="logo">
                <a href="ShowIdexItemServlet"><img src="asset/images/logo-circle.png"></a>
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
            </div>
        </header>
        <!---------Banner-slider-------->
        <section>
            <div class="container flex">
                <div class="left">
                    <div class="main-image" id="test">
                        <img id="myImage" src="asset/images/9f272ae3ed5b701cef3826b17993d612.jpg" alt="" class="slide">
                    </div>
                    <div class="option flex">
                        <img src="asset/images/a431fa2ea0f231d48d1586a0faef5454.jpg" onclick="img('asset/images/a431fa2ea0f231d48d1586a0faef5454.jpg')">
                        <img src="asset/images/b2712178ec861468e23277efeb1b800c.jpg" onclick="img('asset/images/b2712178ec861468e23277efeb1b800c.jpg')">
                        <img src="asset/images/f059db41962d4c055e985a7c06dc532d.jpg" onclick="img('asset/images/f059db41962d4c055e985a7c06dc532d.jpg')">
                        <img src="asset/images/a431fa2ea0f231d48d1586a0faef5454.jpg" onclick="img('asset/images/a431fa2ea0f231d48d1586a0faef5454.jpg')">
                        <img src="asset/images/b2712178ec861468e23277efeb1b800c.jpg" onclick="img('asset/images/b2712178ec861468e23277efeb1b800c.jpg')">
                    </div>
                </div>
                <div class="right">
                    <h3>Silk dress</h3>
                    <div class="rating">
                        <div class="eva">
                            <li><a href="">Quantity</a></li>
                            <li><a href="">Sold</a></li>
                            <li><a href="">Comment</a></li>
                            <li><a href="">Report</a></li>
                            <!--
                            MÃ¢u thuáº«n vá»i cÃ¡i sao á» dÆ°á»i
                            <li class="eva-star">
                                    <a href="">Evaluate </a>
                                    <input type="radio" name="rating" id="star5">
                                    <label for="star1"></label>
                                    <input type="radio" name="rating" id="star4">
                                    <label for="star4"></label>
                                    <input type="radio" name="rating" id="star3">
                                    <label for="star3"></label>
                                    <input type="radio" name="rating" id="star2">
                                    <label for="star2"></label>
                                    <input type="radio" name="rating" id="star1">
                                    <label for="star1"></label>
                            </li>
                            -->
                        </div>

                    </div>
                    <h4> <small>$</small>999.99</h4>
                    <p>Thiáº¿t káº¿ Ã¡o Äáº§m dÃ i tay, tÃ¹ng vÃ¡y xáº¿p ly cÃ¡ch kiá»u, Äá» dÃ i qua Äáº§u gá»i xÃ²e thÆ°á»t tha vá»«a giÃºp cÃ¡c nÃ ng che Äi khuyáº¿t Äiá»m, vá»«a nháº¹ nhÃ ng bay bá»ng phÃ¹ há»£p vá»i khÃ´ng khÃ­ mÃ¹a thu ÄÃ´ng. Pháº§n cá» cao thiáº¿t káº¿ kÃ¨m dÃ¢y buá»c nÆ¡ táº¡o kiá»u cho thiáº¿t káº¿ Ã¡o thÃªm yÃªu kiá»u mang phong cÃ¡ch tiá»u thÆ°.</p>
                    <h5>Color-rose Gold</h5>
                    <div class="color flex">
                        <span></span>
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>
                    <h5>Size</h5>
                    <div class="add flex1">
                        <span>S</span>
                        <span>M</span>
                        <span>L</span>
                        <span>XL</span>
                    </div>
                    <h5>Number</h5>
                    <div class="add flex1">
                        <span>-</span>
                        <label>1</label>
                        <span>+</span>
                    </div>
                    <button type="button" onclick="openPopup()">Add to cart</button>
                    <div class="popup" id="popup">
                        <img src="asset/images/404-tick.png">
                        <h2>Thank you!</h2>
                        <p>Your product has been added to the cart.</p>
                        <button type="button" onclick="closePopup()">Continue</button>
                        <!-----onclick="location.href='login.html';"------>
                    </div>
                </div>
            </div>
        </section>
        <script>
            function img(anything) {
                document.querySelector('.slide').src = anything;
            }
            function change(change) {
                const line = document.querySelector('.home');
                line.style.background = change;
            }


//popup------
            let popup = document.getElementById("popup");
            function openPopup() {
                popup.classList.add("open-popup");
            }
            function closePopup() {
                popup.classList.remove("open-popup");
            }


//zoom out image on mousemove() & mouseleave()-------
            var myImage = document.getElementById("myImage");
            myImage.addEventListener("mousemove", function () {
                myImage.classList.remove("zoomOut"); /* remove the zoom-out class */
            });

            myImage.addEventListener("mouseleave", function () {
                myImage.classList.add("zoomOut"); /* add the zoom-out class */
            });
        </script>
        <!---------comment-------->
        <section id="testimonials">
            <div class="testimonial-heading">
                <h1>Product Reviews</h1>
                
                    <form action="CommentServlet" method="GET">
                    <div class="footer-top">
                    <li>
                        <button type="button" class="btn btn-secondary" >
                        All
                        </button>
                    </li>
                    <li>
                        <button type="button" class="btn btn-secondary" >
                        5 <i class="fa fa-star" ></i> (20)
                        </button>
                    </li>
                    <li>
                        <button type="button" class="btn btn-secondary" >
                        4 <i class="fa fa-star" ></i> (15)
                        </button>
                    </li>
                    <li>
                        <button type="button" class="btn btn-secondary" >
                        3 <i class="fa fa-star" ></i> (0)
                        </button>
                    </li>
                    <li>
                        <button type="button" class="btn btn-secondary" >
                        2 <i class="fa fa-star" ></i> (0)
                        </button>
                    </li>
                    <li>
                        <button type="button" class="btn btn-secondary" >
                        1 <i class="fa fa-star" ></i> (0)
                        </button>
                    </li>
                    <li>
                        <button type="button" class="btn btn-secondary" >
                        Gần nhất
                        </button>
                    </li>
                    <li>
                        <button type="submit" class="btn btn-secondary" >
                        Xa nhất
                        </button>
                    </li>
                    </div>
                    </form>
                
            </div>

            <!--testimonials-box-container------------->
            <div class="testimonials-box-container">

                <!--box-1------------->
                <div class="testimonials-box">
                    <div class="box-top">
                        <!--profile------------->
                        <div class="profile">
                            <div class="profile-img">
                                <img src="asset/images/296059556_584764606675134_7640748425626229317_n.jpg" >
                            </div>

                            <div class="name-user">  
                                <form action="CommentServlet" method="GET">

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
                                    <div class="enter-comment">								
                                        <li>

                                            <button type="submit" class="btn btn-light">Submit</button>

                                        </li>				
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>


                <!-----------------------------------------box-2-------------------------------------->
                <c:if test="${not empty sessionScope}">
                    <c:set var="list" value="${sessionScope.INFOCOMMENT}"/>
                    <c:if test="${not empty list}">
                        <c:forEach var="dto" items="${sessionScope.INFOCOMMENT}">
                            <div class="testimonials-box">
                                <div class="box-top">
                                    <!--profile------------->
                                    <div class="profile">
                                        <div class="profile-img">
                                            <img src="asset/images/296059556_584764606675134_7640748425626229317_n.jpg" >
                                        </div>
                                        <div class="name-user">
                                            <strong>UserID = ${dto.getUserID()}</strong>
                                            ${dto.getDate()}
                                            <br>

                                            <p>
                                                Point of this product:  ${dto.getPoint()} <i class="fa fa-star" ></i>
                                            </p>
                                        </div>
                                    </div>
                                    <!--review------------->
                                    <div class="review">
                                        <i class="fa fa-pencil-square"></i>
                                        <i class="fa fa-flag"></i>
                                        <div class="popup" id="popup">
                                            <img src="images/296059556_584764606675134_7640748425626229317_n.jpg">
                                            <p>This user has violated! Do you agree to report.</p>
                                            <button type="button" onclick="openPopupReport()">Agree</button>
                                            <!-----onclick="location.href='login.html';"------>
                                        </div>
                                        <i class="fa fa-trash"></i>
                                    </div>         
                                </div>
                                <!--comment------------->
                                <div class="client-comment">
                                    <p>
                                        ${dto.getDescription()}
                                    </p>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>

                </div>  
            </c:if>
            <c:if test="${not empty sessionScope}">
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