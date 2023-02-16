<%-- 
    Document   : showDetailProduct
    Created on : Feb 14, 2023, 4:12:10 PM
    Author     : mthin
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Product</title>
    </head>
    <body>
        <%--Menu--%>
        <%--Main--%>
        <c:set var="result" value="${requestScope.P_Detail}"/>
        <c:if test="${not empty result}"> 
            <div class="cartegory-right-content row">
                <c:forEach var="detail" items="${result}" >
                    <form action="addToCartController" method="POST">
                        <img src="${detail.image}"/>
                        <div>${detail.productName} </div>
                        <div>${detail.gender}</div> 
                        <div>${detail.brandName}</div> 
                        <div>${detail.price}</div> 
                        <div>${detail.quantity}</div> 
                        <input type="submit" value="add to cart" name="btAction" />
                    </form>
                </c:forEach>                                
            </div>
        </c:if>
        <c:if test="${ empty result}">  
            Dang lam sai
        </c:if>
        <%--Footer--%>
    </body      
</html>
