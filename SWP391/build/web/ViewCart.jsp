<%-- 
    Document   : ViewCart
    Created on : Feb 9, 2023, 1:48:50 PM
    Author     : mthin
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
    <h1>Your Cart</h1>
     <c:set var="cart" value="${sessionScope.CART.items}"/>
        <c:if test="${not empty cart}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ProductName</th>
                        <th>Quantity</th>                      
                    </tr>
                </thead>

                <tbody>

                    <c:forEach var="item" items="${cart}" varStatus="counter">
                   
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${item.key}
                            </td>
                            <td>
                                ${item.value}
                            </td>
                            <td>
                                <input type="checkbox" name="chkItem" 
                                       value="${item.key}" />
                            </td>
                        </tr>

                    </c:forEach>                                  
            </tbody>
        </table>
            </c:if>
  </body>
</html>
