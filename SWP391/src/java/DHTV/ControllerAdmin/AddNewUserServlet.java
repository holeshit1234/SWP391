/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.ControllerAdmin;

import DHTV.address.AddressDAO;
import DHTV.address.AddressDTO;
import DVHT.userdetails.UserDetailSignUpError;
import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "AddNewUserServlet", urlPatterns = {"/AddNewUserServlet"})
public class AddNewUserServlet extends HttpServlet {

    private final String SIGNUP_PAGE = "AddNewUser.jsp";
    private final String SHOW_USER = "ShowUserByManagerServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SIGNUP_PAGE;

        //Get parameter from signup.jsp
        String button = request.getParameter("btAction");

        String username = request.getParameter("txtUsername");
        byte[] bytes1 = username.getBytes(StandardCharsets.ISO_8859_1);
        username = new String(bytes1, StandardCharsets.UTF_8);
        //        
        String password = request.getParameter("txtPassword");
        bytes1 = password.getBytes(StandardCharsets.ISO_8859_1);
        password = new String(bytes1, StandardCharsets.UTF_8);
        //
        String confirm = request.getParameter("txtConfirm");
        bytes1 = confirm.getBytes(StandardCharsets.ISO_8859_1);
        confirm = new String(bytes1, StandardCharsets.UTF_8);
        //
        String fullname = request.getParameter("txtFullname");
        bytes1 = fullname.getBytes(StandardCharsets.ISO_8859_1);
        fullname = new String(bytes1, StandardCharsets.UTF_8);
        //
        String email = request.getParameter("txtEmail");
        bytes1 = email.getBytes(StandardCharsets.ISO_8859_1);
        email = new String(bytes1, StandardCharsets.UTF_8);
        //
        String phone = request.getParameter("txtPhone");
        bytes1 = phone.getBytes(StandardCharsets.ISO_8859_1);
        phone = new String(bytes1, StandardCharsets.UTF_8);
        //
        String DOB = request.getParameter("txtDOB");
        Date dob = Date.valueOf(DOB);
        //
        String province = request.getParameter("txtProvince");
        bytes1 = province.getBytes(StandardCharsets.ISO_8859_1);
        province = new String(bytes1, StandardCharsets.UTF_8);
        //
        String district = request.getParameter("txtDistrict");
        bytes1 = district.getBytes(StandardCharsets.ISO_8859_1);
        district = new String(bytes1, StandardCharsets.UTF_8);
        //
        String ward = request.getParameter("txtWard");
        bytes1 = ward.getBytes(StandardCharsets.ISO_8859_1);
        ward = new String(bytes1, StandardCharsets.UTF_8);
        //
        String street = request.getParameter("txtStreet");
        bytes1 = street.getBytes(StandardCharsets.ISO_8859_1);
        street = new String(bytes1, StandardCharsets.UTF_8);

        String gender = request.getParameter("gender");
        
        String roleid = request.getParameter("txtRole");
        int RoleID = Integer.parseInt(roleid);
        //process
        //create errors variable for sign up
        UserDetailSignUpError errors = new UserDetailSignUpError();
        boolean isError = false;
        try {
            if (button.equals("Register")) {
                if (username.trim().length() < 4 || username.trim().length() > 30) {
                    isError = true;
                    errors.setUsernameLengthErr("Username requires input from "
                            + "4 to 30 characters!!");
                }
                if (password.trim().length() < 5 || password.trim().length() > 20) {
                    isError = true;
                    errors.setPasswordLengthErr("Password requires input from "
                            + "5 to 20 characters!!");
                } else if (!(password.trim().equals(confirm.trim()))) {
                    isError = true;
                    errors.setConfirmNotMatchErr("Confirm doesn't match with the "
                            + "entered Password!!!");
                }
                if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                    isError = true;
                    errors.setFullnameLengthErr("Full name requires input from "
                            + "2 to 50 characters!!");
                }

                if (isError) {
                    request.setAttribute("ERROR", errors);
                } else {
                    // Create Account
                    // check username and email
                    String message = "";
                    UserDetailsDAO dao = new UserDetailsDAO();
                    boolean usernameExist = dao.usernameExist(username);
                    boolean emailExist = dao.emailExist(email);
                    if (emailExist) {
                        message += "Your email has existed! Please enter different email!! \n";
                        log("Email has existed!! Can not registration !");
                    }
                    if (usernameExist) {
                        message += "User name has existed! Please enter different user name!! \n";
                        log("User name has existed!! Can not registration !");
                    }
                    if (!message.isEmpty()) {
                        request.setAttribute("MESSAGE", message);
                    }
                    //start to add info
                    if (!emailExist && !usernameExist) {
                        //set default value                       
                        dob = null;
                        // new dto from the value
                        UserDetailsDTO userAccount = new UserDetailsDTO(0, RoleID, username, password, email, fullname, phone, dob, "23b33efd6739a27e12124c02169572c0.jpg", gender, true);
                        //process - add into database
                        int key = 0;
                        key = dao.addUser(userAccount);
                        if (key > 0) {
                            AddressDAO dao2 = new AddressDAO();
                            AddressDTO address = new AddressDTO(0, key, province, ward, street, district, true);
                            boolean result = dao2.addAddress(address);
                            if (result) {
                                url =SHOW_USER;
                            }
                        } else {
                            message += "Some thing wrong here! Can not registration, please try again.\n";
                            request.setAttribute("MESSAGE", message);
                            log("Can not registration!");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            log("CreateNewAccountServlet SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("CreateNewAccountServlet Naming: " + ex.getMessage());
        } catch (ParseException ex) {
            log("CreateNewAccountServlet Parse: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
