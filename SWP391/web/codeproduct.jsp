<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <title>SWP team project</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
          crossorigin="anonymous">

    <link rel="stylesheet" href="asset/css/codeproduct.css">

    <link rel="shortcut icon" href="asset/images/logo.png">
    <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<!-------Header---------->
 <jsp:include page="header.jsp"/>
<!----code------>
<div class="body">
    <div class="container">
        <div class="cart-icon">
            <i class="fa fa-check-circle-o "></i>
        </div>
        <div class="title-content">
            <h2>Thanks For Buying.</h2>
            <br>
        </div>
        <div class="content">
            <p>Thanks for your believing and buying product at <strong>VDTH Shop</strong>. We will try the best and improve about the service which can satisfy . </p>
            <p> We hope you come next.</p>
        </div>
        <div class="button">
            <a class="btn " href="ShowIdexItemServlet">Continue Buying </a>
            <a class="btn" href="ShowOrderTrackingServlet">Order Tracking</a>
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




<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>