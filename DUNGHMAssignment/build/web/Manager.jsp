<%-- 
    Document   : Manager
    Created on : Oct 25, 2022, 9:34:20 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager</title>
    </head>
    <body>
        <h1>
            <font color="red"> 
            Welcome, ${sessionScope.User.fullName}
            </font>
        </h1>

        <jsp:include page="logout.jsp"/>


        <table border="1">
            <thead>
            <thead>
                <tr>
                    <th>Option</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
            <form action="manageAccountPage" method="POST">
                <tr>                
                    <td> <input type ="submit" name = "btAction" 
                                value = "Manage_User_Accounts" /> </td>
                    <td> Manage User Accounts </td>
                </tr>
            </form>
            <form action="manageStoreController" method="POST">
                <tr>
                    <td> <input type ="submit" name = "btAction" 
                                value = "Manage_Store"/> </td> </td>
                    <td> Manage Store </td>
                </tr>
            </form>

        </tbody>

    </table>                      


</body>
</html>
