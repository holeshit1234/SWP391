/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.Controller;

import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
import DVHT.utils.MyAplications;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
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
@WebServlet(name = "FirstTimeRequestServlet", urlPatterns = {"/FirstTimeRequestServlet"})
public class FirstTimeRequestServlet extends HttpServlet {

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

        String url = (String) siteMaps.get(MyAplications.firstTimeServlet.LOGIN_PAGE);

        try {
            //1. get cookie
            Cookie[] cookies = request.getCookies();
            //2.Read cookie
            if (cookies != null) {
                Cookie lastcookie = cookies[cookies.length - 1];

                String username = lastcookie.getName();
                String password = lastcookie.getValue();

                //3. call DAo to check login
                UserDetailsDAO dao = new UserDetailsDAO();
                UserDetailsDTO result = dao.checkLogin(username, password);
                //4. processif (cookies != null) {
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
                HttpSession session = request.getSession();
                session.setAttribute("USER", result);

                if (result != null) {
                    if (result.getRoleID() == 1) {
                        url = siteMaps.getProperty(MyAplications.firstTimeServlet.ADMIN_PAGE);
                        //url = MyAplications.LoginServlet.ADMIN_PAGE;
                    } else if (result.getRoleID() == 2) {
                        url = siteMaps.getProperty(MyAplications.firstTimeServlet.MANAGER_PAGE);
                        //url = MyAplications.LoginServlet.MANAGER_PAGE;
                    } else {
                        //url = MyAplications.LoginServlet.SEARCH_STORE_PAGE;
                        url = siteMaps.getProperty(MyAplications.firstTimeServlet.SEARCH_STORE_PAGE);
                    }
                }
//                }//cookie end traversal
            }//cookie existed
        } catch (SQLException ex) {
            log("FirstTimeLoginServlet _SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("FirstTimeLoginServlet _Naming" + ex.getMessage());
        } finally {
            response.sendRedirect(url);
//              RequestDispatcher rd = request.getRequestDispatcher(url);
//              rd.forward(request, response);
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
