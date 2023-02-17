<%-- 
    Document   : verifyCode
    Created on : Feb 16, 2023, 12:08:47 PM
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
         <div class="forgot-form-container">
                        <img srcset="images/security-user.png 2x" alt="" />
                        <h3 class="forgot-text">Enter Code</h3>
                        <p class="forgot-text-detail">
                            We sent OTP code to your email address
                        </p>
                        <form action="verifyCodeController" method="POST">
                            <div class="forgot-form">
                                <c:set var = "scope" value="${requestScope.VERIFYCODE_SCOPE}"/>
                                <div class="forgot-input">
                                    <input type="text" class="forgot-input-otp"
                                           name="txtCode1" value="${param.txtCode1}"/><br/>
                                </div>
                                <div class="forgot-input">
                                    <input type="text" class="forgot-input-otp"
                                           name="txtCode2" value="${param.txtCode2}"/><br/>
                                </div>
                                <div class="forgot-input">
                                    <input type="text" class="forgot-input-otp"
                                           name="txtCode3" value="${param.txtCode3}"/><br/>
                                </div>
                                <div class="forgot-input">
                                    <input type="text" class="forgot-input-otp"
                                           name="txtCode4" value="${param.txtCode4}"/><br/>
                                </div>
                            </div>
                            <c:if test="${not empty scope.codeLengthError}">
                                <p class="forgot-form-container" 
                                   style="font-size: 20px; color: red" >
                                    ${scope.codeLengthError}
                                </p>
                            </c:if>
                            <c:if test="${not empty scope.codeNotExisted}">
                                <p class="forgot-form-container" 
                                   style="font-size: 20px; color: red" >
                                    ${scope.codeNotExisted}
                                </p>
                            </c:if>
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
                                    <input type="submit" value="VERIFY" class="forgot-function-btn">
                                    <a href="loginPage" class="forgot-function-cancel">Cancel</a>
                                </div>
                            </div>
                        </form>
                    </div>
    </body>
</html>
