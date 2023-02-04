<%-- 
    Document   : profiles
    Created on : Feb 3, 2023, 10:17:28 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <c:if test="${not empty sessionScope}">
            <c:set var="user" value="${sessionScope.User}" />
            <c:if test="${not empty requestScope}">
                <c:set var="address" value="${requestScope.INFO}" />
        <div>

            <div>
                <div> <lable>Full Name</lable></div>
                <div> <input type="text" name="txtFullName" value="${user.fullName}"
                             placeholder="Nhập đầy đủ tên"/> </div>
            </div>

            
              <div>
                <div> <lable>Email</lable></div>
                <div> <input type="text" name="txtEmail" value="${user.email}" 
                             placeholder="Nhập đầy đủ tên" /> </div>
            </div>
            
            <div>
                <div> <lable>Phone</lable></div>
                 <div> <input type="text" name="txtPhone" value="${user.phone}" 
                              placeholder="Nhập Số điện thoại"/> </div>
            </div>
             <div>
                <div> <lable>Street</lable></div>
                 <div> <input type="text" name="txtStreet" value="${address.Street}" 
                              placeholder="Nhập số nhà, tên đường"/> </div>
            </div>
            
        </div>
    
 
                
                
            </c:if>
        </c:if>




        <jsp:include page="logout.jsp" />
    </body>
</html>
