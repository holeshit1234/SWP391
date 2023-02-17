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
        <title>JSP Page</title>
    </head>
    <body>
       <div class="forgot">
                <div class="container">
                    <div class="forgot-heading">
                        <h1>Password Recovery</h1>
                    </div>
                    <img srcset="images/Login-line.png 2x" alt="" />
                    <div class="forgot-form-container">
                        <form action="resetPasswordController" method="POST">
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
                                    <a href="loginPage" class="forgot-function-cancel">Cancel</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
    </body>
</html>
