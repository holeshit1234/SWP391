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

             <form action="viewCartPage" method="POST">
                <input type="submit" value="view_your_cart"  />
            </form>
        
        <c:set var="result" value="${requestScope.ITEMS_RESULT}"/>
        <c:if test="${not empty result}">       
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Image</th> 
                        <th>ProductName</th>
                        <th>Category</th>
                        <th>Brand</th>
                        <th>Gender</th>
                        <th>Price</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter" >
                    <form action="addToCartController" method="POST" > 
                        <tr>
                            <td>
                                ${counter.count}
                                .</td>

                            <td >
                                <img src="${dto.image}" />
                            </td> 
                            <td>${dto.productName}
                                <input type="hidden" name="txtProductName" 
                                       value="${dto.productName}" />
                            </td>
                            <td>${dto.categoryName}</td>
                            <td>${dto.brandName}</td>
                            <td>${dto.gender}</td>
                            <td>${dto.price}</td>
                            <td>
                                <input type="text" name="txtQuantity" value="1" />
                            </td>
                            <td>
                                <input type="submit" value="AddToCart"name="btAction"/>                                                                  
                            </td>
                        </tr>
                    </form> 
                </c:forEach>
            </tbody>
        </table>
    </c:if>  
    <c:if test="${empty result}">
        <h2>
            Sold out!!!
        </h2>
    </c:if> 
</body>
</html>
