<%-- 
    Document   : Store
    Created on : Feb 8, 2023, 10:29:32 AM
    Author     : mthin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop DVHT</title>
    </head>
    <body>
        <h1>Store</h1>
        
         <c:set var="result" />
            <c:if test="${ empty result}">
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                  <%--  <th>Image</th> --%>
                    <th>Category</th>
                    <th>Brand</th>
                    <th>Gender</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" items="${dto.productId}" >
                    <tr>
                        <td>${dto.productId}</td>
                       <%-- <td>${dto.image}</td> --%>
                        <td>${dto.category}</td>
                        <td>${dto.brand}</td>
                        <td>${dto.gender}</td>
                        <td>${dto.price}</td>
                        <td>${dto.quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
