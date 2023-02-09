<%-- 
    Document   : search
    Created on : Oct 24, 2022, 10:07:52 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope}">
            <c:set var="user" value="${sessionScope.User}" />
            <c:if test="${not empty requestScope}">
                <c:set var="address" value="${requestScope.INFO}" />
                
              
               
                              
                              
            <div>
                <div> <lable>Ward</lable></div>
                 <div><input type="text" name="txtWard" value="${address.ward}" 
                             placeholder="Nhập Phường, Quận " /></div>
            </div>
            <div>
                <div><lable>Province</lable></div>
                <div><input type="text" name="txtProvince" value="${address.provice}" 
                            placeholder="Nhập Tỉnh, Thành phố"/></div>
            </div>
            </c:if>
        </c:if>

    </body>
</html>
