<%-- 
    Document   : Store
    Created on : Oct 25, 2022, 6:09:40 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fruit Store</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope}">
        <h1>
            <font color="red">
            Welcome, ${sessionScope.User.fullName}
            </font>
        </h1>
            <jsp:include page="logout.jsp"/> <br/>
            
            <form action="viewCartPage" method="POST">
                <input type="submit" value="view_your_cart" name="btAction" />
            </form>
            
        </c:if>
        
        <h3>
            Book Store
        </h3>
                
        <form action="searchProductUserController" method="POST">
            Search Book <input type="text" name="txtSearchBook" 
                                value="${param.txtSearchBook}" />
            <input type="submit" value="SearchBook" name="btAction" />            
        </form>
                                            
            <c:set var="SearchBook" value="${param.txtSearchBook}"/>
            <c:if test="${not empty SearchBook}">
                <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
                <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>sku</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>AddToCart</th>
                            </tr>
                        </thead>
                        <tbody>
                             <c:forEach var="dto" items="${result}" >
                        <form action="addToCartController" method="POST">                            
                            <tr>
                                <td>
                                    ${dto.sku}
                                    <input type="hidden" name="txtSku" 
                                           value="${dto.sku}" />
                                </td>
                                <td>
                                    ${dto.name}
                                </td>
                                <td>
                                    ${dto.description}
                                </td>
                                <td>
                                    <input type="text" name="txtQuantity" value="1" />
                                </td>
                                <td>                                  
                                    <input type="text" name="txtPrice" 
                                           value="${dto.price}" />
                                </td>                               
                                <td>
                                    <input type="submit" value="AddToCart" 
                                           name="btAction"/>
                                    <input type="hidden" name="LastSearchValue"
                                           value="${SearchBook}" />                                                                  
                                </td>
                            </tr>
                        </form>
                             </c:forEach>
                        </tbody>
                    </table>

                </c:if>
                <c:if test="${empty result}">
                    <h2>
                        No records found!!!
                    </h2>
                </c:if>
            </c:if>
    </body>
</html>
