<%-- 
    Document   : registration
    Created on : Feb 7, 2023, 1:08:25 PM
    Author     : vinht
--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
    
        <h1>Create an Account</h1>
        <form action="SignUpPageServlet" method="POST">
            <c:set var="errors" value="${requestScope.ERROR}" />
            
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" /> (4 - 30 chars) <br><br/>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color='red'>
                    ${errors.usernameLengthErr}
                </font><br/>
            </c:if>
                
            Password* <input type="password" name="txtPassword" 
                             value="" /> (4 - 20 chars) <br><br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color='red'>
                    ${errors.passwordLengthErr}
                </font><br/>
            </c:if>
                
            Confirm* <input type="password" name="txtConfirm" value="" /> <br><br/>
            <c:if test="${not empty errors.confirmNotMatchErr}">
                <font color='red'>
                    ${errors.confirmNotMatchErr}
                </font><br/>
            </c:if>
            
            Full name* <input type="text" name="txtFullname" 
                              value="${param.txtFullname}" /> (2 - 50 chars) <br><br/>
            <c:if test="${not empty errors.fullnameLengthErr}">
                <font color='red'>
                    ${errors.fullnameLengthErr}
                </font><br/>
            </c:if>
                
            Email <input type="text" name="txtEmail" value="" /> <br><br/>
                     
            Phone <input type="text" name="txtPhone" value="" /> <br><br/>          
                
            <c:set var="message" value="${requestScope.MESSAGE}" />
            <c:if test="${not empty message}">
                <font color='red'>
                    ${message}
                </font><br/>
            </c:if>
                
                
                
            <input type="submit" value="Create New Account" name="btAction" /> 
            <input type="reset" value="Reset" />
            
            
            
            
            
            
        </form>
        <br/>
        Already have an account? <a href="login.jsp">Click here to Login</a>
    </body>
</html>
