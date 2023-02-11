<%-- 
    Document   : Profile
    Created on : Feb 3, 2023, 12:35:48 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
    </head>
    <body>
      <form action="UpdateProfileController" method="POST">
                    <div class="profiles-container">
                        <div class="row" style="display: block;">
                            <div class="col-md-2">
                                <p>thông tin tài khoản</p>
                                <p>thông tin tài khoản</p>
                                <p>thông tin tài khoản</p>
                            </div>

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
                                                        <input type="text" name="txtFullName" value="${user.fullName}"
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
                                                        <input type="text" name="txtEmail" value="${user.email}"
                                                               placeholder="Nhập đầy đủ tên" />
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="profile-detail">
                                                <div class="row">
                                                    <div class="content col-md-4" style="float: left;">
                                                        <lable>Phone</lable>
                                                    </div>
                                                    <div class="input-text col-md-8">
                                                        <input type="text" name="txtPhone" value="${user.phone}"
                                                               placeholder="Nhập Số điện thoại" />
                                                    </div>
                                                </div>

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

                                                </div>

                                                <div class="profile-detail">
                                                    <div class="row">
                                                        <div class="content col-md-4" style="float: left;">
                                                            <lable>Province</lable>
                                                        </div>
                                                        <div class="input-text col-md-8">

                                                            <select name="txtProvince" id="province">
                                                                <option value="" >chọn tỉnh</option>
                                                            </select>
                                                        </div>
                                                    </div>

                                                </div>

                                                <div class="profile-detail">
                                                    <div class="row">
                                                        <div class="content col-md-4" style="float: left;">
                                                            <lable>District</lable>
                                                        </div>
                                                        <div class="input-text col-md-8">
                                                            <!-- <input type="text" name="txtWard" value=""
                                                                placeholder="Nhập Phường, Quận " /> -->
                                                            <select name="txtDistrict" id="district">
                                                                <option value="">chọn quận</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="profile-detail">
                                                    <div class="row">
                                                        <div class="content col-md-4" style="float: left;">
                                                            <lable>Ward</lable>
                                                        </div>
                                                        <div class="input-text col-md-8">
                                                            <!-- <input type="text" name="txtProvince" value="$"
                                                                placeholder="Nhập Tỉnh, Thành phố" /> -->
                                                            <select name="txtWard" id="ward">
                                                                <option value="">chọn phường</option>
                                                            </select>
                                                        </div>
                                                    </div>
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




        </br> <a href="LogoutAccountServlet"> Log Out</a>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
                integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.26.1/axios.min.js"
                integrity="sha512-bPh3uwgU5qEMipS/VOmRqynnMXGGSRv+72H/N260MQeXZIK4PG48401Bsby9Nq5P5fz7hy5UGNmC/W1Z51h2GQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="asset/province.js"></script>


        </br> <a href="LogoutAccountServlet"> Log Out</a>
    </body>
</html>
