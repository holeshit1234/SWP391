<%-- 
    Document   : login.jsp
    Created on : Feb 6, 2023, 10:24:21 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
        
        <div>
            <form action="loginController" method="POST">
            Username <input type="text" name="txtUsername" value="" /><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>            
            <input type="submit" value="Login"  />
            <input type="reset" value="Reset" />            
        </form> 

        Remember me <input type="checkbox" name="chkremember" value="ON" />

        <form action="registration.jsp" method="POST">
            <input type="submit" value="SignUP" name="btAction"/><br/>            
        </form>

        <!--<form action="GoogleController">-->

            <div> 
                <a href="http://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:8084/SWP391/GoogleSignInServlet&response_type=code
                   &client_id=862594563658-nsdpdrc8k2ee7h28v6r1rg8t3obssjtv.apps.googleusercontent.com&approval_prompt=force">Login with Google</a>
            </div>
        <!--</form>-->
        </div>
        
    </body>
</html>
