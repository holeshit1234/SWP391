<%-- 
    Document   : profileAdminManager
    Created on : Mar 2, 2023, 6:21:39 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!--<link href="asset/css/bootstrap.min.css" rel="stylesheet">-->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
     
        <link href="asset/css/styletest.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />      
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <link href="asset/css/stylePageAdmin.css" rel="stylesheet">
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
                                    <a class="nav-link" href="layout-static.html">Product</a>
                                    <a class="nav-link" href="layout-sidenav-light.html">Category</a>
                                    <a class="nav-link" href="layout-sidenav-light.html">Brand</a>
                                    <a class="nav-link" href="layout-sidenav-light.html">Payment</a>
                                </nav>
                            </div>   

                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseOrder" aria-expanded="false" aria-controls="collapseOrder">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Order Manage
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseOrder" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="layout-static.html">Comfirm</a>
                                    <a class="nav-link" href="layout-sidenav-light.html">Wait to Comfirm</a>
                                </nav>
                            </div> 


                            <a class="nav-link" href="#" >
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Report Manage
                            </a>                        
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
                                <c:if test="${not empty sessionScope.USERS}">
                                    <c:set var="info_user" value="${sessionScope.USERS}"/>  
                                    <c:set var="error" value="${requestScope.UP_ERROR}"/>       
                                    <div class="title text-center">My account</div>
                                    <form action="UpdateProfileAdminManager" method="POST">
                                        <div class="user-detail row text-center">
                                            <div class="user-info col-md-6">
                                                <div class="input-box">
                                                    <span class="detail">Full name:</span>
                                                    <input type="text" placeholder="Enter your username" 
                                                           name="txtFullName" value="${info_user.fullName}"/>
                                                    <c:if test="${not empty error.notEnoughWordFullName}">
                                                        <font color="red">
                                                        ${error.notEnoughWordFullName}
                                                        </font><br/>
                                                    </c:if>
                                                </div>	                                                                       
                                                <div class="input-box">
                                                    <span class="detail">Date of birth:</span>
                                                    <input type="date" placeholder="Enter Date of birth" name="txtDOB"
                                                           value="${info_user.DOB}" 
                                                           placeholder="Nhập đầy đủ tên"/>
                                                </div>
                                                <div class="input-box">
                                                    <span class="detail">Phone:</span>
                                                    <input type="text" placeholder="Enter your phone" 
                                                           value="${info_user.phone}"
                                                           name="txtPhone"  />
                                                    <c:if test="${not empty error.emptyPhone}">
                                                        <font color="red">
                                                        ${error.emptyPhone}
                                                        </font><br/>
                                                    </c:if>
                                                </div>
                                                <div class="input-box">
                                                    <span class="detail">Password:</span>
                                                    <input type="password" placeholder="Enter your password" 
                                                           name="txtPassWord" value="${info_user.passWord}"/>
                                                    <c:if test="${not empty error.notEnoughWordPassWord}">
                                                        <font color="red">
                                                        ${error.notEnoughWordPassWord}
                                                        </font><br/>
                                                    </c:if>
                                                </div>
                                                <div class="input-box">
                                                    <span class="detail">Gender:</span>
                                                    <input type="text" placeholder="Enter your password" 
                                                           name="txtGender" value="${info_user.gender}"
                                                           disabled="disabled" />
                                                </div>	
                                                <div class="input-box">
                                                    <span class="detail">Email:</span>
                                                    <input type="text" placeholder="Enter your email" 
                                                           name="txtEmail" value="${info_user.email}" disabled="disabled" />
                                                </div>
                                            </div>
                                            <div class="user-address col-md-6 text-center">
                                                <c:forEach var="value" items="${sessionScope.INFO}">   
                                                    <div class="profile-detail">                                                                 
                                                        <div class="input-box">
                                                            <span class="detail">Street:</span>
                                                            <input type="text" name="txtStreet" value="${value.getStreet()}"
                                                                   placeholder="Nhập số nhà, tên đường" /> 
                                                            <c:if test="${not empty error.emptyStreet}">
                                                                <font color="red">
                                                                ${error.emptyStreet}
                                                                </font><br/>
                                                            </c:if>
                                                        </div>

                                                        <div class="input-box">
                                                            <span class="detail">Province:</span>
                                                            <input type="text" name="txtProvince" value="${value.getProvice()}"
                                                                   placeholder="Nhập Phường, Quận " /> 
                                                            <c:if test="${not empty error.emptyPronvince}">
                                                                <font color="red">
                                                                ${error.emptyPronvince}
                                                                </font><br/>
                                                            </c:if>
                                                        </div>
                                                        <div class="input-box">
                                                            <span class="detail">District:</span>
                                                            <input type="text" name="txtDistrict" value="${value.getDistrict()}"
                                                                   placeholder="Nhập Phường, Quận " /> 
                                                            <c:if test="${not empty error.emptyDistrict}">
                                                                <font color="red">
                                                                ${error.emptyDistrict}
                                                                </font><br/>
                                                            </c:if>
                                                        </div>
                                                        <div class="input-box">
                                                            <span class="detail">Ward:</span>
                                                            <input type="text" name="txtWard" value="${value.getWard()}"
                                                                   placeholder="Nhập Tỉnh, Thành phố" /> 
                                                            <c:if test="${not empty error.emptyWard}">
                                                                <font color="red">
                                                                ${error.emptyWard}
                                                                </font><br/>
                                                            </c:if>
                                                        </div>
                                                    </div>  
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                    <div class="button text-center">                 
                                        <input type="submit" value="Save" name="btAction"/>
                                    </div>
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
