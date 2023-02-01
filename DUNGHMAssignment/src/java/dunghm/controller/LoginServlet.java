/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.controller;

import dunghm.registration.RegistrationDAO;
import dunghm.registration.RegistrationDTO;
import dunghm.utils.DBHelper;
import dunghm.utils.MyApplication;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dunghm
 */
public class LoginServlet extends HttpServlet {

//    private final String INVALID_PAGE = "invalid.html";
//    private final String MANAGER_PAGE = "Manager.jsp";
//    private final String SEARCH_STORE_PAGE = "Store.jsp";
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
        //2. get properties get sitemap
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");

        //get parameter
        //   String checkbox = request.getParameter("chkRemember");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        //String url = siteMaps.getProperty(MyApplication.LoginServlet.INVALID_PAGE);
        String url = MyApplication.LoginServlet.INVALID_PAGE;
        try {
            //save cookie username, password, checkbox
//            Cookie cu = new Cookie("CUSER", username);
////            System.out.println("");
//            Cookie cp = new Cookie("CPASS", password);
////            Cookie cr = new Cookie("CBOX", checkbox);
////            //check checkbox ticked
//            //if (checkbox != null) {
//                cu.setMaxAge(60 * 5);
//                cp.setMaxAge(60 * 5);               
////            //} else {
////             //   cu.setMaxAge(0);
////    /        //    cp.setMaxAge(0);
////            //}
////            //response to file at brower
//            response.addCookie(cu);
//            response.addCookie(cp);
//            response.addCookie(cr);
            //call DAO                
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO result = dao.checkLogin(username, password);

            if (result != null) {

                if (result.isRole() == true) {
                    //url = siteMaps.getProperty(MyApplication.LoginServlet.MANAGER_PAGE);;
                    url = MyApplication.LoginServlet.MANAGER_PAGE;
                } else {
                    //url = siteMaps.getProperty(MyApplication.LoginServlet.SEARCH_STORE_PAGE);;
                    url = MyApplication.LoginServlet.SEARCH_STORE_PAGE;
                }

                //1. get session
                HttpSession session = request.getSession();
                //2. set attribute
                session.setAttribute("User", result);
                Cookie cookie = new Cookie(username, password);
                cookie.setMaxAge(60 * 3);
                response.addCookie(cookie);
            }
        } catch (SQLException ex) {
            log("LoginServlet _SQL_ " + ex.getMessage());
        } catch (/*ClassNotFoundException*/NamingException ex) {
            log("LoginServlet _Naming_ " + ex.getMessage());
        } finally {
            //RequestDispatcher rd = request.getRequestDispatcher(url);
            // rd.forward(request, response);
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
