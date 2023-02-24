<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

    <head>
        <title>SWP team project</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="asset/css/styleproduct.css">
        <link rel="shortcut icon" href="asset/images/logo.png">
        <link rel="stylesheet" href="asset/icon fronts/font-awesome-4.7.0/css/font-awesome.min.css">
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
                <li><a class="fa fa-user" href="login.jsp"></a></li>
                <li><a class="fa fa-shopping-bag" href=""></a></li>
            </div>
        </header>
        <!---------Banner-slider-------->
        <section>
            <div class="container flex">
                <jsp:useBean id="daoProduct" class="DHTV.product.ProductDAO"/>  
                <jsp:useBean id="daoProductImg" class="DHTV.product.ProductImgDAO"/>  
                <div class="left">
                    <div class="main-image" id="test">
                        <img id="myImage" src="${daoProductImg.getOneImgByProductID(requestScope.PRODUCTID)}" alt="" class="slide">
                    </div>
                    <div class="option flex">
                        <img src="${daoProductImg.getOneImgByProductID(requestScope.PRODUCTID)}" onclick="img('${daoProductImg.getOneImgByProductID(requestScope.PRODUCTID)}')">
                        <img src="asset/images/b2712178ec861468e23277efeb1b800c.jpg" onclick="img('asset/images/b2712178ec861468e23277efeb1b800c.jpg')">
                        <img src="asset/images/f059db41962d4c055e985a7c06dc532d.jpg" onclick="img('asset/images/f059db41962d4c055e985a7c06dc532d.jpg')">
                        <img src="asset/images/a431fa2ea0f231d48d1586a0faef5454.jpg" onclick="img('asset/images/a431fa2ea0f231d48d1586a0faef5454.jpg')">
                        <img src="asset/images/b2712178ec861468e23277efeb1b800c.jpg" onclick="img('asset/images/b2712178ec861468e23277efeb1b800c.jpg')">
                    </div>
                </div>
                <jsp:useBean id="dao" class="DVHT.comment.CommentDAO"/>                      
                <jsp:useBean id="daoBrand" class="DHTV.brand.BrandDAO"/>                      
                <jsp:useBean id="daoCategory" class="DHTV.category.CategoryDAO"/>
                <jsp:useBean id="daoSize" class="DHTV.size.SizeDAO"/>  


                <div class="right">
                    <form action="AddToCartServlet" method="GET">

                        <h3>${daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getProductName()} - 
                            <c:set var="avgRate" value="
                                   ${ 
                                   (   (dao.countCommentListByStar(requestScope.PRODUCTID,5)) * 5 +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,4)) * 4 +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,3)) * 3 +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,2)) * 2 +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,1)) * 1 
                                       ) 
                                       /
                                       (   (dao.countCommentListByStar(requestScope.PRODUCTID,5)) +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,4)) +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,3)) +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,2)) +
                                       (dao.countCommentListByStar(requestScope.PRODUCTID,1)) 
                                       )
                                   }
                                   "/>
                            <jsp:useBean id="daoUtil" class="DVHT.utils.Util"/>    
                            ${daoUtil.roundingFunction(avgRate)}
                            <i class="fa fa-star" ></i>
                        </h3>

                        <h4>${daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getPrice()}  <small>vnd</small></h4>
                        <p>  ${daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getDescription()}  </p>
                        Brand: 
                        <p>
                            ${daoBrand.getInfoBrandByBrandID(daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getBrandID()).getBrandName()} 
                        </p>
                        Category:
                        <p>
                            ${daoCategory.getInfoCategoryByCategoryID(daoProduct.getInfoProductByProductID(requestScope.PRODUCTID).getCategoryID()).getCategoryName()} 
                        </p>
                        Size :                         
                        ${daoSize.showSizeList()}
                        <div class="add flex1">
                            <c:forEach var="dto" items="${daoSize.getSizeList()}" >
                                <input type="radio" name="size" value="${dto.getSizeName()}">
                                <label for="age1">${dto.getSizeName()}</label><br>
                            </c:forEach>                        
                        </div>
                        <button type="button" onclick="openPopupAddToCart()">Add to cart</button>
                        <div class="popup" id="popup">
                            <img src="asset/images/404-tick.png">
                            <h2>Thank you!</h2>
                            <p>Your product has been added to the cart.</p>
                            <button type="button" onclick="closePopupAddToCart()">Continue</button>
                            <!-----onclick="location.href='login.html';"------>
                        </div>
                    </form>
                </div>
            </div>
        </section>
        <script>
            function img(anything) {
                document.querySelector('.slide').src = anything;
            }
            function change(change) {
                const line = document.querySelector('.home');
                line.style.background = change;
            }


