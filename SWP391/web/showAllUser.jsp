<%-- 
    Document   : test
    Created on : Feb 25, 2023, 7:16:18 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Tables - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="asset/css/styletest.css" rel="stylesheet" />
        <link href="asset/css/showAllUser.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
       
    </head>
    <body class="sb-nav-fixed">

        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="ShowDashBoard">VDTH STORE</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
             <a class="navbar-brand ps-3" >Manage user</a>
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
                        <h1 class="mt-4">Manage User</h1>

                        <c:if test="${not empty sessionScope.USER}">
                            <c:set var="user" value="${sessionScope.USER}"/>
                            <c:if test="${user.getRoleID() == 1}">
                                <div style="margin-bottom: 10px">
                                    <div >
                                        <a type="submit" href="AddNewUser.jsp">Add New User</a>
                                    </div>
                                </div>
                            </c:if>
                        </c:if>
                        <div class="card mb-4">

                            <div class="card-body">
                                <c:if test="${not empty requestScope.USER_INFO}">
                                    <c:set var="user_info" value="${requestScope.USER_INFO}"/>
                                    <table id="datatablesSimple">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Username</th>
                                                <th>Password</th>
                                                <th>Email</th>
                                                <th>Fullname</th>
                                                <th>Gender</th>
                                                <th>Role</th>
                                                <th>Function</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>ID</th>
                                                <th>Username</th>
                                                <th>Password</th>
                                                <th>Email</th>
                                                <th>Fullname</th>
                                                <th>Gender</th>
                                                <th>Role</th>
                                                <th>Function</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:forEach var="dto" items="${user_info}">
                                                <tr>
                                                    <td>${dto.getUserID()}</td>
                                                    <td>${dto.userName}</td>
                                                    <td>${dto.passWord}</td>
                                                    <td>${dto.email}</td>
                                                    <td>${dto.fullName}</td>
                                                    <td class="gender" >${dto.gender}</td>
                                                    <td class="role">
                                                        <c:choose>
                                                            <c:when test="${dto.getRoleID() == 2}">Manager</c:when>
                                                            <c:when test="${dto.getRoleID() == 3}">User</c:when>
                                                            <c:otherwise>Unknown</c:otherwise>
                                                        </c:choose>
                                                    </td>

                                                    <td>
                                                        <c:url var="urlInfo" value="GetUserUpdate">
                                                            <c:param name="btAction" value="UpdateInfo"/>
                                                            <c:param name="userid" value="${dto.getUserID()}"/>
                                                        </c:url>
                                                        <a type="button" value="Update"  
                                                           href="${urlInfo}">Update </a> <br>

                                                        <c:url var="urlAddress" value="GetAddressServelts">
                                                            <c:param name="btAction" value="Show Address"/>
                                                            <c:param name="userid" value="${dto.getUserID()}"/>
                                                        </c:url>
                                                        <a type="button" 
                                                           href="${urlAddress}">Show Address </a> <br>

                                                        <c:if test="${not empty sessionScope.USER}">
                                                            <c:set var="user" value="${sessionScope.USER}"/>
                                                            <c:if test="${user.getRoleID() == 1}">
                                                                <c:url var="urlDelete" value="DeleteUserServlet">                                                                  
                                                                    <c:param name="userid" value="${dto.getUserID()}"/>
                                                                </c:url>

                                                                <button type="button" onclick="confirmDelete('${urlDelete}')">
                                                                    Delete User
                                                                </button> <br>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${not empty sessionScope.USER}">
                                                            <c:set var="user" value="${sessionScope.USER}"/>
                                                            <c:if test="${user.getRoleID() == 2}">
                                                                <c:url var="urlBanUser" value="BanUserServletAtUserPage" >
                                                                    <c:param name="userID" value="${dto.getUserID()}"/>
                                                                </c:url>

                                                                <button type="button" onclick="confirmDelete('${urlBanUser}')">
                                                                    Ban User
                                                                </button> <br>

                                                            </c:if>
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

        <script src="asset/js/Jaddress.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="asset/js/slideBar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="asset/js/text.js"></script>
        <script src="asset/js/datatables.js"></script>
        <link href="asset/js/datatables.min.js"/>
    </body>
</html>

