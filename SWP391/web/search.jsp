<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="DHTV.product.ProductImgDAO"%>
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
        <style>
            .pagination-containers {
                text-align: center;
            }

            .paginationjs {
                margin: 20px 0;
            }

            .paginationjs-pages ul {
                list-style: none;
                display: inline-flex;
                justify-content: center;
                align-items: center;
            }

            .paginationjs-page {
                padding: 0 10px;
                font-size: 14px;
            }

            .paginationjs-page.active {
                background-color: #337ab7;
                color: #fff;
            }

            .paginationjs-page:hover {
                cursor: pointer;
                background-color: #f5f5f5;
            }

            .paginationjs-page {
                font-size: 2.0rem;
                padding: 5px 10px;
            }

            .paginationjs-prev a,
            .paginationjs-next a {
                display: inline-block;
                padding: 0 10px;
                font-size: 14px;
                color: #337ab7;
            }

            .paginationjs-prev.disabled a,
            .paginationjs-next.disabled a {
                color: #ccc;
                pointer-events: none;
            }

        </style>
        <script>
            $(document).ready(function () {
                var dataSource = [];
            <%
                try {
                    // Assuming that your servlet stores the JSON data as a string in a variable called jsonData
                    String jsonData = (String) request.getAttribute("products");
                    System.out.println(jsonData);
                    JSONArray jsonArray = new JSONArray(jsonData);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Object element = jsonArray.get(i);
                        if (element instanceof String) {
                            String item = (String) element;
                            out.print("dataSource.push('" + item + "');\n");
                        } else if (element instanceof Number) {
                            Number item = (Number) element;
                            out.print("dataSource.push(" + item.toString() + ");\n");
                        } else if (element instanceof JSONObject) {
                            JSONObject obj = (JSONObject) element;
                            out.print("dataSource.push(" + obj.toString(2) + ");\n");
                        }
                    }
                } catch (JSONException ex) {
                    out.println("Error: " + ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            %>
                var dataContainer = $('#data-container');
                var options = {
                    dataSource: dataSource,
                    pageSize: 8,
                    callback: function (data, pagination) {
                        var html = '';

                        for (var i = 0; i < data.length; i++) {
                            if (i % 4 === 0) {
                                html += '<div class="row">';
                            }
                            html += '<div class="cartegory-right-content-item col-md-4">'
                                    + '<div class="item-product">'
                                    + '<form action="DispatchController" method="POST">'
                                    + '<input type="hidden" name="txtProductID" value="' + JSON.stringify(data[i].productID) + '" />'
                                    + '<img src="' + JSON.stringify(data[i].image).replace(/^"(.*)"$/, '$1') + '"/>'
                                    + '<div>' + JSON.stringify(data[i].productName).replace(/^"(.*)"$/, '$1') + '</div>'
                                    + '<div>' + JSON.stringify(data[i].price) + '</div>'
                                    + '<input type="submit" value="Detail" class="btn" name="btAction"/>'
                                    + '</form>'
                                    + '</div>'
                                    + '</div>';

                            if (i % 4 === 3 || i === data.length - 1) {
                                html += '</div>';
                            }
                        }
                        dataContainer.html(html);
                    },
                    // attach CSS classes to the UL and LI elements
                    paginationClass: 'paginationjs',
                    activeClassName: 'active',
                    prevClass: 'prev',
                    nextClass: 'next'
                };
                $('#pagination-container').pagination(options);
            });
        </script>

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
                        <input placeholder="Search" type="text" name="txtSearch" value=""> <i class="fa fa-search"></i>                        
                    </form>
                </li>

                <c:url var="urlprofile" value="DispatchController" >
                    <c:param name="btAction" value="show" />
                </c:url>
                <c:if test="${not empty sessionScope.USER}">
                    <li><a class="fa fa-user" href="${urlprofile}" ></a></li>
                    </c:if>

                <c:if test="${empty sessionScope.USER}">
                    <li><a class="fa fa-user" href="login.jsp"></a></li>
                    </c:if>

                <li><a class="fa fa-shopping-bag" href=""></a></li>
                    <c:if test="${not empty sessionScope.USER}">
                    <!--<li> <a href="LogoutAccountServlet">(Logout)</a>  </li>-->
                    <jsp:include page="logout.jsp"/>
                </c:if>
            </div>
        </header>

        <!---------Item-------->
        <section class="cartegory">
            <div class="container">
                <!--                <div class=" row">
                
                
                                    <div class="cartegory-right-top-item">
                                        <form action="SearchServlet">
                                            <div class="row">
                                                <div  class="col-md-12">
                                                    <input type="text" placeholder="Search" class="form-control">
                                                </div>                                
                                            </div>
                                        </form>
                
                                    </div>
                                    <div class="cartegory-right-top-item">
                                        <button><span>Filter</span><i class="fa fa-sort-down"></i></button>
                                    </div>
                                    <div class="cartegory-right-top-item">
                                        <select name="" id="">
                                            <option value="">Arrange</option>
                                            <option value="">High to low price</option>
                                            <option value="">Low to high price</option>
                                        </select>
                                    </div>
                
                                </div>-->



                <div id="data-container"></div>
                <div class="pagination-containers" id="pagination-container"></div>





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