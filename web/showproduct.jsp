<%-- 
    Document   : showproduct
    Created on : Feb 22, 2023, 6:40:47 AM
    Author     : User
--%>

<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="asset/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="asset/css/styleindex.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/paginationjs/2.1.4/pagination.min.js"></script>
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
//                                    if (jsonArray.get(i) instanceof String) {
//                                        String item = jsonArray.getString(i);
//                                        out.print("dataSource.push('" + item + "');\n");
//                                    }
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
                            // Handle object element
                            System.out.println(obj.toString());
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
                            html += '<div class="cartegory-right-content-item ">'
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
                    }
                };
                $('#pagination-container').pagination(options);
            });
        </script>
    </head>

    <body>

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
                <div class=" row">


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

                </div>

                <div id="data-container"></div>
                <div id="pagination-container"></div>                
                    
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
