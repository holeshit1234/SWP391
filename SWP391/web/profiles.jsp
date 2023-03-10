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
        <link rel="stylesheet" href="asset/css/styleprofile.css">


        <!--        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
                      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
                      crossorigin="anonymous">-->

        <!--<link href="asset/css/bootstrap.min.css" rel="stylesheet">-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        <title>JSP Page</title>
    </head>
    <body>

        <jsp:include page="header.jsp"/>

        <!--body-->

        <c:if test="${not empty requestScope.USERS}">
            <c:set var="info_user" value="${requestScope.USERS}"/>             
            <div class="context-profile" style="overflow-x: hidden;" >
                <div class="row">
                    <div class="new">
                        <div class="avt col-md-4">
                            <div class="user">
                                <img src="asset/images/useravatar/${info_user.picture}" alt="">                                        
                                <h3 class="name">${info_user.fullName}</h3>              
                            </div>

                            <nav class="navbar">
                                <ul>
                                    <li><a href="ShowProfileServlet">Account information</a></li>
                                    <li><a href="ShowOrderTrackingServlet">Order management</a></li>
                                    <li><a href="ShowAddressServlet">Address number</a></li>
                                    <li><a href="https://www.facebook.com/people/VDTH/100090772202536/">Contact us</a></li>

                                </ul>
                            </nav>
                        </div>


                        <c:set var="error" value="${requestScope.UP_ERROR}"/>
                        <div class="sigup col-md-8">
                            <div class="container ">
                                <div class="title text-center">My account</div>
                                <form action="UpdateProfileServlet" method="POST">
                                    <div class="user-detail row">
                                        <div class="col-md-6">
                                            <div class="input-box">
                                                <span class="detail">Full name</span>
                                                <input type="text" placeholder="Enter your username" 
                                                       name="txtFullName" value="${info_user.fullName}"/>

                                                <c:if test="${not empty error.notEnoughWordFullName}">
                                                    <font color="red">
                                                    ${error.notEnoughWordFullName}
                                                    </font><br/>
                                                </c:if>

                                            </div>	                                                                       

                                            <div class="input-box">
                                                <span class="detail">Date of birth</span>
                                                <input type="date" placeholder="Enter Date of birth" name="txtDOB"
                                                       value="${info_user.DOB}" 
                                                       placeholder="Nhập đầy đủ tên"/>
                                            </div>
                                            <div class="input-box">
                                                <span class="detail">Phone</span>
                                                <input type="text" placeholder="Enter your phone" 
                                                       value="${info_user.phone}"
                                                       name="txtPhone"  />

                                                <c:if test="${not empty error.emptyPhone}">
                                                    <font color="red">
                                                    ${error.emptyPhone}
                                                    </font><br/>
                                                </c:if>
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="input-box">
                                                <span class="detail">Password</span>
                                                <input type="password" placeholder="Enter your password" 
                                                       name="txtPassWord" value="${info_user.passWord}"/>

                                                <c:if test="${not empty error.notEnoughWordPassWord}">
                                                    <font color="red">
                                                    ${error.notEnoughWordPassWord}
                                                    </font><br/>
                                                </c:if>
                                            </div>

                                            <div class="input-box">
                                                <span class="detail">Gender</span>
                                                <input type="text" placeholder="Enter your password" 
                                                       name="txtGender" value="${info_user.gender}"
                                                       disabled="disabled" />
                                            </div>	

                                            <div class="input-box">
                                                <span class="detail">Email</span>
                                                <input type="text" placeholder="Enter your email" 
                                                       name="txtEmail" value="${info_user.email}" disabled="disabled" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="button">                 
                                        <input type="submit" value="Save Change" name="btAction" 
                                               />
                                    </div>
                                </form> 
                                <div class="button">
                                    <input type="submit" value="Change Avatar" data-toggle="modal" data-target="#changeAvatar"/>
                                </div>
                                <!--                            <div class="container">
                                                                <h2>Small Modal</h2>
                                                                 Trigger the modal with a button 
                                                                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Small Modal</button>-->

                                <!-- Modal -->
                                <div class="modal fade" id="changeAvatar" role="dialog">
                                    <div class="modal-dialog modal-sm">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Modal Header</h4>
                                            </div>
                                            <div class="modal-body">
                                                <form action="SavePhotoServlet" method="Post" enctype="multipart/form-data">

                                                    <input type="text" name="txtuserid" value="${info_user.getUserID()}" style="display: none;" />
                                                    <img src="asset/images/useravatar/${info_user.getPicture()}" alt="" 
                                                         style="align-items: center; border-radius: 50%; max-width: 150px; ">   


                                                    <input type="file" name="txtPicture" class="form-control"/>
                                                    <input type="submit" value="save photo" name="btAction" />
                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                        <!--</div>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </c:if>

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

        <!--        <script
                    src="https://code.jquery.com/jquery-3.6.3.min.js"
                    integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
                crossorigin="anonymous"></script>
        
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" 
                        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" 
                crossorigin="anonymous"></script>
        
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
                        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" 
                crossorigin="anonymous"></script>-->


    </body>

</html>
