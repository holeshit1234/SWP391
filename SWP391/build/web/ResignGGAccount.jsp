<%-- 
    Document   : ResignGGAccount
    Created on : Feb 17, 2023, 6:06:39 AM
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
        <form action="writeInformationGgController" method="POST">
            Phone <input type="text" name="txtPhone" value="${param.txtPhone}" /><br>
            Gender <input type="radio" name="txtGender" value="Nam" /><br>
                   <input type="radio" name="txtGender" value="Nữ" /><br>
                   <input type="radio" name="txtGender" value="Other" /><br>
            DOB <input type="date" name="txtDOB" value="${param.txtDOB}" /><br>
            Province <input type="text" name="txtProvince" value="${param.txtProvince}" /><br>
            District <input type="text" name="txtDistrict" value="${param.txtDistrict}" /><br>
            Ward <input type="text" name="txtWard" value="${param.txtProvince}" /><br>
            Street <input type="text" name="txtStreet" value="${param.txtProvince}" /><br>
            
            <input type="submit" value="Summit" />
            
            
        </form>
    </body>
</html>
