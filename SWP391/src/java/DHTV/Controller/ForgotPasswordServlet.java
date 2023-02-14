/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
import DVHT.utils.MyAplications;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/ForgotPasswordServlet"})
public class ForgotPasswordServlet extends HttpServlet {

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
                MyAplications.ForgotPasswordServlet.FORGOTPASSWORD_PAGE);

        String email = request.getParameter("txtEmail");
        String message = "Hello";
        String subject = "Hello world";
        try {
            UserDetailsDAO dao = new UserDetailsDAO();
            UserDetailsDTO dto = dao.findEmail(email);
            if (dto == null) {
                request.setAttribute("FORGOTPASSWORDERROR", "Email is incorrect");
            } else {
                Email from = new Email("no-reply@swp391project.net");
                Email to = new Email(email);
                Content content = new Content("text/plain", message);
                Mail mail = new Mail(from, subject, to, content);

                SendGrid sg = new SendGrid("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");
                Request req = new Request();

                req.setMethod(Method.POST);
                req.setEndpoint("mail/send");
                req.setBody(mail.build());
                Response res = sg.api(req);
                System.out.println("Send");
            }
        } catch (Exception ex) {
            log("ForgotPassword _ Exception _ " + ex.getMessage());
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
