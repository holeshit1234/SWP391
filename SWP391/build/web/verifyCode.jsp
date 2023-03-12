<%-- 
    Document   : verifyCode
    Created on : Feb 16, 2023, 12:08:47 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Code</title>
        <link rel="stylesheet" href="asset/css/styleVerifyCode.css">
        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <header>
            <div class="logo">
                <img  src="asset/images/logo-circle.png">
            </div>
            <div class="menu">
                <li><a href="showProductByGenderServlet?gender=Nam">Nam</a></li>
                <li><a href="showProductByGenderServlet?gender=Nữ">Nữ</a></li>
                <li><a href="showProductByGenderServlet?gender=Unisex">Unisex</a></li>
            </div>
            <div class="orther">
                <li><input placeholder="Search" type="text"><i class="fa fa-search"></i></li>
                <li><a class="fa fa-user" href="login.jsp"></a></li>
                <li><a class="fa fa-shopping-bag" href="#"></a></li>
            </div>
        </header>

        <!--body-->

        <div class="form">
            <div class="container">
                <h2>Verify your account</h2>
                <p>
                    We emailed you the six digit code to sgdbd@gmail.com <br>
                    Enter the code below to confirm your email address.
                </p>
                <form action="VerifyCodeServlet" method="POST">
                    <div class="code-container">
                        <c:set var = "scope" value="${requestScope.VERIFYCODE_SCOPE}"/>

                        <input type="text" class="code" placeholder="0" min="0" max="9" required
                               name="txtCode1" value="${param.txtCode1}"/><br/>


                        <input type="text" class="code" placeholder="0" min="0" max="9" required
                               name="txtCode2" value="${param.txtCode2}"/><br/>


                        <input type="text" class="code" placeholder="0" min="0" max="9" required
                               name="txtCode3" value="${param.txtCode3}"/><br/>


                        <input type="text" class="code" placeholder="0" min="0" max="9" required
                               name="txtCode4" value="${param.txtCode4}"/><br/>

                    </div>
                    <c:if test="${not empty scope.codeLengthError}">

                        style="font-size: 20px; color: red" >
                        ${scope.codeLengthError}

                    </c:if>
                    <c:if test="${not empty scope.codeNotExisted}">
                        <font style="font-size: 20px; color: red" >
                        ${scope.codeNotExisted}
                        </font>
                    </c:if>

                    <div class="forgot-function">
                        <p class="forgot-function-security">
                            This site is protected by reCAPTCHA and the Google
                            <a href="https://policies.google.com/terms">Privacy Policy</a>
                            and
                            <a href="https://policies.google.com/privacy"
                               >Terms of Service</a
                            >
                            apply.
                        </p>
                        <div class="forgot-function-choose">
                            <input type="submit" value="VERIFY" name="btAction" class="btn btn-primary"><br/>
                            <a href="login.jsp" class="forgot-function-cancel btn">Cancel</a>
                        </div>
                    </div>
                </form>

            </div>
        </div>


        <!--footer-->
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
        <script>
            const codes = document.querySelectorAll('.code')
            codes[0].focus()


            codes.forEach((code, idx) => {
                code.addEventListener('keydown', (e) => {
                    if (e.key >= 0 && e.key <= 9) {
                        codes[idx].value = ''
                        setTimeout(() => codes[idx + 1].focus(), 10)
                    } else if (e.key === 'Backspace') {
                        setTimeout(() => codes[idx - 1].focus(), 10)
                    }
                })
            })
        </script>



        <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" 
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" 
        crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" 
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" 
        crossorigin="anonymous"></script>
    </body>
</html>
