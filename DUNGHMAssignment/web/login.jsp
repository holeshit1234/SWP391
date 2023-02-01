<%-- 
    Document   : login
    Created on : Oct 25, 2022, 11:38:48 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>
            Login
        </h1> 

        <c:forEach var="cookies" items="${cookie}">
            <c:if test="${cookies.key eq 'CUSER'}">
                <c:set var="username" value="${cookies.value.value}" />
            </c:if>

        </c:forEach>

        <form action="loginController" method="POST">
            Username <input type="text" name="txtUsername" 
                            value="${username}" /><br/>           
            Password <input type="password" name="txtPassword" 
                            value="" /><br/>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />

        </form> 
            
        <form action="signUpPage" method="POST">
            <input type="submit" value="SignUP" name="btAction"/><br/>            
        </form>

        <form action="clickToBuy" method="POST">
            <input type="submit" value="Click Here To Buy" name="btAction" />
        </form>

    </body>
</html>
