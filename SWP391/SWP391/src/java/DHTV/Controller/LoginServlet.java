/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
import DVHT.userdetails.UserDetailsErr;
import DVHT.utils.MyAplications;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author User
 */
public class LoginServlet extends HttpServlet {

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
        String ckrem = request.getParameter("chkremember");

        UserDetailsErr error = new UserDetailsErr();
        boolean flag = false;
        //String url = siteMaps.getProperty(MyApplication.LoginServlet.INVALID_PAGE);
        String url = (String) siteMaps.getProperty(MyAplications.LoginServlet.ERROR_PAGE);
        try {

            if (password.trim().length() < 1 || username.trim().length() < 1) {
                flag = true;
                error.setEmptyUserNamePassWord("Username or PassWord is empty");
            }
            //System.out.println(flag);
            if (flag) {
                request.setAttribute("L_ERROR", error);
            } else {
                //call DAO                
                UserDetailsDAO dao = new UserDetailsDAO();

                UserDetailsDTO result = dao.checkLogin(username, password);
                if (result == null) {
                    flag = true;
                    error.setWrongUserNamePassWord("Incorect UserName or Password");
                }
                if (flag) {
                    request.setAttribute("L_ERROR", error);
                } else {

                    if (result.getRoleID() == 1) {
                        url = siteMaps.getProperty(MyAplications.LoginServlet.ADMIN_PAGE);
                        //url = MyAplications.LoginServlet.ADMIN_PAGE;
                    } else if (result.getRoleID() == 2) {
                        url = siteMaps.getProperty(MyAplications.LoginServlet.MANAGER_PAGE);
                        //url = MyAplications.LoginServlet.MANAGER_PAGE;
                    } else {
                        //url = MyAplications.LoginServlet.SEARCH_STORE_PAGE;
                        url = siteMaps.getProperty(MyAplications.showItems.SEARCH_STORE_SERVLET);
                    }

                    //1. get session
                    HttpSession session = request.getSession();
                    //2. set attribute
                    session.setAttribute("USER", result);
                    if (ckrem != null) {
                        Cookie cookie = new Cookie(username, password);
                        cookie.setMaxAge(60 * 3);
                        response.addCookie(cookie);
                    } else {
                        Cookie cookie = new Cookie(username, password);
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        } catch (SQLException ex) {
            log("LoginServlet _SQL_ " + ex.getMessage());
        } catch (/*ClassNotFoundException*/NamingException ex) {
            log("LoginServlet _Naming_ " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
//            response.sendRedirect(url);
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
