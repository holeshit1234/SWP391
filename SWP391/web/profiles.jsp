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
            <c:set var="user" value="${sessionScope.USER}" />
            <c:if test="${not empty requestScope.USERS}">
                <c:set var="info_user" value="${requestScope.USERS}"/>
                <c:if test="${not empty requestScope.INFO}">
                    <form action="UpdateProfileController" method="POST">
                        <div class="profiles-container">
                            <div class="row" style="display: block;">
                                <div class="col-md-2">
                                    <p>thông tin tài khoản</p>
                                    <p>thông tin tài khoản</p>
                                    <p>thông tin tài khoản</p>
                                </div>
                                <c:set var="error" value="${requestScope.UP_ERROR}"/>
                                <div class="col-md-8 profile-content ">
                                    <div class="row">
                                        <div class="col-md-6 ">
                                            <img class="resposive img-circle avatar" src="images/banner1.jpg">

                                        </div>
                                        <div class="col-md-6 ">
                                            <div class="profile-detail-content">
                                                <div class="profile-detail">
                                                    <div class="row">
                                                        <div class="content col-md-4 " style="float: left;">
                                                            <lable>Full Name</lable>
                                                        </div>
                                                        <div class="input-text col-md-8">
                                                            <input type="text" name="txtFullName" value="${info_user.fullName}"
                                                                   placeholder="Nhập đầy đủ tên" />
                                                        </div>
                                                    </div>
                                                    <c:if test="${not empty error.notEnoughWordFullName}">
                                                        <font color="red">
                                                        ${error.notEnoughWordFullName}
                                                        </font><br/>
                                                    </c:if>
                                                </div>
                                                <div class="profile-detail">
                                                    <div class="row">
                                                        <div class="content col-md-4 " style="float: left;">
                                                            <lable>Date Of Birth</lable>
                                                        </div>
                                                        <div class="input-text col-md-8">
                                                            <input type="date" name="txtDOB" value="${info_user.DOB}" 
                                                                   placeholder="Nhập đầy đủ tên" />
                                                        </div>
                                                    </div>
                                                    <c:if test="${not empty error.emptyDOB}">
                                                        <font color="red">
                                                        ${error.emptyDOB}
                                                        </font><br/>
                                                    </c:if>
                                                </div>
                                                <div class="profile-detail">
                                                    <div class="row">
                                                        <div class="content col-md-4 " style="float: left;">
                                                            <lable>Gender</lable>
                                                        </div>
                                                        <div class="input-text col-md-8">
                                                            <input type="text" name="txtDOB" value="${info_user.gender}" disabled="disabled"
                                                                   placeholder="Nhập đầy đủ tên" /> 
                                                        </div>
                                                    </div>

                                                </div>

                                                <div class="profile-detail">
                                                    <div class="row">
                                                        <div class="content col-md-4" style="float: left;">
                                                            <lable>Email</lable>
                                                        </div>
                                                        <div class="input-text col-md-8">
                                                            <input type="text" name="txtEmail" value="${info_user.email}" 
                                                                   placeholder="Nhập đầy đủ tên" />
                                                        </div>
                                                    </div>
                                                    <c:if test="${not empty error.emptyEmail}">
                                                        <font color="red">
                                                        ${error.emptyEmail}
                                                        </font><br/>
                                                    </c:if>
                                                </div>

                                                <div class="profile-detail">
                                                    <div class="row">
                                                        <div class="content col-md-4" style="float: left;">
                                                            <lable>Phone</lable>
                                                        </div>
                                                        <div class="input-text col-md-8">
                                                            <input type="text" name="txtPhone" value="${info_user.phone}"
                                                                   placeholder="Nhập Số điện thoại" />
                                                        </div>
                                                    </div>
                                                    <c:if test="${not empty error.emptyPhone}">
                                                        <font color="red">
                                                        ${error.emptyPhone}
                                                        </font><br/>
                                                    </c:if>
                                                </div>
                                                <c:forEach var="value" items="${requestScope.INFO}">   
                                                    <div class="profile-detail">
                                                        <div class="row">
                                                            <div class="content col-md-4" style="float: left;">
                                                                <lable>Street</lable>
                                                            </div>
                                                            <div class="input-text col-md-8">

                                                                <input type="text" name="txtStreet" value="${value.getStreet()}"
                                                                       placeholder="Nhập số nhà, tên đường" /> 

                                                            </div>
                                                        </div>
                                                        <c:if test="${not empty error.emptyStreet}">
                                                            <font color="red">
                                                            ${error.emptyStreet}
                                                            </font><br/>
                                                        </c:if>
                                                    </div>

                                                    <div class="profile-detail">
                                                        <div class="row">
                                                            <div class="content col-md-4" style="float: left;">
                                                                <lable>Province</lable>
                                                            </div>
                                                            <div class="input-text col-md-8">
                                                                <input type="text" name="txtProvince" value="${value.getProvice()}"
                                                                       placeholder="Nhập Phường, Quận " /> 

                                                            </div>
                                                        </div>
                                                        <c:if test="${not empty error.emptyPronvince}">
                                                            <font color="red">
                                                            ${error.emptyPronvince}
                                                            </font><br/>
                                                        </c:if>
                                                    </div>

                                                    <div class="profile-detail">
                                                        <div class="row">
                                                            <div class="content col-md-4" style="float: left;">
                                                                <lable>District</lable>
                                                            </div>
                                                            <div class="input-text col-md-8">
                                                                <input type="text" name="txtDistrict" value="${value.getDistrict()}"
                                                                       placeholder="Nhập Phường, Quận " /> 

                                                            </div>
                                                        </div>
                                                        <c:if test="${not empty error.emptyDistrict}">
                                                            <font color="red">
                                                            ${error.emptyDistrict}
                                                            </font><br/>
                                                        </c:if>
                                                    </div>

                                                    <div class="profile-detail">
                                                        <div class="row">
                                                            <div class="content col-md-4" style="float: left;">
                                                                <lable>Ward</lable>
                                                            </div>
                                                            <div class="input-text col-md-8">
                                                                <input type="text" name="txtWard" value="${value.getWard()}"
                                                                       placeholder="Nhập Tỉnh, Thành phố" /> 

                                                            </div>
                                                        </div>
                                                        <c:if test="${not empty error.emptyWard}">
                                                            <font color="red">
                                                            ${error.emptyWard}
                                                            </font><br/>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="update-button">
                                            <button type="submit" class="save-button">Save</button>
                                        </div>
                                    </div>
                                    <div class="col-md-2">

                                    </div>
                                </div>
                            </div>
                        </form>

                    </c:forEach>
                </c:if>
            </c:if>
        </c:if>




        </br> <a href="LogoutAccountServlet"> Log Out</a>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
                integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.26.1/axios.min.js"
                integrity="sha512-bPh3uwgU5qEMipS/VOmRqynnMXGGSRv+72H/N260MQeXZIK4PG48401Bsby9Nq5P5fz7hy5UGNmC/W1Z51h2GQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="asset/province.js"></script>
    </body>
</html>
