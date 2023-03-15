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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
              crossorigin="anonymous">

        <link rel="stylesheet" href="asset/css/resignGG.css">

        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>

        <!---------HEADER-------->
        <header>
            <div class="logo">
                <img src="asset/images/logo-circle.png">
            </div>
            <div class="menu">
                <li><a href="showProductByGenderServlet?gender=Nam">Nam</a></li>
                <li><a href="showProductByGenderServlet?gender=Nữ">Nữ</a></li>
                <li><a href="showProductByGenderServlet?gender=Unisex">Unisex</a></li>
            </div>
            <div class="orther">
                <li><input placeholder="Search" type="text"><i class="fa fa-search"></i></li>
                <li><a class="fa fa-user" href="login.html"></a></li>
                <li><a class="fa fa-shopping-bag" href=""></a></li>
            </div>
        </header>
        <!---------resingin-GG-form-------->
        <div class="body">
            <div class="container">
                <div class="title">Resign GG Account</div>
                <form action="writeInformationGgServlet" method="POST">
                    <div class="user-detail">
                        <div class="input-box">
                            <span class="detail">Phone</span>
                            <input type="text" name="txtPhone" value="${param.txtPhone}" placeholder="Enter your phone" required>
                            <span class="error-message phone" style="display: none;"></span>
                        </div>
                        <div class="input-box">
                            <span class="detail">DOB</span>
                            <input type="date" name="txtDOB" value="${param.txtDOB}" required/><br>
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
                        <div class="input-box">
                            <span class="detail">Street</span>
                            <input type="text" name="txtStreet" value="${param.txtProvince}" required/><br>
                        </div>
                    </div>

                    <div class="gender-detail">
                        <input type="radio" name="txtGender" value="Nam" id="dot-1"/>
                        <input type="radio" name="txtGender" value="Nữ" id="dot-2"/>
                        <input type="radio" name="txtGender" value="Other" id="dot-3"/>
                        <span class="gender-title">Gender</span>
                        <div class="category">
                            <label for="dot-1">
                                <span class="dot one"></span>
                                <span class="gender">Male</span>
                            </label>
                            <label for="dot-2">
                                <span class="dot two"></span>
                                <span class="gender">Female</span>
                            </label>
                            <label for="dot-3">
                                <span class="dot three"></span>
                                <span class="gender">Orther</span>
                            </label>
                        </div>
                    </div>

                    <div class="button">
                        <input type="submit" value="Summit" name="btAction" />
                    </div>

                </form>
            </div>  
        </div>

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
         <script src="asset/js/apiProvince.js"></script> 
    </body>
</html>
