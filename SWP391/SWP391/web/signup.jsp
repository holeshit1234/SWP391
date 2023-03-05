<!DOCTYPE html>
<html lang="en">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
	<title>SWP team project</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap -->
        <link href="asset/css/bootstrap.min.css" rel="stylesheet">
	
        <link rel="stylesheet" href="asset/css/stylesignup.css">
	
	<link rel="shortcut icon" href="images/logo.png">
	<link rel="stylesheet" href="icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
</head>

<body>
<!---------HEADER-------->
<header>
    <div class="logo">
        <a href="ShowIdexItemServlet"><img src="asset/images/logo-circle.png"></a>
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
        
          <c:url var="urlprofile" value="DispatchController">
                    <c:param name="btAction" value="show"/>
                </c:url>
        
        <c:if test="${not empty sessionScope.USER}">
                    <li><a class="fa fa-user" href="${urlprofile}"></a></li>
                    </c:if>

                <c:if test="${empty sessionScope.USER}">
                    <li><a class="fa fa-user" href="login.jsp"></a></li>
                    </c:if>

                <li><a class="fa fa-shopping-bag" href=""></a></li>
                    <c:if test="${not empty sessionScope.USER}">
                    <!--<li> <a href="LogoutAccountServlet">(Logout)</a>  </li>-->
                    <jsp:include page="logout.jsp"/>
                    </c:if>
    </div>
</header>
<!---------sign-up-form-------->
<section class="sigup">
	<div class="container">
		<div class="title">Sign up form</div>
		<form action="SignUpPageServlet" method="POST">
                        <c:set var="message" value="${requestScope.MESSAGE}" />
                                        <c:if test="${not empty message}">
                                            <font color='red'>
                                                ${message}
                                            </font><br/>
                                        </c:if>
                        <c:set var="errors" value="${requestScope.ERROR}" />
			<div class="user-detail">
				<div class="input-box">
					<span class="detail">User name *</span>
                                        <input type="text" name="txtUsername" placeholder="Enter your username" required  value="" /> 
                                        <c:if test="${not empty errors.usernameLengthErr}">
                                            <font color='red'>
                                                ${errors.usernameLengthErr}
                                            </font><br/>
                                        </c:if>
				</div>
                                <div class="input-box">
					<span class="detail">Full name *</span>
                                        <input type="text" name="txtFullname"  value="" placeholder="Enter your full name" required/> 
                                        <c:if test="${not empty errors.fullnameLengthErr}">
                                            <font color='red'>
                                              ${errors.fullnameLengthErr}
                                            </font><br/>
                                        </c:if>
				</div>
				<div class="input-box">
					<span class="detail">Password *</span>
                                        <input type="password" name="txtPassword"  placeholder="Enter your password" required value="" /> 
                                       <c:if test="${not empty errors.passwordLengthErr}">
                                           <font color='red'>
                                               ${errors.passwordLengthErr}
                                           </font><br/>
                                       </c:if>
				</div>
				<div class="input-box">
					<span class="detail">Confirm password *</span>
                                        <input type="password" name="txtConfirm" value="" placeholder="Confirm your password" c/> 
                                        <c:if test="${not empty errors.confirmNotMatchErr}">
                                            <font color='red'>
                                                ${errors.confirmNotMatchErr}
                                            </font><br/>
                                        </c:if>
				</div>
				
                            
				<div class="input-box">
					<span class="detail">Email</span>
					<input type="text" name="txtEmail" value="" placeholder="Enter your email" required>
				</div>
				<div class="input-box">
					<span class="detail">Phone</span>
					<input type="text" name="txtPhone" value="" placeholder="Enter your phone" required>
				</div>
				<div class="input-box">
					<span class="detail">Date of birth</span>
					<input type="date" name="txtDOB" value="" placeholder="Enter Date of birth" required>
				</div>
                                <div class="input-box">
					<span class="detail">Province</span>
					<input type="text" name="txtProvince" value="" placeholder="Enter your province" required>
				</div>
                                <div class="input-box">
					<span class="detail">District</span>
					<input type="text" name="txtDistrist" value="" placeholder="Enter your District" required>
				</div>
                                <div class="input-box">
					<span class="detail">Ward</span>
					<input type="text" name="txtWard" value="" placeholder="Enter your Ward" required>
				</div>

                                <!-- Province.js -->
                                <!--                            
				<div class="input-box">
					<span class="detail">Province</span>
                                        <select name="" id="province" name="txtProvince" required>
                                        </select>
					
				</div>
				<div class="input-box">
					<span class="detail">District</span>
                                        
					<select name="" id="district" name="txtDistrist" required>
                                            <option value="">chọn quận</option>
                                        </select>
				</div>
				<div class="input-box">
					<span class="detail">Ward</span>
					<select name="" id="ward" name="txtWard" required>
                                            <option value="">chọn phường</option>
                                        </select>
				</div>
                                -->
                                
				<div class="input-box1">
					<span class="detail">Street</span>
                                        <input type="text" placeholder="Enter your address" name="txtStreet" value="" required>
				</div>	
			</div>
			<div class="gender-detail">
				<input type="radio" name="gender" value="Nam" id="dot-1">
				<input type="radio" name="gender" value="Nu" id="dot-2">
				<input type="radio" name="gender" value="Khac" id="dot-3">
				<span class="gender-title">Gender</span>
				<div class="category">
					<label for="dot-1">
						<span class="dot one" name="txtGender" value="Nam" ></span>
						<span class="gender">Male</span>
					</label>
					<label for="dot-2">
						<span class="dot two" name="txtGender" value="Nu"></span>
						<span class="gender">Female</span>
					</label>
					<label for="dot-3">
						<span class="dot three" name="txtGender" value="Khac"></span>
						<span class="gender">Orther</span>
					</label>
				</div>
			</div>
			<div class="button">
                            <input type="submit" value="Register" name="btAction">
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
		©IVYmoda All rights reserved
	</div>
        </footer>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
        integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.26.1/axios.min.js"
        integrity="sha512-bPh3uwgU5qEMipS/VOmRqynnMXGGSRv+72H/N260MQeXZIK4PG48401Bsby9Nq5P5fz7hy5UGNmC/W1Z51h2GQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="asset/province.js"></script>

	
</body>

</html>