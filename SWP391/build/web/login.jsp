<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
	<title>SWP team project</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	
	<link rel="stylesheet" href="css/stylelogin.css">
	
	<link rel="shortcut icon" href="images/logo.png">
	<link rel="stylesheet" href="icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
</head>

<body>
<!---------HEADER-------->
<header>
    <div class="logo">
        <img src="images/logo-circle.png">
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
        <li><a class="fa fa-user" href="login.html"></a></li>
        <li><a class="fa fa-shopping-bag" href=""></a></li>
    </div>
</header>
<!---------Login-------->
<section class="login">
	<div class="container">
		<div class="sigin-signup">
			<form action="loginController" method="POST" class="sign-in-form">
				<h2 class="title">Login</h2>
				<div class="input-field">
					<i class="fa fa-user"></i>
					<input type="text"name="txtUsername" value="${param.txtUsername}" placeholder="Username">
				</div>
                                
				<div class="input-field">
					<i class="fa fa-lock"></i>
					<input type="password" name="txtPassword" value="" placeholder="Password">
				</div>
                                
                                <c:set var="err" value="${requestScope.L_ERROR}" />
                                <c:if test="${not empty err.emptyUserNamePassWord}">
                                    <font color="red">
                                    ${err.emptyUserNamePassWord}
                                    </font><br/>
                                </c:if>
                                <c:if test="${not empty err.wrongUserNamePassWord}">
                                    <font color="red">
                                    ${err.wrongUserNamePassWord}
                                    </font><br/>
                                </c:if>
                                    
                                <div class="remember-forgot">
                                    <label><input type="checkbox" name="chkremember" value="ON">
                                    Remember me</label>
                                    <a href="#">Forgot Password?</a>
                                </div>
                                    
				<input type="submit" value="Login" class="btn">
				<p class="social-text">Or sign in with social platform</p>
				<div class="social-media">
					<a href="http://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:8084/SWP391/GoogleSignInServlet&response_type=code
                   &client_id=862594563658-nsdpdrc8k2ee7h28v6r1rg8t3obssjtv.apps.googleusercontent.com&approval_prompt=force" class="social-icon">
						<i class="fa fa-google"></i>
					</a>
				</div>
			</form>
			<form action="signup.jsp" class="sign-up-form">
				<h2 class="title">Become a customer of the store</h2>
				
                <p class="social-text">If you do not have an account on ivymoda.com, use this option to access the registration form. By providing IVY moda with your details, the buying process on ivymoda.com will be a more enjoyable and quicker experience.</p>
				<input type="submit" value="Sign up" class="btn">
				</div>
			</form>
		</div>
		
</section>
	

	
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


	
</body>

</html>