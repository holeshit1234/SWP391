<%-- 
    Document   : EditProductServletPage
    Created on : Mar 2, 2023, 11:44:08 PM
    Author     : mthin
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
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>

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


                            <a class="nav-link collapsed" href="#" >
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
                              <a class="nav-link" href="showOrder">Wait to Confirm</a>
                                    <a class="nav-link" href="showOrderConfirmed">Confirm</a>
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
                        <h1 class="mt-4">Thêm size</h1>
                        <div class="card mb-4">
                           
                            <jsp:useBean id="daoProductDetail" class="DHTV.product.ProductDetailDAO"/>  
                            <jsp:useBean id="daoSize" class="DHTV.size.SizeDAO"/>  
                            <c:set var="result" value="${requestScope.ADD_NEW_SIZE_PRODUCT}"/>
                            <c:if test="${not empty result}">

                                <form action="UpdateNewSizeServlet" method="POST" onsubmit="return validateForm()">
                                    <c:forEach var="dto" items="${result}">
                                        <input type="hidden" value="${dto.productID}" name="txtProductID">
                                        Ten San Pham: ${dto.productName}
                                        <a class="hidden">${daoProductDetail.getSizeIdById(dto.productID)}</a>
                                        <a class="hidden">${daoSize.showSizeList()}</a><br>
                                        <style>
                                            .hidden {
                                                display: none;
                                            }
                                        </style>
                                        <c:forEach var="size" items="${daoSize.getSizeList()}">
                                            <c:set var="found" value="false" />
                                            <c:forEach var="detail" items="${daoProductDetail.getDetailList()}">
                                                <c:if test="${detail.sizeID == size.sizeID}">
                                                    <c:set var="found" value="true" />
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${found eq false}">
                                                <div class="size-option">
                                                    <input type="checkbox" value="${size.getSizeID()}" name="txtSize" onclick="showForm(this)"/>
                                                    <label>${size.getSizeName()}</label>
                                                </div>
                                            </c:if>
                                        </c:forEach>

                                    </c:forEach>
                                    <input type="submit" value="Update" >

                                </form>
                                <script>
                                    function validateForm() {
                                        var checkboxes = document.getElementsByName("txtSize");
                                        var isChecked = false;
                                        for (var i = 0; i < checkboxes.length; i++) {
                                            if (checkboxes[i].checked) {
                                                isChecked = true;
                                                break;
                                            }
                                        }
                                        if (!isChecked) {
                                            alert("Hãy chọn ít nhất một size ");
                                            return false;
                                        }
                                    }
                                </script>
                            </c:if>
                        </div>
                    </div>
                </main>



                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2022</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
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
