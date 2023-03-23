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
        <link href="asset/css/addProductPageStyle.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
       
    </head>
    <body class="sb-nav-fixed">

        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="ShowDashBoard">VDTH STORE</a>
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
                        <!--                        <h1 class="mt-4">Tables</h1>-->

                        <div class="card mb-4">

                            <jsp:useBean id="daoBrand" class="DHTV.brand.BrandDAO"/> 
                            <jsp:useBean id="daoSize" class="DHTV.size.SizeDAO"/>
                            <jsp:useBean id="daoCategory" class="DHTV.category.CategoryDAO"/>
                            <h1>Thêm sản phẩm</h1>
                            <form action="AddProductServlet" method="POST" enctype="multipart/form-data" onsubmit="return validateForm()">

                                <label for="product-name-input">Tên sản phẩm:</label>
                                <input type="text" id="product-name-input" name="txtProductName" value="" required><br/>

                                <label for="brand-select">Tên thương hiệu:</label>
                                ${daoBrand.listBrand()}
                                <select name="txtBrand">
                                    <c:forEach var="dto" items="${daoBrand.getBrandList()}">
                                        <option value="${dto.getBrandId()}"> ${dto.getBrandName()} </option>
                                    </c:forEach>
                                </select>

                                <br/>
                                ${daoCategory.showListCategory()}
                                <div class="sex-option">
                                    <input type="radio" name="gender" onclick="showCate(this)" value="Nam" />
                                    <label>Nam</label>
                                    <div class="form-input">
                                        <div class="input-row">
                                            <select name="txtCate" disabled>
                                                <c:forEach var="dto" items="${daoCategory.getCateList()}">
                                                    <c:if test="${dto.getGender() == 'Nam' }">
                                                        <option id="category" value="${dto.getCategoryID()}">${dto.getCategoryName()}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="sex-option">
                                    <input type="radio" name="gender" onclick="showCate(this)" value="Nữ" />
                                    <label>Nữ</label>
                                    <div class="form-input">
                                        <div class="input-row">
                                            <select name="txtCate" disabled>
                                                <c:forEach var="dto" items="${daoCategory.getCateList()}">
                                                    <c:if test="${dto.getGender() == 'Nữ' }">
                                                        <option id="category" value="${dto.getCategoryID()}">${dto.getCategoryName()}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="sex-option">
                                    <input type="radio" name="gender" onclick="showCate(this)" value="Unisex" />
                                    <label>Unisex</label>
                                    <div class="form-input">
                                        <div class="input-row">
                                            <select name="txtCate" disabled>
                                                <c:forEach var="dto" items="${daoCategory.getCateList()}">
                                                    <c:if test="${dto.getGender() == 'Unisex' }">
                                                        <option id="category" value="${dto.getCategoryID()}">${dto.getCategoryName()}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <script>
                                    function showCate(radio) {
                                        // Disable all select elements
                                        var selectElements = document.querySelectorAll('.sex-option select');
                                        for (var i = 0; i < selectElements.length; i++) {
                                            selectElements[i].disabled = true;
                                        }

                                        // Enable the select element within the selected sex-option
                                        var sexOption = radio.closest('.sex-option');
                                        var selectElement = sexOption.querySelector('select');
                                        selectElement.disabled = false;

                                        // Remove the "selected" class from all sex-options
                                        var sexOptions = document.querySelectorAll('.sex-option');
                                        for (var i = 0; i < sexOptions.length; i++) {
                                            sexOptions[i].classList.remove('selected');
                                        }

                                        // Add the "selected" class to the clicked sex-option
                                        sexOption.classList.add('selected');
                                    }
                                </script>

                                <style>
                                    .sex-option .form-input {
                                        display: none;
                                    }

                                    .sex-option.selected .form-input {
                                        display: block;
                                    }
                                </style>

                                <br/>
                                Kích cỡ: 
                                ${daoSize.showSizeList()}
                                <br>

                                <c:forEach var="dto" items="${daoSize.getSizeList()}" varStatus="status">
                                    <div class="size-option">
                                        <input id="size" type="checkbox" value="${dto.getSizeID()}" name="txtSize" onclick="showForm(this)"/>
                                        <label>${dto.getSizeName()}</label>
                                        <div class="form-input">                      
                                            <div class="input-row">
                                                <span>Số lượng</span>
                                                <input id="quantity" type="text" name="txtQuantity" value="1"><br/> 
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                              

                                <script>
                                    function showForm(checkbox) {
                                        var formInput = checkbox.parentNode.querySelector('.form-input');
                                        formInput.classList.toggle('show');
                                    }
                                </script>


                                Giá: <input id="price" type="text" name="txtPrice" value="" required><br>
                                Mô tả: <input id="description" type="text" name="txtDescription" value="" required><br/>
                                Link ảnh: <input id="image" type="file" name="txtImage" value="" required><br/>

                                <input type="submit" value="submit">

                            </form>

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


