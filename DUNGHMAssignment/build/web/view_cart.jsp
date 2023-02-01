<%-- 
    Document   : view_cart
    Created on : Oct 25, 2022, 5:59:19 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>

        <h1>Your Cart</h1><br/>

        <c:set var="cartList" value="${sessionScope.CART.items}"/>
        <c:if test="${not empty cartList}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Sku</th>
                        <th>Quantity</th>
                        <th>btAction</th>
                    </tr>
                </thead>
                <tbody>     
                <form action="removeToCartController" method="POST">
                    <c:forEach var="result" items="${cartList}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count} 
                            </td>
                            <td>
                                ${result.key}
                            </td>
                            <td>
                                ${result.value}
                            </td>
                            <td>
                                <input type="checkbox" name="chkItems" 
                                       value="${result.key}" />                                   
                            </td>                                       
                        </tr>
                    </c:forEach>
                    <td colspan="3"> 
                        <a href="searchProduct">ADD MORE</a>
                    </td>
                    <td>                                
                        <input type="submit" value="removeItems" name="btAction" />                             
                    </td >    
                    </tbody>
                </form>

            </table> 

            <form action="checkOutPage" method="POST">
                <input type="submit" value="checkout" name="btAction" />
            </form>      
        </c:if>  
        <c:if test="${empty cartList}">
            <h2>
                not items here!!!!
            </h2>
            <a href="searchProduct">Back To Store</a>
        </c:if>
    </body>
</html>
