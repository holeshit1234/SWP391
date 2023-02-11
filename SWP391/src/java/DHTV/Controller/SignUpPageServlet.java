/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;


import DHTV.address.AddressDAO;
import DHTV.address.AddressDTO;
import DVHT.userdetails.UserDetailSignUpError;
import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
import DVHT.utils.MyAplications;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vinht
 */
@WebServlet(name = "SignUpPageServlet", urlPatterns = {"/SignUpPageServlet"})
public class SignUpPageServlet extends HttpServlet {

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
                
        //Get siteMaps from context Scope
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        String url = siteMaps.getProperty(MyAplications.SignUpPageServlet.SIGN_UP_PAGE);
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        String email = request.getParameter("txtEmail");
        String phone =request.getParameter("txtPhone");        
        String DOB = request.getParameter("txtDOB");
        Date dob = Date.valueOf(DOB);
        String province = request.getParameter("txtProvince");
        String district = request.getParameter("txtDistrist");
        String ward = request.getParameter("txtWard");
        String street = request.getParameter("txtStreet");
        byte[] bytes1 = street.getBytes(StandardCharsets.ISO_8859_1);
        street = new String(bytes1, StandardCharsets.UTF_8);
        String gender = request.getParameter("gender");
        //gender ="Nam"; 
//đang k lấy dc gender nên để tạm
        
        
        //print to check
        /*
        UserDetailsDTO user = new UserDetailsDTO(0, 0, username, password, email, fullname, phone, dob, gender);
        System.out.println(user);
        AddressDTO add = new AddressDTO(0, 0, province, ward, street, null, district);
        System.out.println(add);
        */
        
        //process
        UserDetailSignUpError errors = new UserDetailSignUpError();
        boolean isError = false;
        try {
            if (username.trim().length() < 4 || username.trim().length() > 30) {
                isError = true;
                errors.setUsernameLengthErr("Username requires input from "
                        + "4 to 30 characters!!");
            }
            if (password.trim().length() < 4 || password.trim().length() > 20) {
                isError = true;
                errors.setPasswordLengthErr("Password requires input from "
                        + "4 to 20 characters!!");
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
                String message ="";
                UserDetailsDAO dao = new UserDetailsDAO();
                boolean usernameExist = dao.usernameExist(username);
                if (usernameExist) {
                    message += "User name has existed! Please enter different user name!!\n";
                    request.setAttribute("MESSAGE", message);
                    log("User name has existed!! Can not registration !");
                }
                else{
                    int role = 3;
                    dob=null;
                    UserDetailsDTO userAccount = new UserDetailsDTO(role, role, username, password, email, fullname, phone, dob, gender);

                    int key = 0;
                    key = dao.addUser(userAccount);
                    if (key>0){
                        
                        AddressDAO dao2 = new AddressDAO();
                        AddressDTO address = new AddressDTO(0, key, province, ward, street, province, district); 
                        boolean result = dao2.addAddress(address);                        
                        if(result) url = siteMaps.getProperty(MyAplications.SignUpPageServlet.FINISH_PAGE);
                        
                    }
                }
            }
        } catch (SQLException ex) {
            log("CreateNewAccountServlet SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("CreateNewAccountServlet Naming: " + ex.getMessage());
        }
        finally {
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
