<%-- 
    Document   : logout
    Created on : Feb 2, 2023, 5:02:35 PM
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
        <c:url var="urllogout" value="DispatchController">
            <c:param name="btAction" value="Logout"/>
        </c:url>
        <a href="${urllogout}">Logout</a>  
    </body>
</html>
