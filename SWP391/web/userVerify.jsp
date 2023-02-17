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
     
        <title>JSP Page</title>
    </head>
    <body>
        
       <div class="forgot">
                <div class="container">
                    <div class="forgot-heading">
                        <h1>Login</h1>
                    </div>
                    <img srcset="images/Login-line.png 2x" alt="" />
                    <div class="forgot-form-container">
                        
                        <form action="userVerifyController" method="POST">
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
                                    <a href="loginPage" class="forgot-function-cancel">Cancel</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
                                    
    </body>
</html>
