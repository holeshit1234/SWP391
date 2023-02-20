/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DVHT.userdetails.UserDetailError;
import DVHT.userdetails.UserDetailsDAO;
import DVHT.utils.MyAplications;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/ResetPasswordServlet"})
public class ResetPasswordServlet extends HttpServlet {

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

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        String url = siteMaps.getProperty(
                MyAplications.ResetPasswordServlet.RESETPASSWORD_PAGE);

        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        boolean errorFound = false;
        UserDetailError errors = new UserDetailError();
        try {
            if (password.trim().length() < 1) {
                errorFound = true;
                errors.setPasswordLengthErr("You can't leave this empty");
            } else if (password.trim().length() < 6 || password.trim().length() > 30) {
                errorFound = true;
                errors.setPasswordLengthErr(
                        "Password is required input from 6 to 30 characters");
            } else if (!password.trim().equals(confirm.trim())) {
                errorFound = true;
                errors.setConfirmNotMatchErr("Confirm must be matched password");
            }
            if (errorFound) {
                //catch error
                request.setAttribute("RESETPASSWORD_ERROR", errors);
                //transfer to inform users
            } else {
                HttpSession session = request.getSession();
                String email = (String) session.getAttribute("email");
                UserDetailsDAO dao = new UserDetailsDAO();
                boolean result = dao.updatePassword(email, password);
                if (result) {
                    url = siteMaps.getProperty(
                            MyAplications.ResetPasswordServlet.LOGIN_PAGE);
                }
            }
        }catch(SQLException ex){
            log("ResetPasswordServlet_SQL " + ex.getMessage());
        } catch(NamingException ex){
            log("ResetPasswordServlet_Naming" + ex.getMessage());
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
