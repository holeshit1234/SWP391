<%-- 
    Document   : logout
    Created on : Oct 26, 2022, 12:28:27 AM
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
       <c:url var="urllogout" value="logoutcontroller">
            <c:param name="btAction" value="Logout"/>
        </c:url>
        <a href="${urllogout}">Logout</a>      
    </body>
</html>
