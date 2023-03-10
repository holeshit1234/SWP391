<%-- 
    Document   : footer
    Created on : Feb 14, 2023, 6:42:43 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="asset/css/styleindex.css">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">       
        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">

        <style>
            #map {
                height: 300px;
                width: 100%;
            }
        </style>
        <script>
            function initMap() {
                var myLatLng = {lat: 10.84142, lng: 106.81004};

                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 15,
                    center: myLatLng
                });

                var marker = new google.maps.Marker({
                    position: myLatLng,
                    map: map,
                    title: 'My Location'
                });
            }
        </script>
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDc7PnOq3Hxzq6dxeUVaY8WGLHIePl0swY&callback=initMap"></script>

        <title>JSP Page</title>
    </head>
    <body>

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
            <div class="footer-content">
                <div class="footer-center">
                    <p>
                        Contact phone number: 0111111111 <br>
                        Registration address: ??????????? <br>
                        Order online: <b>022222222</b>
                    </p>
                </div>
                <div class="map-container">
                    <div id="map"></div>
                </div>
            </div>
            <div class="footer-bottom">
                ©IVYmoda All rights reserved
            </div>
        </footer>
    </body>
</html>
