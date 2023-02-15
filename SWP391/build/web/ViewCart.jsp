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
                <form action="removeToCartController" method="POST">  
                    <form action="updateCartController" method="POST">  
                        <c:forEach var="item" items="${cart}" varStatus="counter">

                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${item.key}
                                </td>
                                <td>
                                    <input type="text" name="txtQuantity" value=" ${item.value}" />
                                    <input type="submit" value="UpdateCart" name="btAction" />     
                                </td>
                                <td>
                                    <input type="checkbox" name="chkItem" 
                                           value="${item.key}" />
                                </td>

                            </tr>
                        </c:forEach>      
                        <td>                                
                            <input type="submit" value="removeItems" name="btAction" />                             

                        </td > 
                    </form>
                </form>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty cart}">
        <h2>
            not items here!!!!
        </h2>
        <form action="viewCartPage" method="POST">Back to Store</form>
    </c:if>
</body>

</html>
