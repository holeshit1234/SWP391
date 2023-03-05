<%-- 
    Document   : header
    Created on : Feb 14, 2023, 6:42:34 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap -->
        <link href="asset/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="asset/css/styleheader.css">


        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">

        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <div class="logo">
                <a href="ShowIdexItemServlet"><img src="asset/images/logo-circle.png"></a>

            </div>
            <div class="menu">
                <!--        <li><a href="">Male</a>
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
                        <li><a href="">Information</a></li>-->
                <li><a href="SearchServlet">Search Page</a> </li>
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


                <c:if test="${empty sessionScope.USER}">
                    <li><a class="fa fa-user" href="login.jsp"></a></li>
                </c:if>
                <c:if test="${not empty sessionScope.USER}">
                    <li><a class="fa fa-user" href="${urlprofile}" ></a></li>
                </c:if>

                <li><a class="fa fa-shopping-bag" href="ViewCartServlet"></a></li>
                <c:if test="${not empty sessionScope.USER}">
                    <!--<li> <a href="LogoutAccountServlet">(Logout)</a>  </li>-->
                    <jsp:include page="logout.jsp"/>
                </c:if>
            </div>

        </header>

    </body>
</html>
