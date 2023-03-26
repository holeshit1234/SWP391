<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>SWP team project</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
              crossorigin="anonymous">

        <link rel="stylesheet" href="asset/css/ordertracking.css">
        <link rel="stylesheet" href="asset/css/styleindex.css">
        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="asset/css/styletest.css" rel="stylesheet" />
        <!--<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>-->
        <style>            
            .add-buttom button{
                background: #e7d0c4;
                border: none;
                border-radius: 10px;

            }

            .confirm-box {
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: white;
                border: 1px solid black;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
                z-index: 9999;
                max-width: 500px;
                width: 100%;
            }

            .confirm-box p {
                margin-top: 0;
            }

            .button-container {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }

            .ok-button,
            .cancel-button {
                padding: 10px 20px;
                border-radius: 5px;
                border: none;
                cursor: pointer;
            }

            .ok-button {
                background-color: #EEDBD0;
                color: black;
                margin-right: 10px;
            }

            .cancel-button {
                background-color: #E7D0C4;
                color: black;
            }

            .ok-button:hover,
            .cancel-button:hover {
                background-color: #D5C0A7;
            }

            .confirm-box input[type="text"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
                margin-top: 10px;
            }

            .confirm-box input[type="text"]:focus {
                border-color: #66afe9;
                outline: 0;
                box-shadow: 0 0 10px rgba(102,175,233,.6);
            }

            .icon {
                cursor: pointer;
            }

        </style>
    </head>

    <body>
        <!---------HEADER-------->
        <jsp:include page="header.jsp"/>
        <!---------cart-------->
        <div class="cart">
            <c:if test="${not empty sessionScope.USER}">
                <c:set var="info_user" value="${sessionScope.USER}"/>  
                <div class="container">
                    <div class="order-content row">
                        <div class="order-content-left" style="display: block;">
                            <div class="content-left" style="margin-left: -85px;">
                                <div class="user">
                                    <img src="asset/images/useravatar/${info_user.picture}" alt="">                                        
                                    <h3 class="name">${info_user.fullName}</h3>              
                                </div>
                            </c:if> 
                            <nav class="navbar" >
                                <ul>
                                    <li><a href="ShowProfileServlet">Account information</a></li>
                                    <li><a href="ShowOrderTrackingServlet">Order management</a></li>
                                    <li><a href="ShowAddressServlet">Address number</a></li>
                                    <li><a href="https://www.facebook.com/people/VDTH/100090772202536/">Contact us</a></li>

                                </ul>
                            </nav>
                        </div>
                    </div>

                    <div class="order-content-right">
                        <div class="title" style="display: flex; align-items: center; justify-content: space-between;">
                            <h2 style="font-size: 25px; padding-bottom: 30px;">Order management</h2>
                            <div class="filter" style="margin-bottom: 1.5rem;">
                                <label>Order Status:</label>
                                <jsp:useBean id="daoApp" class="DHTV.approvalStatus.ApprovalStatusDAO"/>
                                ${daoApp.getData()}
                                <form method="get" action="ShowOrderTrackingServlet">
                                    <select name='txtStatus' onchange='if (this.value != 0) {
                                                this.form.submit();
                                            }' class="form-filter">
                                        <option value='9'>Option</option>
                                        <option value='9'>Tất cả</option>
                                        <c:forEach var="dto" items="${daoApp.getList()}" >
                                            <option value='${dto.getApprovalStatusID()}'>${dto.getApprovalStatus()}</option>

                                        </c:forEach>

                                    </select>
                                </form>
                            </div>
                        </div>
                        <div id="layoutSidenav_content">
                            <main>
                                <div class="container-fluid px-4">                           
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            <i class="fa fa-table me-1"></i>
                                            Order Manage
                                        </div>
                                        <div class="card-body">
                                            <table id="datatablesSimple">
                                                <thead>
                                                    <tr>
                                                        <th> Order Code </th>
                                                        <th>Date</th>
                                                        <th>Status</th>
                                                        <th>Product</th>
                                                        <th>Total</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>

                                                <jsp:useBean id="daoOrder" class="DHTV.order.OrderDAO"/>
                                                <jsp:useBean id="daoOrderDetail" class="DHTV.order.OrderDetailDAO"/>
                                                <jsp:useBean id="daoProduct" class="DHTV.product.ProductDAO"/>
                                                <jsp:useBean id="daoSize" class="DHTV.size.SizeDAO"/>
                                                <c:set var="userID" value="${sessionScope.USER.userID}"/>


                                                ${daoOrder.showOrderByUserID(userID)}
                                                ${daoBill.showBillByUserID(userID)}
                                                <c:if test="${not empty requestScope.STATUS}">
                                                    ${daoOrder.showOrderByUserIDAndStatus(userID, requestScope.STATUS)}
                                                </c:if>
                                                <tbody>



                                                    <c:forEach var="dto" items="${daoOrder.getOrderList()}" >
                                                        <c:if test="${dto.getApprovalStatus() != 4}">
                                                            <tr>
                                                                <td><p>VDTH ${dto.getOrderID()}</p></td>
                                                                <td><p>${dto.getDate()}</p></td>
                                                                <td>
                                                                    <c:if test="${dto.getApprovalStatus() == 1}">

                                                                        <p style="color:gold;"> ${daoOrder.getApprovalStatus(dto.getApprovalStatus())}</p>

                                                                    </c:if>
                                                                    <c:if test="${dto.getApprovalStatus() == 2}">

                                                                        <p style="color : green;"> ${daoOrder.getApprovalStatus(dto.getApprovalStatus())}</p>

                                                                    </c:if>
                                                                    <c:if test="${dto.getApprovalStatus() == 3}">

                                                                        <p style="color : green;"> ${daoOrder.getApprovalStatus(dto.getApprovalStatus())}</p>

                                                                    </c:if>

                                                                </td>
                                                                <td>
                                                                    ${daoOrderDetail.showOrderDetailByOrderID(dto.getOrderID())}
                                                                    <c:forEach var="dtoDetail" items="${daoOrderDetail.getOrderDetailList()}" >

                                                                        <p>
                                                                            <fmt:formatNumber var="totalPriceOfPro" 
                                                                                              value="${daoProduct.getProductByProductID(dtoDetail.getProductID()).getPrice() * dtoDetail.getQuantity()}" 
                                                                                              pattern="#,###"/>
                                                                            ${daoProduct.getInfoProductByProductID(dtoDetail.getProductID()).getProductName()} -
                                                                            size ${daoSize.getInfoSizeBySizeID(dtoDetail.getSizeID()).getSizeName()} 
                                                                            x${dtoDetail.getQuantity()} - <br> 
                                                                            total ${totalPriceOfPro} <sup>vnd</sup>
                                                                        </p>
                                                                    </c:forEach>
                                                                </td>
                                                                <td>
                                                                    <fmt:formatNumber var="totalWithShipping" value="${dto.getTotalPrice()+dto.getShippingFee()}" pattern="#,###"/>
                                                                    <p>${totalWithShipping} <sup>vnd</sup></p>
                                                                </td>

                                                                <td>

                                                                    <c:if test="${dto.getApprovalStatus() == 3 && !daoOrder.checkOrderPoint(dto.getOrderID())}">
                                                                        <p>Đã đánh giá</p>
                                                                    </c:if>
                                                                        <c:if test="${dto.getApprovalStatus() != 3 || daoOrder.checkOrderPoint(dto.getOrderID())}">
                                                                        
                                                        <li class="nav-item dropdown">
                                                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"></a>
                                                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                                                <c:if test="${dto.getApprovalStatus() == 1}">
                                                                    <c:url var="CancelOrderURL" value="CancelOrderServlet" >
                                                                        <c:param name="txtOrderID" value="${dto.getOrderID()}" />                                            
                                                                    </c:url>
                                                                    <li><a class="dropdown-item" onclick="confirmCancelOrder('${CancelOrderURL}')">Cancel</a></li>
                                                                    </c:if>
                                                                    <c:if test="${dto.getApprovalStatus() == 3 && daoOrder.checkOrderPoint(dto.getOrderID())}">
                                                                        <c:url var="RatingOrderURL" value="ratingorder.jsp" >
                                                                            <c:param name="OrderID" value="${dto.getOrderID()}" />                                            
                                                                        </c:url>
                                                                    <li><a class="dropdown-item" onclick="window.location.href = '${RatingOrderURL}'">Rating</a></li>
                                                                    </c:if>
                                                            </ul>
                                                        </li>
                                                        </c:if>

                                                        <%--
                                                    <div class="add-buttom">
                                                        <c:if test="${dto.getApprovalStatus() == 1}">
                                                            <c:url var="CancelOrderURL" value="CancelOrderServlet" >
                                                                <c:param name="txtOrderID" value="${dto.getOrderID()}" />                                            
                                                            </c:url>
                                                            <button class="btn btn-primary" type="button" onclick="confirmCancelOrder('${CancelOrderURL}')"><sup>Hủy đơn hàng</sup></button>
                                                        </c:if>
                                                    </div>
                                                    <div class="add-buttom">
                                                        <c:if test="${dto.getApprovalStatus() == 3 && daoOrder.checkOrderPoint(dto.getOrderID())}">
                                                            <c:url var="RatingOrderURL" value="ratingorder.jsp" >
                                                                <c:param name="OrderID" value="${dto.getOrderID()}" />                                            
                                                            </c:url>
                                                            <button class="btn btn-primary" type="submit" onclick="window.location.href = '${RatingOrderURL}'"><sup>Đánh giá sp</sup></button>
                                                        </c:if>
                                                        <c:if test="${dto.getApprovalStatus() == 3 && !daoOrder.checkOrderPoint(dto.getOrderID())}">
                                                            <p>Đã đánh giá</p>
                                                        </c:if>
                                                    </div>
                                                        --%>
                                                        </td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>  


                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div> 
                            </main>
                        </div>
                    </div>
                </div>
            </div> 
        </div> 

        <%--  <c:if test="${(dto.getApprovalStatus() == 4)||(dto.getApprovalStatus() == 3)}">                                                
                                                          <c:url var="repurchaseURL" value="RepurchaseServlet" >                                                    
                                                              <c:param name="txtCommentID" value="${cmtID}" />
                                                              <c:param name="txtProductID" value="${requestScope.PRODUCTID}" />
                                                              <c:param name="txtUserID" value="${user.getUserID()}" />
                                                          </c:url>
                                                          <button class="btn btn-primary" type="button" onclick="confirmFlag('${repurchaseURL}')">
                                                              <sup>Mua lại</sup>
                                                          </button>
                                                      </c:if>
        --%>
        <!---------Footer-------->



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

        <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="asset/js/Jorder.js"></script> 
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="asset/js/slideBar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="asset/js/text.js"></script>
        <script src="asset/js/datatables.js"></script>
        <link href="asset/js/datatables.min.js"/>
    </body>
</html>