/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.controller;

import dunghm.registration.RegistrationDAO;
import dunghm.registration.RegistrationDTO;
import dunghm.utils.MyApplication;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
 * @author dunghm
 */
@WebServlet(name = "FirstTimeLoginServlet", urlPatterns = {"/FirstTimeLoginServlet"})
public class FirstTimeLoginServlet extends HttpServlet {

//    private final String LOGIN_PAGE = "login.html";
//    private final String SEARCH_PAGE = "search.jsp";

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
        
        String url = (String) 
                siteMaps.get(MyApplication.firstTimeServlet.LOGIN_PAGE);

        try {
            //1. get cookie
            Cookie[] cookies = request.getCookies();
            //2.Read cookie
            if(cookies != null){
                Cookie lastcookie = cookies[cookies.length-1];
                
                String username = lastcookie.getName();                            
                String password = lastcookie.getValue();
                
                //3. call DAo to check login
                RegistrationDAO dao = new RegistrationDAO();                
                RegistrationDTO result = dao.checkLogin(username, password);
            //4. processif (cookies != null) {
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
                    HttpSession session = request.getSession();
                    session.setAttribute("User", result);
                    
                 if (result != null) {
                        if (result.isRole() == true) {
                    //url = siteMaps.getProperty(MyApplication.LoginServlet.MANAGER_PAGE);;
                    url = MyApplication.firstTimeServlet.MANAGER_PAGE;
                } else {
                    //url = siteMaps.getProperty(MyApplication.LoginServlet.SEARCH_STORE_PAGE);;
                    url = MyApplication.firstTimeServlet.SEARCH_STORE_PAGE;
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
