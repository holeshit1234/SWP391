<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>SWP team project</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
              crossorigin="anonymous">

        <link rel="stylesheet" href="asset/css/checkout.css">

        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        <style>
            .add-buttom{
                padding-top: 20px;
            }
            .add-buttom button{
                background: #e7d0c4;
                border: none;
                border-radius: 10px;

            }

            .button {
                display: inline-block;
                padding: 10px 20px;
                height: 40px;
                cursor: pointer;
                border-radius: 10px;
                background: linear-gradient(135deg, #e7d0c4, #efe6e1);
                border: none;
                margin-right: 20px;
                color: #333;
                border: none;
            }
        </style>
    </head>
    <!---------HEADER-------->
    <jsp:include page="header.jsp"/>
    <!---------checkout-------->
    <div class="cart">
        <div class="container">
            <div class="cart-top-wrap">
                <div class="cart-top">
                    <div class="cart-top-cart cart-top-item">
                        <i class="fa fa-shopping-cart"></i>
                    </div>
                    <div class="cart-top-address cart-top-item">
                        <i class="fa fa-map"></i>
                    </div>
                    <div class="cart-top-payment cart-top-item">
                        <i class="fa fa-money"></i>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">            
            <form action="CodeProductServlet">
                <div class="payment row">

                    <div class="payment-content-left">
                        <div class="payment-content-left-method-delivery">
                            <p style="font-weight: bold;">Delivery Method</p>
                            <div class="payment-content-left-method-delivery-item">
                                <input checked type="radio">
                                <label for="">Chuyển phát nhanh</label>
                            </div>
                        </div>
                        <div class="payment-content-left-method-payment">
                            <!--Payment Method------------->
                            <p style="font-weight: bold;">Payment Method</p>
                            <c:if test="${not empty requestScope.PAYMENT}">
                                <font color='red'>
                                ${requestScope.PAYMENT}
                                </font><br/>
                            </c:if>
                            <div id="smart-button-container">
                                <div style="text-align: center;">
                                    <div id="paypal-button-container"></div>
                                </div>
                            </div>
                            </script>

                            <jsp:useBean id="daoPayment" class="DHTV.payment.PaymentMethodDAO"/> 
                            ${daoPayment.showPaymentMethod()}
                            <c:forEach var="dto" items="${daoPayment.getPaymentList()}" >                           
                                <div class="payment-content-left-method-payment-item">
                                    <input name="txtPayment" type="radio" value="${dto.getPaymentID()}">
                                    <label for="">${dto.getPaymentMethod()}</label>
                                </div>                            
                            </c:forEach>                        
                        </div>
                        <div class="payment-content-left-address-delivery">

                            <!--Address------------->
                            <p style="font-weight: bold;">Delivery Address</p>
                            <c:if test="${not empty requestScope.ADDRESS}">
                                <font color='red'>
                                ${requestScope.ADDRESS}
                                </font><br/>
                            </c:if>
                            <jsp:useBean id="daoAdd" class="DHTV.address.AddressDAO"/>                          
                            <jsp:useBean id="daoUser" class="DVHT.userdetails.UserDetailsDAO"/>                          
                            ${daoAdd.getAddress(requestScope.USERID)}

                            <c:set var="user" value="${daoUser.showUserById(requestScope.USERID)}"/>
                            <c:set var="firstIteration" value="true"/>
                            <c:forEach var="dto" items="${daoAdd.getInfoList()}" >
                                <c:if test="${dto.isStatus() == true}">
                                    <div class="inline">
                                        <input name="txtAddressID" value="${dto.getAddressID()}" type="radio" ${firstIteration ? 'checked' : ''}>
                                        <div class="testimonials-box">                                
                                            <div class="client-comment">
                                                ${user.getFullName()} <br>
                                                ${user.getPhone()}<br>
                                                ${dto.getStreet()}, ${dto.getProvice()}, ${ dto.getWard()}, ${dto.getDistrict()}
                                            </div>
                                        </div>
                                    </div>
                                    <c:set var="firstIteration" value="false"/>
                                </c:if>
                            </c:forEach>

                            <!--Add Address------------->
                            <div class="add-buttom">
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#popup1">
                                    Add New Address
                                </button>

                            </div>
                        </div>
                    </div>                           

                    <div class="cart-content-right">
                        <table>
                            <tr>
                                <th colspan="2">Summary Cart</th>
                            </tr>
                            <tr>
                                <c:if test="${not empty requestScope.QUANTITIES}">
                                    <c:set var="quantity" value="${requestScope.QUANTITIES}"/>
                                    <td>Total Product Quantities</td>
                                    <td>${quantity}</td>                               
                                </c:if>
                            </tr>
                            <tr>
                                <c:if test="${not empty requestScope.TOTAL_PRICE}">
                                    <fmt:formatNumber var="totalprice" value="${requestScope.TOTAL_PRICE}" pattern="#,###,###"/>
                                    <td>Total Price</td>
                                    <td><p>${totalprice}<sup>vnd</sup></p></td>
                                </c:if>

                            </tr>
                            <tr>
                                <td>Shipping Fee</td>
                                <td><p >30.000<sup>vnd</sup></p></td>
                            </tr>
                            <tr>
                                <c:if test="${not empty requestScope.TOTAL_PRICE}">
                                    <c:set var="total" value="${requestScope.TOTAL_PRICE}"/>
                                    <fmt:formatNumber var="totalWithShipping" value="${total+30000}" pattern="#,###"/>                               
                                    <td>Total</td>
                                    <td><p id="total-price" style="color: black; font-weight: bold;">${totalWithShipping}<sup>vnd</sup></p></td>
                                </c:if>
                            </tr>
                        </table>
                        <div class="cart-content-right-buttom">
                            <a href="ShowIdexItemServlet" class="button">Continue Buying</a>
                            <button type="submit">Finish</button>                                       
                        </div>
                    </div>
                </div>
            </form>    
        </div>
    </div>
    <c:set var="user" value="${sessionScope.USER}"/>
    <div class="modal fade" id="popup1" tabindex="-1" role="dialog" aria-labelledby="popup1Label"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="popup1Label">Add address</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Đóng">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                    <form action="AddAddressCheckout" method="POST">

                        <input type="text" name="txtuserid" value="${user.getUserID()}" 
                               style="display: none"/>

                        <div class="user-detail">
                            <div class="input-box">
                                <span class="detail">Full name</span>
                                <input type="text" placeholder="Enter your name" name="txtfullName"
                                       value="${user.getFullName()}" disabled="disabled">
                            </div>
                            <div class="input-box">
                                <span class="detail">Phone</span>
                                <input type="number" placeholder="Enter your phone" name="txtPhone"
                                       value="${user.getPhone()}" disabled="disabled">
                            </div>
                            <div class="input-box">
                                <label class="field-label" for="stored-city">Province</label><br>
                                <select class="field-input" id="stored-city" required>
                                    <option class="field-input-item" data-name="" value="">
                                        Choose Province / city
                                        <!-- Ở ĐÂY OPTION MÌNH CÓ THỂ TRUYỀN ĐƯỢC data-properties={"nội dung"} và value ={} -->
                                    </option>
                                </select>
                                <input type="hidden" id="txtProvinceDataName" name="txtProvinceDataName" value=""/>
                            </div>

                            <!-- DISTRICT -->
                            <div class="input-box">
                                <label class="field-label" for="stored-district">District</label><br>
                                <select class="field-input" id="stored-district" required>
                                    <option class="field-input-item" data-name="" value="">
                                        Choose District
                                        <!-- Ở ĐÂY OPTION MÌNH CÓ THỂ TRUYỀN ĐƯỢC data-properties={"nội dung"} và value ={} -->
                                    </option>
                                </select>
                                <input type="hidden" id="txtDistrictDataName" name="txtDistrictDataName" value=""/>
                            </div>
                            <div class="input-box">
                                <label class="field-label" for="stored-ward">Ward</label><br>
                                <select class="field-input" id="stored-ward" required>
                                    <option class="field-input-item" data-name="" value="">
                                        Choose Wards
                                        <!-- Ở ĐÂY OPTION MÌNH CÓ THỂ TRUYỀN ĐƯỢC data-properties={"nội dung"} và value ={} -->
                                    </option>
                                </select>
                                <input type="hidden" id="txtWardDataName" name="txtWardDataName" value=""/>
                            </div>
                            <div class="input-box">
                                <span class="detail">Address</span>
                                <input type="text" placeholder="Enter your address" name="txtAddress"
                                       value=""  required>
                            </div>
                        </div>
                        <input type="hidden" name="checkout" value="checkout" />
                        <input type="submit" value="Add Address" name="btAction"/>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" 
                            data-dismiss="modal">Close</button>
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
            Â©IVYmoda All rights reserved
        </div>
    </footer>
    <script src="asset/js/apiProvince.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>