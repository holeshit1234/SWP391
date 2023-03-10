<%-- 
    Document   : userVerify
    Created on : Feb 16, 2023, 11:31:15 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="asset/css/styleUserVerify.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
              crossorigin="anonymous">

        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

<div class="content">
        <div class="forgot">
            <div class="container">
                <div class="forgot-heading">
                    <h1>Login</h1>
                </div>
                <img srcset="images/Login-line.png 2x" alt="" />
                <div class="forgot-form-container">

                    <form action="UserVerifyServlet" method="POST">
                        <p class="forgot-text">Password Recovery</p>
                        <c:set var = "scope" value="${requestScope.VERIFYMAIL_SCOPE}"/>
                        <div class="forgot-form">
                            <input type="email" name="txtEmail"
                                   class="forgot-input-email"
                                   placeholder="Email"
                                   value="${param.txtEmail}"><br/>
                            <div class="phat">
                                <c:if test="${not empty scope.emailLengthError}">
                                    <font color="red" >
                                    ${scope.emailLengthError}
                                    </font><br/>
                                </c:if>
                            </div>
                            <c:if test="${not empty scope.emailNotExisted}">
                                <font color="red">
                                ${scope.emailNotExisted}
                                </font><br/>
                            </c:if>
                            <c:if test="${not empty scope.signUpWithGoogleAccount}">
                                <font color="red">
                                ${scope.signUpWithGoogleAccount}
                                </font><br/>
                            </c:if>
                        </div>
                        <div class="forgot-function">
                            <p class="forgot-function-security">
                                This site is protected by reCAPTCHA and the Google
                                <a href="https://policies.google.com/terms">Privacy Policy</a> and
                                <a href="https://policies.google.com/privacy">Terms of Service</a> apply.
                            </p>
                            <div class="forgot-function-choose">
                                <input class="forgot-function-btn" type="submit" value="Send"
                                       name = "btAction">
                                <a href="login.jsp" class="forgot-function-cancel">Cancel</a>
                            </div>
                        </div>
                    </form>
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
                ©IVYmoda All rights reserved
            </div>
        </footer>


        <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
