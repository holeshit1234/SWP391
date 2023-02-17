<%-- 
    Document   : header
    Created on : Feb 14, 2023, 6:42:34 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap -->
        <link href="asset/css/bootstrap.min.css" rel="stylesheet">
	
        <link rel="stylesheet" href="asset/zzcss/stylelogin.css">
	
        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        
        <title>JSP Page</title>
    </head>
    <body>
       <header>
    <div class="logo">
        <a href="index.jsp"><img src="asset/images/logo-circle.png"></a>
        
    </div>
    <div class="menu">
        <li><a href="">Male</a>
            <ul class="sub-menu">
                <li><a href="">New products</a></li>
                <li><a href="">Collection</a></li>
                <li><a href="">Men's shirt</a>
                    <ul>
                        <li><a href="">Shirt</a></li>
                        <li><a href="">T-shirt</a></li>
                        <li><a href="">Vest</a></li>
                        <li><a href="">Sweater</a></li>
                        <li><a href="">Coat</a></li>
                    </ul>					
                </li>
                <li><a href="">Men's pants</a>
                    <ul>
                        <li><a href="">Jeans</a></li>
                        <li><a href="">Short pant</a></li>
                        <li><a href="">Trouser</a></li>
                    </ul>					
                </li>
            </ul>
        
        </li>
        <li><a href="">Female</a></li>
        <li><a href="">Children</a></li>
        <li><a href="">Sale</a></li>
        <li><a href="">Collection</a></li>
        <li><a href="">Information</a></li>
    </div>
    <div class="orther">
        <li><input placeholder="Search" type="text"><i class="fa fa-search"></i></li>
        <li><a class="fa fa-user" href="FirstTimeRequestServlet"></a></li>
        <li><a class="fa fa-shopping-bag" href=""></a></li>
    </div>
</header>
        
    </body>
</html>
