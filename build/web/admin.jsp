<%-- 
    Document   : admin
    Created on : Feb 2, 2023, 5:04:21 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADmin PAGE</title>
    </head>
    <body>
        admin <br/>
        </br> <a href="LogoutAccountServlet"> Log Out</a>
        
        
        </c:forEach>


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

                                            <form action="AddAddressController" method="POST">

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
                                                        <span class="detail">Province</span>
                                                        <input type="text" placeholder="Enter your province" name="txtProvince"
                                                               value="" required>
                                                    </div>
                                                    <div class="input-box">
                                                        <span class="detail">district</span>
                                                        <input type="text" placeholder="Enter your district" name="txtDistrict"
                                                               value="" required>
                                                    </div>
                                                    <div class="input-box">
                                                        <span class="detail">ward</span>
                                                        <input type="text" placeholder="Enter your ward" name="txtWard"
                                                               value="" required>
                                                    </div>
                                                    <div class="input-box">
                                                        <span class="detail">Address</span>
                                                        <input type="text" placeholder="Enter your address" name="txtAddress"
                                                               value=""  required>
                                                    </div>
                                                </div>
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



                            <div class="modal fade" id="popup2" tabindex="-1" role="dialog"
                                 aria-labelledby="popup2Label" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="popup2Label">Update address</h5>
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Đóng">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="user-detail">
                                                <div class="input-box">
                                                    <span class="detail">Full name</span>
                                                    <input type="text" placeholder="Enter your name"
                                                           required>
                                                </div>
                                                <div class="input-box">
                                                    <span class="detail">Phone</span>
                                                    <input type="number" placeholder="Enter your phone"
                                                           required>
                                                </div>
                                                <div class="input-box">
                                                    <span class="detail">Province</span>
                                                    <input type="text" placeholder="Enter your province"
                                                           required>
                                                </div>
                                                <div class="input-box">
                                                    <span class="detail">district</span>
                                                    <input type="text" placeholder="Enter your district"
                                                           required>
                                                </div>
                                                <div class="input-box">
                                                    <span class="detail">ward</span>
                                                    <input type="text" placeholder="Enter your ward"
                                                           required>
                                                </div>
                                                <div class="input-box">
                                                    <span class="detail">Address</span>
                                                    <input type="text" placeholder="Enter your address"
                                                           required>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary"
                                                    data-dismiss="modal">close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                    </c:if>
                </c:if>
            </c:if>
    </body>
</html>
