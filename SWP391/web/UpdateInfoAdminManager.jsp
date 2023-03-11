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
        <!--<link  href="asset/css/styleUpdate.css" rel="stylesheet">-->
        <link  href="asset/css/stylecheck.css" rel="stylesheet">
        <link href="asset/css/styletest.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />      
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>

        <title>Update Info</title>
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
                            <a class="nav-link" href="ShowDashBoard">
                                <div class="sb-nav-link-icon" ><i class="fas fa-tachometer-alt"></i></div>
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
                        <div class="small">Logged in as:  ${dto.fullName}</div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <div class="card mb-4">
                            <div class="card-body">

                                <c:if test="${not empty sessionScope.USER_INFO}">
                                    <c:set value="${sessionScope.USER_INFO}" var="user"/>
                                    <c:set var="error" value="${requestScope.UP_ERROR}"/> 
                                    <div class="title"> Update User ${user.getFullName()} </div>
                                    <form action="UpdateUserInfoServelt" method="POST">
                                        <div class="user-detail row" style="margin-top: 30px;">
                                            <div class="col-md-6">
                                                <div class="user_info">
                                                    <span>
                                                        User ID:
                                                    </span>
                                                    <input type="hidden" name="userid" value="${user.getUserID()}"/>
                                                    <input type="text" name="userid" value="${user.getUserID()}" disabled="disabled"/>
                                                </div>
                                                <div class="user_info">
                                                    <span>
                                                        Full Name : 
                                                    </span>
                                                    <input type="text" name="fullname" value="${user.getFullName()}" />
                                                    <c:if test="${not empty error.notEnoughWordFullName}">
                                                        <font color="red">
                                                        ${error.notEnoughWordFullName}
                                                        </font><br/>
                                                    </c:if>

                                                </div>
                                                <div class="user_info">
                                                    <span>
                                                        Phone: 
                                                    </span>
                                                    <input type="text" name="phone" value="${user.getPhone()}" />
                                                    <c:if test="${not empty error.emptyPhone}">
                                                        <font color="red">
                                                        ${error.emptyPhone}
                                                        </font><br/>
                                                    </c:if>
                                                </div>
                                                <div class="user_info">
                                                    <span>
                                                        Gender:  
                                                    </span>
                                                    <input type="text" name="gender" value="${user.getGender()}" disabled="disabled"/>
                                                </div >                                             
                                            </div>

                                            <div class="col-md-6">
                                                <div class="user_info">
                                                    <span>
                                                        User Name: 
                                                    </span>
                                                    <input type="text" name="username" value="${user.getUserName()}" disabled="disabled" />
                                                </div>
                                                <div class="user_info">
                                                    <span>
                                                        Pass Word: 
                                                    </span>
                                                    <input type="text" name="password" value="${user.getPassWord()}" />
                                                    <c:if test="${not empty error.notEnoughWordPassWord}">
                                                        <font color="red">
                                                        ${error.notEnoughWordPassWord}
                                                        </font><br/>
                                                    </c:if>
                                                </div>
                                                <div class="user_info">
                                                    <span>
                                                        Email:  
                                                    </span>
                                                    <input type="text" name="email" value="${user.getEmail()}" disabled="disabled" />
                                                </div>
                                                <div class="user_info">
                                                    <span>
                                                        Role:  
                                                        <c:choose>
                                                            <c:when test="${user.getRoleID() == 2}">Manager</c:when>
                                                            <c:when test="${user.getRoleID() == 3}">User</c:when>
                                                            <c:otherwise>Unknown</c:otherwise>
                                                        </c:choose>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="button text-center">                 
                                            <input type="submit" value="Save" name="btAction"/>
                                        </div>
                                    </c:if>
                                </form>
                            </div> 
                        </div>
                    </div>
                </main>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="asset/js/slideBar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="asset/js/text.js"></script>
        <script src="asset/js/datatables.js"></script>
        <link href="asset/js/datatables.min.js"/>
    </body>
</html>