//popup------
            let popup = document.getElementById("popup");
            function openPopupAddToCart() {
                popup.classList.add("open-popup");
            }
            function closePopupAddToCart() {
                popup.classList.remove("open-popup");
            }


//zoom out image on mousemove() & mouseleave()-------
            var myImage = document.getElementById("myImage");
            myImage.addEventListener("mousemove", function () {
                myImage.classList.remove("zoomOut"); /* remove the zoom-out class */
            });
            myImage.addEventListener("mouseleave", function () {
                myImage.classList.add("zoomOut"); /* add the zoom-out class */
            });
        </script>
        <!---------comment-------->
        <section id="testimonials">
            <div class="testimonial-heading">
                <h1>Product Reviews</h1>


                <div class="footer-top">
                    <li>
                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary" >
                                All 
                                (${dao.countCommentListByStar(requestScope.PRODUCTID,5) +
                                   dao.countCommentListByStar(requestScope.PRODUCTID,4) +
                                   dao.countCommentListByStar(requestScope.PRODUCTID,3) +
                                   dao.countCommentListByStar(requestScope.PRODUCTID,2) +
                                   dao.countCommentListByStar(requestScope.PRODUCTID,1)})
                                <input type="hidden" name="star" value="0" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary" >
                                5 <i class="fa fa-star" ></i>

                                (${dao.countCommentListByStar(requestScope.PRODUCTID,5)})
                                <input type="hidden" name="star" value="5" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>

                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary" >
                                4 <i class="fa fa-star" ></i> 

                                (${dao.countCommentListByStar(requestScope.PRODUCTID,4)})
                                <input type="hidden" name="star" value="4" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary" >
                                3 <i class="fa fa-star" ></i>

                                (${dao.countCommentListByStar(requestScope.PRODUCTID,3)})
                                <input type="hidden" name="star" value="3" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary" >
                                2 <i class="fa fa-star" ></i> 

                                (${dao.countCommentListByStar(requestScope.PRODUCTID,2)})
                                <input type="hidden" name="star" value="2" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary" >
                                1 <i class="fa fa-star" ></i> 

                                (${dao.countCommentListByStar(requestScope.PRODUCTID,1)})
                                <input type="hidden" name="star" value="1" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary" >
                                Gần nhất 
                                <input type="hidden" name="star" value="6" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary" >
                                Lâu nhất 
                                <input type="hidden" name="star" value="7" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="CommentServlet" method="GET">
                            <button type="submit" class="btn btn-secondary" >
                                Your comment 
                                <input type="hidden" name="star" value="8" />
                                <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                            </button>
                        </form>
                    </li>
                </div>
            </div>

            <!--testimonials-box-container------------->


            <!--box-1------------->
            <div class="testimonials-box">
                <div class="box-top">
                    <!--profile------------->
                    <div class="profile">
                        <div class="profile-img">
                            <img src="asset/images/296059556_584764606675134_7640748425626229317_n.jpg" >
                        </div>

                        <div class="name-user"> 
                            <c:set var="productID" value="${requestScope.PRODUCTID}"/>
                            ProductID = ${productID}
                            <form action="CommentServlet" method="GET">
                                <input type="hidden" name="txtProductID" value="${productID}" />
                                <div class="enter-comment">								
                                    <li>
                                        <input placeholder="Enter your comment" type="text" name="txtDescription">

                                    </li>				
                                </div>
                                <div class="rating-cmt">
                                    <div class="eva-cmt">

                                        <li class="eva-star-cmt">

                                            <input type="radio" name="rating" value="5" id="star5">
                                            <label for="star5"></label>
                                            <input type="radio" name="rating" value="4" id="star4">
                                            <label for="star4"></label>
                                            <input type="radio" name="rating" value="3" id="star3">
                                            <label for="star3"></label>
                                            <input type="radio" name="rating" value="2" id="star2">
                                            <label for="star2"></label>
                                            <input type="radio" name="rating" value="1" id="star1">
                                            <label for="star1"></label>
                                        </li>
                                    </div>                           
                                </div>
                                <c:if test="${not empty requestScope.MESSAGE}">
                                    <font color='red'>
                                    ${requestScope.MESSAGE}
                                    </font><br/>
                                </c:if>
                                <c:if test="${not empty requestScope.MESSAGE2}">
                                    <font color='red'>
                                    ${requestScope.MESSAGE2}
                                    </font><br/>
                                </c:if>
                                <div class="enter-comment">								
                                    <li>
                                        <button type="submit" class="btn btn-light form-control" name="txtSubmitComment" value="submit">Submit</button>
                                    </li>				
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>


            <!-----------------------------------------box-2-------------------------------------->
            <c:if test="${not empty sessionScope}">
                <c:set var="list" value="${sessionScope.INFOCOMMENT}"/>
                <c:if test="${not empty list}">
                    <c:forEach var="dto" items="${sessionScope.INFOCOMMENT}">
                        <div>
                            <div class="col-lg-12">
                                <div class="testimonials-box">
                                    <div class="box-top">
                                        <!--profile------------->
                                        <div class="profile">
                                            <div class="profile-img">
                                                <img src="asset/images/296059556_584764606675134_7640748425626229317_n.jpg" >
                                            </div>
                                            <div class="name-user">
                                                <jsp:useBean id="daoUser" class="DVHT.userdetails.UserDetailsDAO"/>                      
                                                <strong>${daoUser.getInfoUser(dto.getUserID()).getFullName()}</strong>
                                                ${dto.getDate()}
                                                <br>

                                                <p>
                                                    Point of this product:  ${dto.getPoint()} <i class="fa fa-star" ></i>
                                                </p>
                                            </div>
                                        </div>
                                        <!--review------------->
                                        <c:set var="user" value="${sessionScope.USER}"/>
                                        <div class="review">
                                            ${dto.getCommentID()}
                                            <!-- The modal edit ----------------------------------->
                                            <c:if test="${user.getUserID() == dto.getUserID()}">
                                                <div class="container-fluid">
                                                    <button type="button" class="btn btn-primary btn-lg fa fa-pencil-square" data-toggle="modal" data-target="#editPopup">
                                                    </button>
                                                    <!-- The modal -->
                                                    <div class="modal fade" id="editPopup" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">                                                        
                                                        <form action="EditCommentServlet" method="GET">
                                                            <div class="modal-dialog" role="document">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                            <span aria-hidden="true">&times;</span>
                                                                        </button>
                                                                        <h4 class="modal-title" id="modalLabel">Change your comment. </h4>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <input type="text" name="txtNewComment" class="form-control" placeholder="Your new change comment..." value="" />
                                                                        <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                                                                        <input type="hidden" name="txtUserID" value="${dto.getUserID()}" />
                                                                    </div>

                                                                    <!--button-->
                                                                    <div class="row">
                                                                        <div class="modal-footer col-xs-6">
                                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                        </div>
                                                                        <div class="modal-footer col-xs-6">                                                                                                                                              
                                                                            <button type="submit" class="btn btn-secondary" >Submit</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                        </form>
                                                    </div>
                                                </div>
                                            </c:if>

                                            <!------------------------------------------->
                                            <!-- The modal delete----------------------------------->
                                            <c:if test="${user.getUserID() == dto.getUserID()}">
                                                <div class="container-fluid">
                                                    <button type="button" class="btn btn-primary btn-lg fa fa-trash" data-toggle="modal" data-target="#deletePopup">
                                                    </button>
                                                    <!-- The modal -->
                                                    <div class="modal fade" id="deletePopup" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                                                        <form action="DeleteCommentServlet" method="GET">
                                                            <div class="modal-dialog" role="document">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                            <span aria-hidden="true">&times;</span>
                                                                        </button>
                                                                        <h4 class="modal-title" id="modalLabel">Are you sure you want to delete this comment!</h4>
                                                                        <input type="hidden" name="txtCommentID" value="${dto.getCommentID()}" />                                             
                                                                        <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                                                                        <input type="hidden" name="txtUserID" value="${dto.getUserID()}" />
                                                                    </div>

                                                                    <div class="row">
                                                                        <div class="modal-footer col-xs-6">
                                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                        </div>
                                                                        <div class="modal-footer col-xs-6">                                                                                                                                              
                                                                            <button type="submit" class="btn btn-secondary" >Delete</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </c:if>

                                            <!------------------------------------------->
                                            <!-- The modal flag----------------------------------->

                                            <c:if test="${user.getUserID() != dto.getUserID()}">

                                                <div class="container-fluid">
                                                    <button type="button" class="btn btn-primary btn-lg fa fa-flag" data-toggle="modal" data-target="#flagPopup">
                                                    </button>
                                                    <!-- The modal -->
                                                    <div class="modal fade" id="flagPopup" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                                                        <form action="FlagCommentServlet" method="GET">

                                                            <input type="hidden" name="txtCommentID" value="${dto.getCommentID()}" />                                             
                                                            <input type="hidden" name="txtProductID" value="${requestScope.PRODUCTID}" />
                                                            <input type="hidden" name="txtUserID" value="${user.getUserID()}" />
                                                            <div class="modal-dialog" role="document">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                            <span aria-hidden="true">&times;</span>
                                                                        </button>
                                                                        <h4 class="modal-title" id="modalLabel">Flag that this comment is being used for bad purposes such as: </h4>
                                                                        <h5>1/ Deliberately misleading review</h5>
                                                                        <h5>2/ Spam review to reduce rate</h5>
                                                                        <h5>3/ Toxic</h5>
                                                                        <h5>4/ Other...</h5>                                                                       

                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <input type="text"  class="form-control" name="txtInfoFlag" value="" placeholder="The reason for this flag..." />


                                                                    </div>
                                                                    <!--button-->
                                                                    <div class="row">
                                                                        <div class="modal-footer col-xs-6">
                                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                        </div>
                                                                        <div class="modal-footer col-xs-6">                                                                                                                                              
                                                                            <button type="submit" class="btn btn-secondary" >Submit</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </c:if>

                                            <!------------------------------------------->                                        

                                        </div>         
                                    </div>
                                    <!--comment------------->
                                    <div class="client-comment">
                                        <p>
                                            ${dto.getDescription()}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>

                    <button class="btn center-block loadMore">
                        Load more
                    </button>

                    <script src="asset/js/jquery-latest.min.js"></script>

                </c:if>


            </c:if>
            <c:if test="${empty sessionScope.INFOCOMMENT}">
                <p>Do not have any review</p>
            </c:if>
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

        <script>
            $(".col-lg-12").slice(0, 3).show();
            $(".loadMore").on("click", function () {
                $(".col-lg-12:hidden").slice(0, 3).show();
                if ($(".col-lg-12:hidden").length == 0)
                {
                    $(".loadMore").fadeOut();
                }
            })

        </script>
        <script>

            function openPopup() {
                document.getElementById("myFormEditComment").style.display = "block";
            }

            function closePopup() {
                document.getElementById("myFormEditComment").style.display = "none";
            }
        </script>           

        <!-- jQuery library -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Bootstrap JS -->
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

        <!-- Initialize Bootstrap functionality -->
        <script>
            // Initialize tooltip component
            $(function () {
                $('[data-toggle="tooltip"]').tooltip()
            })

            // Initialize popover component
            $(function () {
                $('[data-toggle="popover"]').popover()
            })
        </script>


    </body>

</html>