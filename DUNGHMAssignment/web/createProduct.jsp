<%-- 
    Document   : createProduct
    Created on : Nov 2, 2022, 7:51:03 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Product</title>
    </head>
    <body>
        <form action="createProductController" method="POST">
            <c:set var="error" value="${requestScope.ERRORS}" />

            Sku* <input type="text" name="txtSku" 
                        value="${param.txtSku}" />(1-10)<br/>
            <c:if test="${not empty error.skuLengthErr}">
                <font color="red">
                ${error.skuLengthErr}
                </font><br/>
            </c:if>

            <c:if test="${not empty error.skuIsExisted}">
                <font color="red">
                ${error.skuIsExisted}
                </font><br/>
            </c:if>   

            Name* <input type="text" name="txtName" 
                         value="${param.txtName}" />(1-50)<br/>
            <c:if test="${not empty error.nameLengthErr}">
                <font color="red">
                ${error.nameLengthErr}
                </font><br/>
            </c:if>   

            Description* <input type="text" name="txtDescription" 
                                value="${param.txtDescription}" />(1-50)<br/>

            <c:if test="${not empty error.descriptionLengthErr}">
                <font color="red">
                ${error.descriptionLengthErr}
                </font><br/>
            </c:if>   

            Quantity* <input type="number" name="txtQuantity" 
                             value="${param.txtQuantity}" />(can't negative)<br/>

            <c:if test="${not empty error.negativeQuantityErr}">
                <font color="red">
                ${error.negativeQuantityErr}
                </font><br/>
            </c:if>

            Price* <input type="number" name="txtPrice" 
                          value="${param.txtPrice}" />(can't negative)<br/>
            <c:if test="${not empty error.negativePriceErr}">
                <font color="red">
                ${error.negativePriceErr}
                </font><br/>
            </c:if>
                
            <input type="submit" value="createNewProduct" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
