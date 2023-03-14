<%-- 
    Document   : showAddress
    Created on : Feb 23, 2023, 9:09:00 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>SWP team project</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <link rel="stylesheet" href="asset/css/style.css">
        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">

        <title>JSP Page</title>
    </head>

    <body>
        <!---------HEADER-------->
        <  <jsp:include page="header.jsp"/>

        <!---------profile-------->
        <c:if test="${not empty requestScope.USERS}" >
            <c:set var="user" value="${requestScope.USERS}"/>
            <div class="row content-detail" style="height: 100%;" >
                <div class="avt col-md-3">
                    <div class="user">
                        <img src="asset/images/useravatar/${user.getPicture()}" alt="">
                        <h3 class="name">${user.fullName}</h3>
                    </div>

                    <nav class="navbar" style="margin : auto;">
                        <ul>
                            <li><a href="ShowProfileServlet">Account information</a></li>
                            <li><a href="ShowOrderTrackingServlet">Order management</a></li>
                            <li><a href="ShowAddressServlet">Address number</a></li>
                            <li><a href="https://www.facebook.com/people/VDTH/100090772202536/">Contact us</a></li>

                        </ul>
                    </nav>
                </div>



                <div class="col-md-9">
                    <div class="container-fluid">
                        <div class="row ">
                            <div class="col-md-12 " >
                                <div>
                                    <div class=" row" >
                                        <div class="col-md-6" >
                                            <h3>Address number</h3>
                                        </div>

                                        <div class="center col-md-6 " >
                                            <!-- Nút mở popup thứ nhất -->
                                            <button type="button" class="btn btn-primary "
                                                    data-toggle="modal" data-target="#popup1">
                                                Add address
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <c:if test="${not empty requestScope.INFO}">
                                <c:set var="address" value="${requestScope.INFO}"/>
                                <c:if test="${not empty address}">
                                    <div class="testimonials-box-container-fluid">
                                        <div class="row box">
                                            <c:forEach var="add" items="${address}" varStatus="loop">

                                                <div class="col-md-6">
                                                    <div class="testimonials-box">
                                                        <div class="box-top">
                                                            <!--profile------------->
                                                            <div class="profile">
                                                                <div class="profile-img">
                                                                    <img src="asset/images/useravatar/${user.getPicture()}">
                                                                </div>
                                                                <div class="name-user">
                                                                    <strong>${user.getFullName()}</strong>
                                                                </div>
                                                            </div>
                                                            <!--review------------->
                                                            <div class="review">

                                                                <c:url var="urlupdate" value="GetAddressServlet" >
                                                                    <c:param name="btAction" value="updateadd" />
                                                                    <c:param name="txtaddid" value="${add.getAddressID()}" />
                                                                </c:url>

                                                                <button type="button" >
                                                                    <a class="fa fa-pencil" href="${urlupdate}"></a>

                                                                </button>

                                                                <c:url var="urldelete" value="DeleteAddressServlet" >
                                                                    <c:param name="btAction" value="Delete" />
                                                                    <c:param name="txtaddid" value="${add.getAddressID()}" />
                                                                </c:url>

                                                                <button type="button" onclick="confirmDelete('${urldelete}')">
                                                                    <a class="fa fa-trash"></a>
                                                                </button>


                                                            </div>
                                                        </div>
                                                        <!--comment------------->
                                                        <div class="client-comment">
                                                            <div class="row">
                                                                <input type="hidden" name="txtaddid" value="${add.getAddressID()}" />
                                                                <div class="col-md-6">
                                                                    <p>Phone number: ${user.getPhone()}</p>                                                                    
                                                                    <p>Province: ${add.getProvice()}</p>
                                                                    <p>Address: ${add.getStreet()}</p>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <p>District: ${add.getDistrict()}</p>
                                                                    <p>Ward: ${add.getWard()}</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>


                                            </c:forEach>

                                        </div>
                                    </div>
                                </c:if>
                            </c:if>


                            <!--popup-->



                            <div class="modal fade" id="popup1" tabindex="-1" role="dialog" aria-labelledby="popup1Label"
                                 aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="popup1Label">Add address</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Đóng">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">

                                            <form action="AddAddressController" method="POST">

                                                <input type="text" name="txtuserid" value="${user.getUserID()}" 
                                                       style="display: none"/>

                                                <div class="user-detail">
                                                    <div class="input-box">
                                                        <span class="detail">Full name</span>
                                                        <input type="text" placeholder="Enter your name" name="txtfullName"
                                                               value="${user.getFullName()}" disabled="disabled">
                                                    </div>
                                                    <div class="input-box">
                                                        <span class="detail">Phone</span>
                                                        <input type="number" placeholder="Enter your phone" name="txtPhone"
                                                               value="${user.getPhone()}" disabled="disabled">
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
                                                        <span class="detail">Address</span>
                                                        <input type="text" placeholder="Enter your address" name="txtAddress"
                                                               value=""  required>
                                                    </div>
                                                </div>
                                                <input type="submit" value="Add Address" name="btAction" class="center-button"/>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" 
                                                    data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>                            
                            <!modal-->                        


                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <!---------Footer-------->

        <footer>
            <div class="footer-content" style="width: 100%;">
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
            </div>
        </footer>


    </body>

    <script src="asset/js/Jaddress.js"></script>
    <script src="asset/js/apiProvince.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"
    integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
    crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
    crossorigin="anonymous"></script>

</html>
