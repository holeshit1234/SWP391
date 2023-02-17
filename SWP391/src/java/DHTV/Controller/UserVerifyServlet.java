/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.forgotpassword.SendEmail;
import DHTV.forgotpassword.UserDetailsForgetPasswordDTO;
import DHTV.forgotpassword.VerifyError;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "UserVerifyServlet", urlPatterns = {"/UserVerifyServlet"})
public class UserVerifyServlet extends HttpServlet {

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
        String url = siteMaps.getProperty(MyAplications.UserVerifyServlet.USERVERIFY_PAGE);

        String email = request.getParameter("txtEmail");
        boolean errorFound = false;
        VerifyError errors = new VerifyError();

        try {
            if (email.trim().length() < 1) {
                errorFound = true;
                errors.setEmailLengthError("You can't leave this empty");
            }
            if (errorFound) {
                request.setAttribute("VERIFYMAIL_SCOPE", errors);
            } else {
                UserDetailsDAO dao = new UserDetailsDAO();
                UserDetailsDTO result = dao.findEmail(email);
                if (result == null) {
                    errors.setEmailNotExisted("Sorry, this email is not sign up");
                    errorFound = true;
                } else {
                    String emailcheck = result.getEmail();
                    String usernamecheck = result.getUserName();
                    if (emailcheck.equals(usernamecheck)) {
                        errors.setSignUpWithGoogleAccount("You login with google account");
                        errorFound = true;
                    }
                }
                if (errorFound) {
                    request.setAttribute("VERIFYMAIL_SCOPE", errors);
                } else {
                    //create instance object of the SendEmail Class
                    SendEmail sm = new SendEmail();
                    //get the 6-digit code
                    String code = sm.getRandom();
                    //craete new user using all information
                    UserDetailsForgetPasswordDTO user
                            = new UserDetailsForgetPasswordDTO(email, code);
                    //call the send email method
                    boolean test = sm.sendEmail(user);
                    //check if the email send successfully
                    if (test) {
                        HttpSession session = request.getSession();
                        session.setAttribute("authcode", user);
                        session.setAttribute("email", email);
                        url = siteMaps.getProperty(
                                MyAplications.UserVerifyServlet.VERIFYCODE_PAGE);
                    } else {
                        request.setAttribute("VERIFYMAIL_SCOPE", "Failed to send verification email");
                    }
                }
            }
        } catch (SQLException ex) {
            log("UserVerifyServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("UserVerifyServlet_Naming " + ex.getMessage());
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
