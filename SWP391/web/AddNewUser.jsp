<%-- 
    Document   : ShowUserAddress
    Created on : Mar 3, 2023, 1:29:16 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link href="asset/css/styleAddNewUser.css" rel="stylesheet">
        <link href="asset/css/styletest.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />      
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <title>JSP Page</title>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.html">DHTV STORE</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <c:set var="dto" value="${sessionScope.USER}"/>
                    <font color="white">
                    ${dto.fullName}
                    </font>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="ShowProfileAdminManager">Profiles</a></li>
                        <!--                        <li><a class="dropdown-item" href="#!">Activity Log</a></li>-->
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="LogoutAccountServlet">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="index.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard
                            </a>
                            <div class="sb-sidenav-menu-heading">Manage</div>


                            <a class="nav-link collapsed" href="ShowUserByManagerServlet" >
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                User Manage

                            </a>

                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseProduct" aria-expanded="false" aria-controls="collapseProduct">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Product Manage
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseProduct" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="ShowAllListProductServlet">Product</a>
                                    <a class="nav-link" href="showCategoryServlet">Category</a>
                                    <a class="nav-link" href="showBrandServlet">Brand</a>
                                    <a class="nav-link" href="showSizeServlet">Size</a>
                                    <a class="nav-link" href="GetPaymentMethodServlet">Payment</a>
                                </nav>
                            </div>   

                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseOrder" aria-expanded="false" aria-controls="collapseOrder">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Order Manage
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseOrder" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="showOrderConfirm">Wait to Comfirm</a>
                                    <a class="nav-link" href="showOrder">Order Confirmed</a>
                                    <a class="nav-link" href="showBill">Bill</a>
                                    <a class="nav-link" href="showOrderCancle">Cancle Order</a>
                                </nav>
                            </div> 
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseReport" aria-expanded="false" aria-controls="collapseReport">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Report Manage
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseReport" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="ShowAllReport">Report</a>                              
                                </nav>
                            </div>                       
                        </div>
                    </div>

                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as: </div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="container">
                                    <div class="title">Sign up form</div>
                                    <form action="AddNewUserServlet" method="POST">
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
                                                <input type="text" name="txtUsername" placeholder="Enter your username" required  value="${param.txtUsername}" /> 
                                                <c:if test="${not empty errors.usernameLengthErr}">
                                                    <font color='red'>
                                                    ${errors.usernameLengthErr}
                                                    </font><br/>
                                                </c:if>
                                            </div>
                                            <div class="input-box">
                                                <span class="detail">Full name *</span>
                                                <input type="text" name="txtFullname"  value="${param.txtFulname}" placeholder="Enter your full name" required/> 
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
                                                <span class="detail">Province</span>
                                                <input type="text" name="txtProvince" value="${param.txtProvince}" placeholder="Enter your province" required>
                                            </div>
                                            <div class="input-box">
                                                <span class="detail">District</span>
                                                <input type="text" name="txtDistrict" value="${param.txtDistrict}" placeholder="Enter your District" required>
                                            </div>
                                            <div class="input-box">
                                                <span class="detail">Ward</span>
                                                <input type="text" name="txtWard" value="${param.txtWard}" placeholder="Enter your Ward" required>
                                            </div>

                                            <div class="input-box1">
                                                <span class="detail">Street</span>
                                                <input type="text" placeholder="Enter your address" name="txtStreet" value="${param.txtStreet}" required>
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
                                        <div class="role-detail">
                                            <span class="role" >Role</span>
                                            <select name="txtRole">
                                                <option value="2">STAFF</option>
                                                <option value="3">USER</option>
                                            </select>                                                                       
                                        </div>
                                        <div class="button">
                                            <input type="submit" value="Register" name="btAction">
                                        </div>
                                    </form>
                                </div>


                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <script src="asset/js/Format.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="asset/js/slideBar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="asset/js/text.js"></script>
        <script src="asset/js/datatables.js"></script>
        <link href="asset/js/datatables.min.js"/>
    </body>
</html>
