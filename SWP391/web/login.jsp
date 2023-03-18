<%-- 
    Document   : login.jsp
    Created on : Feb 6, 2023, 10:24:21 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="asset/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="asset/css/styleOfLogin.css">

        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
        <style>
            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropbtn {
                /*                padding: 12px 16px;*/
                border: none;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        
        </header> <jsp:include page="header.jsp"/>
        <div>
            
            <section class="login">
                <div class="container">
                    <div class="sigin-signup">
                        <form action="LoginServlet" method="POST" class="sign-in-form">
                            <h2 class="title">Login</h2>
                            <div class="input-field">
                                <i class="fa fa-user"></i>

                                <c:set var="err" value="${requestScope.L_ERROR}" />
                                <input type="text" name="txtUsername" value="${param.txtUsername}" /><br/>
                            </div>
                            <div class="input-field">
                                <i class="fa fa-lock"></i>
                                <input type="password" name="txtPassword" value="" /><br/>                                 
                            </div>
                            <input type="hidden" name="txtProductID" value="${param.txtProductID}">
                            
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
                                <label><input type="checkbox" name="chkremember" value="ON" />
                                    Remember me</label>
                                <a href="userVerify.jsp">Forgot Password?</a>
                            </div>


                            <input type="submit" value="login" class="btn" name="btAction">

                            <p class="social-text">Or sign in with social platform</p>
                            <div class="social-media">
                                <a href="http://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:8084/SWP391/GoogleSignInServlet&response_type=code
                                   &client_id=862594563658-nsdpdrc8k2ee7h28v6r1rg8t3obssjtv.apps.googleusercontent.com&approval_prompt=force" class="social-icon">
                                    <i class="fa fa-google"></i>                             
                                </a>
                                <script>
                                    function showAlert() {
                                        const alertMsg = "${!empty sessionScope.LOGIN_ERROR}";
                                        console.log(alertMsg);
                                        if (alertMsg === "true") {
                                            var alertDiv = document.createElement("div");
                                            alertDiv.classList.add("alert");
                                            alertDiv.innerHTML = "This Email is banned.";
                                            document.body.appendChild(alertDiv);
                                            alertDiv.style.display = 'block';
                                            setTimeout(function () {
                                                alertDiv.style.display = 'none';
                                            }, 2000);
                                        }
                                    }                                                                                                
                                </script>
                            </div>

                        </form> 

                        <form action="signup.jsp" class="sign-up-form">
                            <h2 class="title">Become a customer of the store</h2>

                            <p class="social-text">If you do not have an account on DHTV, 
                                use this option to access the registration form. 
                                By providing DHTV with your details,
                                the buying process on DHTV will be a more enjoyable and quicker experience.</p>
                            <input type="submit" value="Sign up" class="btn">
                            </div>
                        </form>
                    </div>
            </section>
        </div>  
                                        <script>
                                            window.onload = function () {
                                                showAlert();
                                            };
                                        </script>                           
    </body>
</html>
