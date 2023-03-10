/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
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

    
    private final String SHOW_INDEX_ITEM ="ShowIdexItemServlet";
    private final String ADMIN_PAGE ="admin.jsp";
    private final String MANAGER_PAGE ="manager.html";
     
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

        
        String url = SHOW_INDEX_ITEM;

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
                        url = ADMIN_PAGE;
                        //url = MyAplications.LoginServlet.ADMIN_PAGE;
                    } else if (result.getRoleID() == 2) {
                        url = MANAGER_PAGE;
                        //url = MyAplications.LoginServlet.MANAGER_PAGE;
                    } else {
                        //url = MyAplications.LoginServlet.SEARCH_STORE_PAGE;
                        url = SHOW_INDEX_ITEM;
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
