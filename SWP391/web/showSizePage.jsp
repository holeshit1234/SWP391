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
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <style>
            input[type=checkbox] {
                margin-right: 10px;
            }                                              
            #brand {
                width: 280px;
            }
            #size {
                width: 280px;
            }
            #description {
                width: 280px;
                height:150px;
            }
            label {
                font-weight: bold;
            }

        </style>
        <style>
            /* Center the modal popup */
            .modal-dialog {
                display: flex;
                justify-content: center;
                align-items: center;
            }

            /* Style the modal content */
            .modal-content {
                background-color: white;
                border-radius: 10px;
                box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);
                padding: 20px;
            }

            /* Style the modal header */
            .modal-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                border-bottom: none;
            }

            /* Style the modal title */
            .modal-title {
                font-size: 24px;
                font-weight: bold;
                margin: 0;
                align-content: center;
            }

            /* Style the close button */
            .btn-close {
                padding: 0;
                background-color: transparent;
                border: none;
                font-size: 1.5rem;
                opacity: 0.5;
            }

            .btn-close:hover {
                opacity: 1;
            }

            /* Style the modal body */
            .modal-body {
                padding: 0;
            }

            /* Style the form */
            form {
                display: flex;
                flex-direction: column;
            }

            /* Style the table */
            table {
                border-collapse: collapse;
                width: 100%;
                margin-bottom: 20px;
            }

            th, td {
                text-align: left;
                padding: 8px;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            /* Style the modal footer */
            .modal-footer {
                display: flex;
                justify-content: flex-end;
                align-items: center;
                border-top: none;
                margin-top: 20px;
            }

            /* Style the buttons */
            .btn {
                padding: 8px 16px;
                border-radius: 5px;
                cursor: pointer;
            }

            .btn-secondary {
                background-color: #6c757d;
                color: white;
                margin-right: 10px;
            }

            .btn-secondary:hover {
                background-color: #5a6268;
            }

            .btn-primary {
                background-color: #007bff;
                color: white;
            }

            .btn-primary:hover {
                background-color: #0069d9;
            }

        </style>
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
                        <h1 class="mt-4">Tables</h1>

                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                DataTable User
                            </div>
                            <div class="card-body">
                                <c:set var="listSize" value="${requestScope.SIZE_RESULT}"/>
                                <c:if test="${not empty listSize}">
                                    <table id="datatablesSimple" >
                                        <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>Size</th>                                             
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="dto" items="${listSize}">
                                                <tr>
                                                    <td>${dto.getSizeID()}</td>
                                                    <td>${dto.getSizeName()}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if>

                                <!-- Button trigger modal -->
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                    Thêm Size
                                </button>

                                <!-- Modal -->
                                <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-s">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="staticBackdropLabel">Thêm size sản phẩm </h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body custom-modal-body" >
                                                <form action="AddSizeServlet" method="POST">
                                                    <label for="size"> Name</label>
                                                    <div>
                                                        <input id="size" type="text" name="txtSize" value="">
                                                        <div id="size-error" class="alert alert-danger d-none" role="alert">
                                                            Kích thước đã tồn tại. Vui lòng nhập một tên khác.
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                                        <button id="add-size-btn" type="submit" class="btn btn-primary" disabled>Thêm Size</button>
                                                    </div>
                                                </form>                                           
                                            </div>
                                        </div>
                                    </div>
                                </div> 
                            </div>                          
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
        <script>
            // Lấy tham chiếu đến các phần tử trong DOM
            const sizeInput = document.getElementById('size');
            const sizeError = document.getElementById('size-error');
            const addSizeBtn = document.getElementById('add-size-btn');

            // Khai báo biến lưu trữ tên size hiện tại
            let currentSize = '';

            // Thiết lập sự kiện khi người dùng nhập liệu
            sizeInput.addEventListener('input', () => {
                currentSize = sizeInput.value.trim();
                if (currentSize === '') {
                    // Nếu trường nhập liệu rỗng, vô hiệu hóa nút "Thêm Size"
                    addSizeBtn.disabled = true;
                    // Ẩn thông báo lỗi
                    sizeError.classList.add('d-none');
                } else {
                    // Kiểm tra tên size hiện tại có trùng với danh sách size hay không
                    let sizeExists = false;
                    const sizeList = document.querySelectorAll('#datatablesSize tbody td:nth-child(2)');
                    sizeList.forEach(size => {
                        if (size.textContent.trim().toLowerCase() === currentSize.toLowerCase()) {
                            sizeExists = true;
                        }
                    });
                    // Nếu tên size đã tồn tại, hiển thị thông báo lỗi và vô hiệu hóa nút "Thêm Size"
                    if (sizeExists) {
                        addSizeBtn.disabled = true;
                        sizeError.classList.remove('d-none');
                    } else {
                        // Ngược lại, cho phép thêm size mới và ẩn thông báo lỗi
                        addSizeBtn.disabled = false;
                        sizeError.classList.add('d-none');
                    }
                }
            });
        </script> 
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="asset/js/slideBar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="asset/js/text.js"></script>
        <script src="asset/js/datatables.js"></script>
        <link href="asset/js/datatables.min.js"/>
    </body>
</html>

