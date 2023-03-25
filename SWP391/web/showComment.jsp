<%-- 
    Document   : ShowDetailReport
    Created on : Mar 4, 2023, 10:23:19 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Tables - Report</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="asset/css/styletest.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>

    </head>

    <body class="sb-nav-fixed">

        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.html">VDTH STORE</a>
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
                            <a class="nav-link collapsed" href="ShowAllCommentServlet" >
                                <div class="sb-nav-link-icon"><i class="far fa-comments"></i></div>
                                Comment
                            </a>
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
                        <h1 class="mt-4">Manage comment</h1>

                        <div class="card mb-4">
                            
                            <div class="card-body">
                                <c:if test="${not empty requestScope.COMMENT}">
                                    <c:set var="des" value="${requestScope.COMMENT}"/>
                                    <table id="datatablesSimple">
                                        <thead>
                                            <tr>
<!--                                                <th>Comment ID </th>-->
                                                <th>Full Name</th>
                                                <th>Product Name</th>
                                                <th>Date </th>
                                                <th>Description</th>
                                                <th>Status</th>
                                                <th>Function</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="dto" items="${des}">
                                                <tr>
<!--                                                    <td>${dto.getCommentID()}</td>-->
                                                    <td>${dto.getFullName()}</td>
                                                    <td>${dto.getProductName()}</td>
                                                    <td>${dto.getDate()}</td>
                                                    <td>${dto.getDescription()}</td>
                                                    <td>                                                                                                           
                                                        <c:choose>
                                                            <c:when test="${dto.isStatus() == true}">Available</c:when>
                                                            <c:when test="${dto.isStatus() == false}">Unavailable</c:when>

                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <c:if test="${dto.isStatus() == true}">
                                                            <c:url var="urlHide" value="HideCommentServlet">

                                                                <c:param name="txtCommentID" value="${dto.getCommentID()}"/>
                                                            </c:url>
                                                            <a href="${urlHide}"> Hide Comment </a>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>

                                </c:if>
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

