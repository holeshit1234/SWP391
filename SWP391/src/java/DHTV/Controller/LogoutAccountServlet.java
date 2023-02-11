/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DVHT.userdetails.UserDetailsDTO;
import DVHT.utils.MyAplications;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "LogoutAccountServlet", urlPatterns = {"/LogoutAccountServlet"})
public class LogoutAccountServlet extends HttpServlet {

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
        
        //1. get servlet context
        ServletContext context = this.getServletContext();
        //2 get sitemap
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        String username ="";
        String url = "";
        Cookie cookie = null;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDetailsDTO result = (UserDetailsDTO) session.getAttribute("USER");
                if (result != null) {
                    username = result.getUserName();
                }
                session.invalidate();
            }//end of destroy session 
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                for (Cookie ck : cookies) {
                    if (ck.getName().equals(username)) {
                        cookie = new Cookie(username, "");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }//end of destroy cookie
                }//end of traverse cookie
            }//end of user is logout
        } finally {
            url = MyAplications.LogoutAccountServlet.LOGOUT_PAGE;
            response.sendRedirect(url);
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
