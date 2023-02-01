<%-- 
    Document   : SignUp
    Created on : Oct 26, 2022, 6:21:03 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
         <h1>
            Create Account
        </h1>
        <form action="createAccountController" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" />(6-20)<br/>
            
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                    ${errors.usernameLengthError}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                ${errors.usernameIsExisted}
                </font><br/>
            </c:if>
                
            Password* <input type="password" name="txtPassword" value="" />(6-20)<br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                    ${errors.passwordLengthError}
                </font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                    ${errors.confirmNotMatched}
                </font><br/>
            </c:if>
            Full Name* <input type="text" name="txtFullname" 
                              value="${param.txtFullname}" />(2-50)<br/>
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color="red">
                    ${errors.fullnameLengthError}
                </font><br/>
            </c:if>
            <input type="submit" value="create_New_Account" name="btAction" /><br/>
            <input type="reset" value="reset" />            
        </form>
    </body>
</html>
