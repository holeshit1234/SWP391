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

            .rating-cmt {
               
                text-align: center;
            }

            .rating-cmt input  {
                display: none;
                background: none;
            }
            .rating-cmt label {
                color: grey;
                font-size: 35px;
                cursor: pointer;
                margin: -5px;
            }
            .rating-cmt label:before {
                content: "\2605";
                margin: 5px;
            }
            .rating-cmt input:checked ~ label {
                color: #f9d71c;
            }
            .rating-cmt label:hover,
            .rating-cmt label:hover ~ label {
                color: #f9d71c;
            }
            img{
                width: 50px;
            }
            .eva-cmt{                  
            }
            .eva-star-cmt{           
            }
            .center{
                display: flex;
  justify-content: center;

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
                            <nav class="navbar" style="margin-left: 65px;">
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
                                                        <th>MÃ ĐƠN HÀNG</th>                                                        
                                                        <th>HÌNH ẢNH</th>
                                                        <th>TÊN SẢN PHẨM</th>
                                                        <th>GIÁ TIỀN</th>

                                                    </tr>
                                                </thead>
                                                <jsp:useBean id="daoOrder" class="DHTV.order.OrderDAO"/>

                                                <jsp:useBean id="daoOrderDetail" class="DHTV.order.OrderDetailDAO"/>

                                                <jsp:useBean id="daoProduct" class="DHTV.product.ProductDAO"/>
                                                <jsp:useBean id="daoSize" class="DHTV.size.SizeDAO"/>
                                                <c:set var="orderID" value="${param.OrderID}"/>


                                                ${daoOrderDetail.showOrderDetailByOrderID(orderID)}
                                                <tbody>                                                 
                                                    <c:forEach var="dto" items="${daoOrderDetail.getOrderDetailList()}" >

                                                    <form action="CommentServlet" method="POST">
                                                        <tr>
                                                            <fmt:formatNumber var="totalPriceOfPro" 
                                                                              value="${daoProduct.getProductByProductID(dto.getProductID()).getPrice() * dto.getQuantity()}" 
                                                                              pattern="#,###"/>
                                                            <td>VDTH${orderID}</td>
                                                            <td><img src="asset/images/productpictures/${daoProduct.getInfoProductByProductID(dto.getProductID()).getImage()}" alt="" srcset=""></td>
                                                            <td>${daoProduct.getInfoProductByProductID(dto.getProductID()).getProductName()}</td>
                                                            <td>${totalPriceOfPro} <sup>vnd</sup></td>

                                                        </tr>
                                                    </form>

                                                </c:forEach>                                                 
                                                </tbody>

                                            </table>
                                            <form action="RatingOrderServlet" >
                                                <input type="hidden" name="orderID" value="${param.OrderID}" />
                                                    <div class="center">
                                                        <h5>Please give your rating for that order</h5>
                                                </div>
                                                <div class="rating-cmt">
                                                    <div class="eva-cmt">
                                                        <li class="eva-star-cmt">
                                                            <input type="radio" name="rating" value="5" checked="checked" id="star5">
                                                            <label for="star5"></label>
                                                            <input type="radio" name="rating" value="4" id="star4">
                                                            <label for="star4"></label>
                                                            <input type="radio" name="rating" value="3" id="star3">
                                                            <label for="star3"></label>
                                                            <input type="radio" name="rating" value="2" id="star2">
                                                            <label for="star2"></label>
                                                            <input type="radio" name="rating" value="1"  id="star1">
                                                            <label for="star1"></label>
                                                        </li>
                                                    </div>
                                                    
                                                </div>
                                                <div class="center">
                                                <input type="submit" class="btn btn-primary" value="Save" />
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div> 

                            </main>

                        </div>




                    </div>
                </div>
            </div> 
        </div> 

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