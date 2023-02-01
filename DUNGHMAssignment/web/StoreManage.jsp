<%-- 
    Document   : StoreManage
    Created on : Oct 25, 2022, 9:45:01 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store</title>
    </head>
    <body>
        <h1>
            <font color="red"> 
            Welcome, ${sessionScope.User.fullName}
            </font>
        </h1><br/>
        
        <jsp:include page="logout.jsp"/>
         <a href="backPage">Back</a>
         
        
        <form action="searchProductManagerController" method="POST">
            Search Book <input type="text" name="txtSearchBookManager" 
                                value="${param.txtSearchBookManager}" />
            <input type="submit" value="SearchBookManager" name="btAction" />
        </form>
            
            <form action="createNewProduct" method="POST">
                <input type="submit" value="create new Product" />
            </form>

        <c:set var="searchbook" value="${param.txtSearchBookManager}"/>
        <c:if test="${not empty searchbook}">
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
                            <th>status</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" >
                        <form action="updateProductManagerController" method="POST">
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
                                    <input type="text" name="txtQuantity" 
                                           value="${dto.quantity}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPrice" 
                                           value="${dto.price}" />
                                </td>
                                <td>                                   
                                    <input type="checkbox" name="chkStatus" 
                                           value="ON" 
                                           <c:if test="${dto.status}">
                                               checked = "checked"
                                           </c:if>
                                           />

                                </td>
                                <td>
                                    <c:url var="urlDelete" value="deleteProductManagerController">
                                        <c:param name="btAction" value="DeleteProduct"/>
                                        <c:param name="pk" value="${dto.sku}"/>                                        
                                        <c:param name="LastSearchValue" value="${searchbook}"/>
                                    </c:url>
                                    <a href="${urlDelete}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="UpdateProduct" 
                                           name="btAction" />
                                    <input type="hidden" name="LastSearchValue" 
                                           value="${searchbook}" />
                                </td>
                            </tr>                          
                        </form>
                               
                    </c:forEach>
                </tbody>
            </table>
                <c:set var="error" value="${requestScope.ERROR_UPDATE_PRODUCT}"/>
                <c:if test="${not empty error.negativeErr}">
                    <font color="red">
                    <h2>
                    ${error.negativeErr}
                    </h2>
                    </font>
                </c:if>
                    <c:if test="${not empty error.wrongFormatErr}">
                    <font color="red">
                    <h2>
                    ${error.wrongFormatErr}
                    </h2>
                    </font>
                    </c:if>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                No records found!!!
            </h2>
        </c:if>
    </c:if>

</body>
</html>
