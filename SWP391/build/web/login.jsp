<%-- 
    Document   : login.jsp
    Created on : Feb 6, 2023, 10:24:21 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="asset/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="asset/css/stylelogin.css">

        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
    </head>
    <body>
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
        <div>
            <section class="login">
                <div class="container">
                    <div class="sigin-signup">
                        <form action="LoginServlet" method="POST" class="sign-in-form">
                            <h2 class="title">Login</h2>
                            <div class="input-field">
                                <i class="fa fa-user"></i>

                                <c:set var="err" value="${requestScope.L_ERROR}" />
                                <input type="text" name="txtUsername" value="${param.txtUsername}" /><br/>
                            </div>
                            <div class="input-field">
                                <i class="fa fa-lock"></i>
                                <input type="password" name="txtPassword" value="" /><br/>                                 
                            </div>
                            <c:if test="${not empty err.emptyUserNamePassWord}">
                                <font color="red">
                                ${err.emptyUserNamePassWord}
                                </font><br/>
                            </c:if>
                            <c:if test="${not empty err.wrongUserNamePassWord}">
                                <font color="red">
                                ${err.wrongUserNamePassWord}
                                </font><br/>
                            </c:if>
                            <div class="remember-forgot">
                                <label><input type="checkbox" name="chkremember" value="ON" />
                                    Remember me</label>
                                <a href="userVerify.jsp">Forgot Password?</a>
                            </div>


                            <input type="submit" value="login" class="btn" name="btAction">

                            <p class="social-text">Or sign in with social platform</p>
                            <div class="social-media">
                                <a href="http://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:8084/SWP391/GoogleSignInServlet&response_type=code
                                   &client_id=862594563658-nsdpdrc8k2ee7h28v6r1rg8t3obssjtv.apps.googleusercontent.com&approval_prompt=force" class="social-icon">
                                    <i class="fa fa-google"></i>                             
                                </a>
                            </div>

                        </form> 

                        <form action="signup.jsp" class="sign-up-form">
                            <h2 class="title">Become a customer of the store</h2>

                            <p class="social-text">If you do not have an account on ivymoda.com, use this option to access the registration form. By providing IVY moda with your details, the buying process on ivymoda.com will be a more enjoyable and quicker experience.</p>
                            <input type="submit" value="Sign up" class="btn">
                            </div>
                        </form>
                    </div>

            </section>



        </div>

    </body>
</html>
