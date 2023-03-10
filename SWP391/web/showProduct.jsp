<%-- 
    Document   : admin
    Created on : Feb 2, 2023, 5:04:21 PM
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
                        <div class="small">Logged in as: </div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Tables</h1>
                        <a href="addProductPage.jsp" class="button">Thêm sản phẩm</a>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                DataTable Product
                            </div>

                            <div class="card-body">
                                <jsp:useBean id="daoCategory" class="DHTV.category.CategoryDAO"/>
                                <jsp:useBean id="daoBrand" class="DHTV.brand.BrandDAO"/>   
                                <jsp:useBean id="daoProductDetail" class="DHTV.product.ProductDetailDAO"/>  
                                <jsp:useBean id="daoSize" class="DHTV.size.SizeDAO"/>  
                                <c:set var="result" value="${requestScope.ITEMS_RESULT_ADMIN}"/>
                                <c:if test="${not empty result}">
                                    <table id="datatablesSimple">
                                        <thead>
                                            <tr>
                                                <th>No.</th>
                                                <th>image</th>
                                                <th>Prodcut Name</th>
                                                <th>brand name</th>
                                                <th>category</th>
                                                <th>Gender</th>
                                                <th>price</th>                     
                                                <th>Size and quantity</th>                     
                                                <th>status</th>
                                                <th>Edit</th>
                                                <th>AddSize</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>No.</th>
                                                <th>image</th>
                                                <th>Prodcut Name</th>
                                                <th>brand name</th>
                                                <th>category</th>
                                                <th>Gender</th>
                                                <th>price</th>                     
                                                <th>Size and quantity</th>                     
                                                <th>status</th>
                                                <th>Edit</th>
                                                <th>AddSize</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:forEach var="dto" items="${result}">
                                                <tr>    
                                                    <td>
                                                        ${dto.productID}
                                                        <input type="hidden"  value="${dto.productID}" name="txtProductID">

                                                    </td>
                                                    <td>
                                                        <image src="asset/images/productpictures/${dto.image}"/>
                                                    </td>
                                                    <td>
                                                        ${dto.productName}
                                                    </td>


                                                    <td>
                                                        ${daoBrand.getInfoBrandByBrandID(dto.brandID).getBrandName()}                             
                                                    </td>

                                                    <td>
                                                        ${daoCategory.getInfoCategoryByCategoryID( dto.categoryID ).getCategoryName()}                            
                                                    </td>
                                                    <td>
                                                        ${daoCategory.getInfoCategoryByCategoryID( dto.categoryID ).getGender()}                            
                                                    </td>
                                                    <td>
                                                        ${dto.price}
                                                    </td>
                                                    <td>  

                                                        <c:set var="currentProductID" value="${dto.productID}" />

                                                        <c:set var="sqdto" value="${daoProductDetail.getSizeIdById(dto.productID)}"/>
                                                        <c:forEach var="size" items="${daoProductDetail.getDetailList()}">
                                                            <c:if test="${size.getProductID() == currentProductID}">
                                                                <c:set var="currentProductID" value="${size.getProductID()}" />

                                                                <div class="size-list">

                                                                    <div class="size-item">
                                                                        Size: 
                                                                        ${daoSize.getNameSizeBySizeID(size.getSizeID()).getSizeName()}
                                                                        Quantity ${size.getQuantity()}
                                                                    </div>

                                                                </div>
                                                            </c:if>
                                                        </c:forEach>

                                                    </td>


                                                    <td>
                                                        <form action="ChangeStatusProductServlet" method="POST" onsubmit="return confirmDelete();">
                                                            <input type="hidden" name ="txtStatus" value="${dto.status}">
                                                            <input type="hidden" name ="txtProductID" value="${dto.productID}">
                                                            <input type="submit" value="Xóa sản phẩm"  />
                                                        </form>

                                                    </td>
                                            <script>
                                                function confirmDelete() {
                                                    return confirm("Are you sure you want to delete this product?");
                                                }
                                            </script>
                                            <td> 
                                                <form action="EditProductServlet" method="POST">
                                                    <input type="hidden" name ="txtProductID" value="${dto.productID}">
                                                    <input type="submit" value="Sửa số lượng"  />   
                                                </form>

                                            </td>
                                            <td> 
                                                <form action="AddNewSizeProductServlet" method="POST">
                                                    <input type="hidden" name ="txtProductID" value="${dto.productID}">
                                                    <input type="submit" value="Thêm size"  />   
                                                </form>
                                            </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if>
                                </form>


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
        <style>
            /* Set font family and size for the whole page */
            body {
                font-family: Arial, sans-serif;
                font-size: 16px;
            }

            /* Style for the card-body container */
            .card-body {
                padding: 20px;
            }

            /* Style for the table */
            table {
                width: 100%;
                border-collapse: collapse;
            }

            /* Style for table headers */
            th {
                background-color: #f2f2f2;
                font-weight: bold;
                text-align: left;
                padding: 8px;
            }

            /* Style for table cells */
            td {
                padding: 8px;
                vertical-align: middle;
            }

            /* Style for image cells */
            td img {
                max-width: 100px;
            }

            /* Style for the "Delete" button */
            input[type="submit"] {
                background-color: #f44336;
                color: white;
                padding: 8px 16px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            input[type="submit"]:hover {
                background-color: #c62828;
            }

            /* Style for the "Update" button */
            input[type="submit"][value="Sửa số lượng"] {
                background-color: #2196F3;
            }

            input[type="submit"][value="Sửa số lượng"]:hover {
                background-color: #0d47a1;
            }
            input[type="submit"][value="Thêm size"] {
                background-color: #2196F3;
            }

            input[type="submit"][value="Thêm size"]:hover {
                background-color: #0d47a1;
            }


            .button {
                display: inline-block;
                padding: 8px 16px;
                font-size: 16px;
                font-weight: bold;
                text-align: center;
                text-decoration: none;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .button:hover {
                background-color: #3e8e41;
            }

            .logout-link {
                display: inline-block;
                padding: 8px 16px;
                font-size: 16px;
                font-weight: bold;
                text-align: center;
                text-decoration: none;
                background-color: #f44336;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .logout-link:hover {
                background-color: #d32f2f;
            }

        </style>
    </body>
</html>
