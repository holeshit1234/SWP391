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
                <li><a href="showProductByGenderServlet?gender=nam">Nam</a></li>
                <li><a href="showProductByGenderServlet?gender=nu">Nữ</a></li>
                <li><a href="showProductByGenderServlet?gender=unisex">Unisex</a></li>
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

                <li><a class="fa fa-shopping-bag" href="ViewCartServlet"></a></li>
                <c:if test="${not empty sessionScope.USER}">
                    <!--<li> <a href="LogoutAccountServlet">(Logout)</a>  </li>-->
                    <jsp:include page="logout.jsp"/>
                </c:if>
            </div>

        </header>

    </body>
</html>
