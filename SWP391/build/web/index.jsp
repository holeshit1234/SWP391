<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>

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

        <link rel="stylesheet" href="asset/css/styleindex.css">

        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/paginationjs/2.1.4/pagination.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                // Listen for changes to the select element
                $('#sort-by').on('change', function () {
                    // Get the selected value
                    var selectedValue = $(this).val();

                    // Sort the product items based on the selected value
                    if (selectedValue === 'price-asc') {
                        $('.product-item').sort(function (a, b) {
                            return parseInt($(a).find('.product-price').text()) - parseInt($(b).find('.product-price').text());
                        }).appendTo('.product-list-container');
                    } else if (selectedValue === 'price-des') {
                        $('.product-item').sort(function (a, b) {
                            return parseInt($(b).find('.product-price').text()) - parseInt($(a).find('.product-price').text());
                        }).appendTo('.product-list-container');
                    } else if (selectedValue === 'name-a-z') {
                        $('.product-item').sort(function (a, b) {
                            return $(a).find('.product-name').text().localeCompare($(b).find('.product-name').text());
                        }).appendTo('.product-list-container');
                    } else if (selectedValue === 'name-z-a') {
                        $('.product-item').sort(function (a, b) {
                            return $(b).find('.product-name').text().localeCompare($(a).find('.product-name').text());
                        }).appendTo('.product-list-container');
                    }
                });
            });
        </script>

        <style>
            #map {
                height: 300px;
                width: 100%;
            }
            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }

            .pagination a {
                color: #555;
                background-color: #fff;
                padding: 8px 16px;
                text-decoration: none;
                border: 1px solid #ddd;
                margin: 0 4px;
                transition: background-color 0.3s ease;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }

            .pagination a.prev, .pagination a.next {
                background-color: #f1f1f1;
                color: #555;
                border: 1px solid #ddd;
            }

            .pagination a.disabled {
                pointer-events: none;
                opacity: 0.6;
            }

            .pagination a i {
                font-size: 16px;
            }

        </style>
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
        
    </head>

    <body>
        <!---------HEADER-------->
        <header>
            <div class="logo">
                <a href="ShowIdexItemServlet"><img src="asset/images/logo-circle.png"></a>
            </div>
            <div class="menu">
              
                <li><a href="SearchServlet">Search Page</a> </li>
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
                    <!--<li> <a href="LogoutAccountServlet">(Logout)</a>  </li>-->
                    <jsp:include page="logout.jsp"/>
                </c:if>
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
                                    <div><img src="asset/images/productpictures/${product.image}"></div>
                                    <div class="product-name"> ${product.getProductName()}</div>
                                    <div class="product-price">${product.getPrice()} vnđ </div>
                                </div>
                            </a>
                        </div>

                    </c:forEach>
                </div>
                <div class="pagination">
                    <c:forEach begin="1" end="${END_PAGE}" var="i">
                        <a href="ShowIdexItemServlet?index=${i}">${i}</a> 
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