<!DOCTYPE html>
<html lang="en">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <head>
        <title>SWP team project</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link href="asset/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="asset/css/stylesignup.css">

        <link rel="shortcut icon" href="images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    </head>

    <body>
        <!---------HEADER-------->
        <jsp:include page="header.jsp"/>
        <!---------sign-up-form-------->
        <section class="sigup">
            <div class="container">
                <div class="title">Sign up form</div>
                <form action="SignUpPageServlet" method="POST">
                    <c:set var="message" value="${requestScope.MESSAGE}" />
                    <c:if test="${not empty message}">
                        <font color='red'>
                        ${message}
                        </font><br/>
                    </c:if>
                    <c:set var="errors" value="${requestScope.ERROR}" />
                    <div class="user-detail">
                        <div class="input-box">
                            <span class="detail">User name *</span>
                            <input type="text" name="txtUsername" placeholder="Enter your username" required  value="" /> 
                            <c:if test="${not empty errors.usernameLengthErr}">
                                <font color='red'>
                                ${errors.usernameLengthErr}
                                </font><br/>
                            </c:if>
                        </div>
                        <div class="input-box">
                            <span class="detail">Full name *</span>
                            <input type="text" name="txtFullname"  value="" placeholder="Enter your full name" required/> 
                            <c:if test="${not empty errors.fullnameLengthErr}">
                                <font color='red'>
                                ${errors.fullnameLengthErr}
                                </font><br/>
                            </c:if>
                        </div>
                        <div class="input-box">
                            <span class="detail">Password *</span>
                            <input type="password" name="txtPassword"  placeholder="Enter your password" required value="" /> 
                            <c:if test="${not empty errors.passwordLengthErr}">
                                <font color='red'>
                                ${errors.passwordLengthErr}
                                </font><br/>
                            </c:if>
                        </div>
                        <div class="input-box">
                            <span class="detail">Confirm password *</span>
                            <input type="password" name="txtConfirm" value="" placeholder="Confirm your password" c/> 
                            <c:if test="${not empty errors.confirmNotMatchErr}">
                                <font color='red'>
                                ${errors.confirmNotMatchErr}
                                </font><br/>
                            </c:if>
                        </div>

                        <div class="input-box">
                            <span class="detail">Email</span>
                            <input type="text" name="txtEmail" id="txtEmail" value="${param.txtEmail}" placeholder="example@gmail.com" required>
                            <span class="error-message" style="display: none; color: red"></span>
                        </div>

                        <div class="input-box">
                            <span class="detail">Phone</span>
                            <input type="text" name="txtPhone" value="${param.txtPhone}" placeholder="Enter your phone" required>
                            <span class="error-message phone" style="display: none;"></span>
                        </div>

                        <div class="input-box">
                            <span class="detail">Date of birth</span>
                            <input type="date" name="txtDOB" value="" placeholder="Enter Date of birth" required>
                        </div>

                        <div class="input-box">
                            <label class="field-label" for="stored-city">Province</label><br>
                            <select class="field-input" id="stored-city" required>
                                <option class="field-input-item" data-name="" value="">
                                    Choose Province / city
                                    <!-- Ở ĐÂY OPTION MÌNH CÓ THỂ TRUYỀN ĐƯỢC data-properties={"nội dung"} và value ={} -->
                                </option>
                            </select>
                            <input type="hidden" id="txtProvinceDataName" name="txtProvinceDataName" value=""/>
                        </div>

                        <!-- DISTRICT -->
                        <div class="input-box">
                            <label class="field-label" for="stored-district">District</label><br>
                            <select class="field-input" id="stored-district" required>
                                <option class="field-input-item" data-name="" value="">
                                    Choose District
                                    <!-- Ở ĐÂY OPTION MÌNH CÓ THỂ TRUYỀN ĐƯỢC data-properties={"nội dung"} và value ={} -->
                                </option>
                            </select>
                            <input type="hidden" id="txtDistrictDataName" name="txtDistrictDataName" value=""/>
                        </div>
                        <div class="input-box">
                            <label class="field-label" for="stored-ward">Ward</label><br>
                            <select class="field-input" id="stored-ward" required>
                                <option class="field-input-item" data-name="" value="">
                                    Choose Wards
                                    <!-- Ở ĐÂY OPTION MÌNH CÓ THỂ TRUYỀN ĐƯỢC data-properties={"nội dung"} và value ={} -->
                                </option>
                            </select>
                            <input type="hidden" id="txtWardDataName" name="txtWardDataName" value=""/>
                        </div>

                        <!-- WARD -->
                        <div class="input-box1">
                            <span class="detail">Street</span>
                            <input type="text" placeholder="Enter your address" name="txtStreet" value="" required>
                        </div>	
                    </div>



                    <div class="gender-detail">
                        <input type="radio" name="gender" value="Nam" id="dot-1">
                        <input type="radio" name="gender" value="Nu" id="dot-2">
                        <input type="radio" name="gender" value="Khac" id="dot-3">
                        <span class="gender-title">Gender</span>
                        <div class="category">
                            <label for="dot-1">
                                <span class="dot one" name="txtGender" value="Nam" ></span>
                                <span class="gender">Male</span>
                            </label>
                            <label for="dot-2">
                                <span class="dot two" name="txtGender" value="Nu"></span>
                                <span class="gender">Female</span>
                            </label>
                            <label for="dot-3">
                                <span class="dot three" name="txtGender" value="Khac"></span>
                                <span class="gender">Orther</span>
                            </label>
                        </div>
                    </div>
                    <div class="button">
                        <input type="submit" value="Register" name="btAction">
                    </div>
                </form>
            </div>
        </section>
        <!---------Footer-------->
        <footer>
            <div class="footer-top">
                <li><a href="">Contact</a></li>
                <li><a href="">Recruit</a></li>
                <li><a href="">Introduce</a></li>
                <li>
                    <a href="" class="fa fa-facebook"></a>
                    <a href="" class="fa fa-twitter"></a>
                    <a href="" class="fa fa-youtube"></a>
                </li>
            </div>
            <div class="footer-center">
                <p>
                    Contact phone number: 0111111111 <br>
                    Registration address: ??????????? <br>
                    Order online: <b>022222222</b>
                </p>
            </div>
            <div class="footer-bottom">
                ©IVYmoda All rights reserved
            </div>
        </footer>
        <script src="asset/js/Format.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
                integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.26.1/axios.min.js"
                integrity="sha512-bPh3uwgU5qEMipS/VOmRqynnMXGGSRv+72H/N260MQeXZIK4PG48401Bsby9Nq5P5fz7hy5UGNmC/W1Z51h2GQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="asset/js/apiProvince.js"></script>

    </body>

</html>