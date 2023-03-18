<%-- 
    Document   : resetPassword
    Created on : Feb 16, 2023, 12:22:19 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
              crossorigin="anonymous">

        <link rel="stylesheet" href="asset/css/resetpassword.css">

        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        <title>JSP Page</title>
    </head>
    <body>

        <jsp:include page="header.jsp"/>
        
        <div class="form">
            <div class="forgot">
                <div class="container">
                    <div class="forgot-heading">
                        <h1>Password Recovery</h1>
                    </div>
                    <img srcset="images/Login-line.png 2x" alt="" />
                    <div class="forgot-form-container">
                        <form action="ResetPasswordServlet" method="POST">
                            <div class="forgot-form">
                                <c:set var = "errors" value="${requestScope.RESETPASSWORD_ERROR}"/>
                                <input type="password" name="txtPassword"
                                       class="forgot-input-password"
                                       placeholder="New password"
                                       value="" />
                                <c:if test="${not empty errors.passwordLengthErr}">
                                    <font color="red">
                                    ${errors.passwordLengthErr}
                                    </font><br/>
                                </c:if>
                                <input type="password" name="txtConfirm"
                                       class="forgot-input-password-confirm"
                                       placeholder="Confirm new password"
                                       value="" /><br/>
                                <c:if test="${not empty errors.confirmNotMatchErr}">
                                    <font color="red">
                                    ${errors.confirmNotMatchErr}
                                    </font><br/>
                                </c:if>
                            </div>
                            <div class="forgot-function">
                                <p class="forgot-function-security">
                                    This site is protected by reCAPTCHA and the Google
                                    <a href="https://policies.google.com/terms">Privacy Policy</a>
                                    and
                                    <a href="https://policies.google.com/privacy"
                                       >Terms of Service</a
                                    >
                                    apply.
                                </p>
                                <div class="forgot-function-choose">
                                    <input type="submit" value="SAVE"
                                           class="forgot-function-btn"
                                           name="btAction" />
                                    <a href="login.jsp" class="forgot-function-cancel">Cancel</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

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
    </body>
</html>
