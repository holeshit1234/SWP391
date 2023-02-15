/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DVHT.userdetails.UserDetailError;
import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
import DVHT.utils.MyAplications;
import java.io.IOException;
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
        String url = siteMaps.getProperty(MyAplications.SignUpPageServlet.REGISTRATION_PAGE);
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        String email = request.getParameter("txtEmail");
        String phone =request.getParameter("txtPhone");        

        
        UserDetailError errors = new UserDetailError();
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
                }
                else{
                    int role = 3;
                    UserDetailsDTO userAccount = new UserDetailsDTO(role, role, username, password, email, fullname, phone);
                    boolean result = false;
                    result = dao.addUser(userAccount);
                    if (result) 
                        url = siteMaps.getProperty(MyAplications.SignUpPageServlet.FINISH_PAGE);
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
