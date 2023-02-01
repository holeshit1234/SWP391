<%-- 
    Document   : CheckOut
    Created on : Oct 31, 2022, 9:17:22 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check out</title>
    </head>
    <body>
        <h1>Your Cart</h1><br/>
        
        <c:if test="${not empty sessionScope}">           
            <a href="searchPage">Link here to buy more</a>
            <c:set var="list" value="${sessionScope.CHECK_OUT}"/>
            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Sku</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>total</th>
                        </tr>
                    </thead>
                    <tbody>  
                    <form action="payController" method="POST">
                        <c:set var="total" value="${0}"/>
                        <c:forEach var="item" items="${list}" varStatus="counter"> 
                            <c:set var="cartList" value="${sessionScope.CART.items}" />
                            <c:forEach var="result" items="${cartList}">
                                <c:if test="${item.sku eq result.key }">
                                    <tr>
                                        <td>
                                            ${counter.count} 
                                        </td>
                                        <td>
                                            ${result.key}
                                        </td>
                                        <td>
                                            ${item.name}
                                        </td>
                                        <td>
                                            ${item.price}
                                        </td> 
                                        <td>
                                            ${result.value}
                                        </td>
                                        <td>
                                            <%--${result.value * item.price} --%>                       
                                            <fmt:formatNumber type="number" 
                                                              maxFractionDigits="3" 
                                                              value="${result.value * item.price}"  />                     
                                            <c:set var="tmp" 
                                                   value="${(result.value * item.price)+ tmp}"/>  
                                        </td>                                       
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                        <td colspan="5"> 
                            total
                        </td>
                        <td>                                             
                           
                            <fmt:formatNumber type="number" 
                                                 maxFractionDigits="3" 
                                                 value=" ${tmp}"  /> 
                            <input type="hidden" name="txtTotal" value="${tmp}" />
                        </td >    

                        </tbody>
                </table>
                <input type="submit" value="Pay" name="btAction"/>
            </form>
        </c:if> 
        <c:if test="${empty sessionScope.CHECK_OUT}">
            <h2>Thank you for choosing us</h2>
        <a href="searchProduct">Click here to buy more</a>
    </c:if>
</c:if>
</body>
</html>
