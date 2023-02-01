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
        <h1>
            <font color="red"> 
            Welcome, ${sessionScope.User.fullName}
            </font>
        </h1>

        <jsp:include page="logout.jsp"/>
        <a href="backPage">Back</a>

        <h2> Search Page </h2>

        <form action="searchController" method="POST">
            Search <input type="text" name="txtSearchValue" 
                          value="${param.txtSearchValue}" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form><br/>

        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>UserName</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" 
                                   varStatus="counter">
                        <form action="updateController" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                    .</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkisAdmin" 
                                           value="ON"
                                           <c:if test="${dto.role}">
                                               checked ="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="deleteController">
                                        <c:param value="Delete" name="btAction" />
                                        <c:param value="${dto.username}" name="pk" />
                                        <c:param value="${searchValue}" name="LastSearchValue" />
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>

                                </td>

                                <td>
                                    <input type="submit" value="Update" 
                                           name="btAction" />
                                    <input type="hidden" name="LastSearchValue" 
                                           value="${searchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                No Records Found!!!!!
            </h2>
        </c:if>





        <c:if test="${not empty requestScope}">
            <h3>${requestScope.STATUS}</h3>
        </c:if>
    </c:if>
    <c:set var="errDelete" value="${requestScope.ERROR_DELETE}"/>
    <c:if test="${not empty errDelete.deleteCurrentAccount}" >
        <font color="red">
        <h2>${errDelete.deleteCurrentAccount}</h2>
        </font> 
    </c:if>

    <c:set var="errorUpdate" value="${requestScope.ERROR}"/>
    <c:if test="${not empty errorUpdate.passwordLengthError}">
        <font color="red">
        <h2>${errorUpdate.passwordLengthError}</h2>
        </font>
    </c:if>

</body>
</html>
