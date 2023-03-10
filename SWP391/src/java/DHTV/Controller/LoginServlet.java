/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
import DVHT.userdetails.UserDetailsErr;
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

    

        //get parameter
        //   String checkbox = request.getParameter("chkRemember");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String ckrem = request.getParameter("chkremember");

        UserDetailsErr error = new UserDetailsErr();
        boolean flag = false;
        //String url = siteMaps.getProperty(MyApplication.LoginServlet.INVALID_PAGE);
        String url = "login.jsp";
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
                        url = "ShowUserByManagerServlet";
                
                    } else if (result.getRoleID() == 2) {
                        url ="ShowUserByManagerServlet";
   
                    } else {
             
              
                        url = SHOW_INDEX_ITEM;
                    }

                    //1. get session
                    HttpSession session = request.getSession();
                    //2. set attribute
                    session.setAttribute("USER", result);
                    if (ckrem != null) {
                        Cookie cookie = new Cookie(username, password);
                        cookie.setMaxAge(60 * 10);
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
