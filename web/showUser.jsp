<%-- 
    Document   : showUser.jsp
    Created on : Feb 25, 2023, 6:58:39 AM
    Author     : User
--%>

<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="asset/css/showuser.css" />


        <script>
            function showPopup(id, email, fullname, gender, role) {
                alert(`ID: ${id}\nEmail: ${email}\nFullname: ${fullname}\nGender: ${gender}\nRole: ${role}`);
            }

            // Get all the "Update" buttons
            const updateButtons = document.querySelectorAll('.update-button');

            // Add a click event listener to each "Update" button
            updateButtons.forEach(button => {
                button.addEventListener('click', event => {
                    // Get the row data
                    const row = event.target.closest('tr');
                    const id = row.querySelector('input[name="txtUserid"]').value;
                    const email = row.querySelector('input[name="txtEmail"]').value;
                    const fullname = row.querySelector('input[name="txtFullname"]').value;
                    const gender = row.querySelector('td:nth-child(4)').textContent;
                    const role = row.querySelector('td:nth-child(5)').textContent;
                    //            const status = row.querySelector('td:nth-child(6)').textContent;

                    // Call the showPopup() function with the row data
                    showPopup(id, email, fullname, gender, role);
                });
            });
        </script>
    </head>
    <body>
        <c:if test="${not empty requestScope.USER_INFO}">
            <c:set var="user_info" value="${requestScope.USER_INFO}"/>
            <table id="datatablesSimple">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>email</th>
                        <th>fullname</th>
                        <th>gender</th>
                        <th>Role</th>
                        <!--<th>Status</th>-->
                        <th>Function</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>id</th>
                        <th>email</th>
                        <th>fullname</th>
                        <th>gender</th>
                        <th>Role</th>
                        <!--<th>Status</th>-->
                        <th>Function</th>
                    </tr>
                </tfoot>
                <tbody>
                    <c:forEach var="dto" items="${user_info}">
                        <tr>
                            <td>${dto.getUserID()}</td>
                            <td><input type="text" name="txtEmail" value="${dto.email}" /></td>
                            <td><input type="text" name="txtFullname" value="${dto.fullName}" /></td>
                            <td>${dto.gender}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${dto.getRoleID() == 2}">Manager</c:when>
                                    <c:when test="${dto.getRoleID() == 3}">User</c:when>
                                    <c:otherwise>Unknown</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <%--                            <td>
                                                                <c:choose>
                                                                    <c:when test="">Ban</c:when>
                                                                    <c:when test="">Not Ban</c:when>
                                                                    <c:otherwise>Unknown</c:otherwise>
                                                                </c:choose>
                                                            </td>       --%>
                            </td>
                            <td>
                                <input type="button" value="Update" class="update-button" onclick="showPopup('${dto.getUserID()}', '${dto.email}', '${dto.fullName}', '${dto.gender}', '${dto.getRoleID()}')" />
                                <input type="button" value="BanUser" name="btAction">
                                <input type="button" value="UnBanUser" name="btAction">
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>


        </c:if>
    </body>
</html>
