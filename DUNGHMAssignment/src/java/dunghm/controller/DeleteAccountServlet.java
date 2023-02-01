/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.controller;

import dunghm.registration.RegistrationDAO;
import dunghm.registration.RegistrationDTO;
import dunghm.registration.RegistrationDeleteError;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dunghm
 */
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {

    //private final String DELETE_ERR = "delete_err.html";
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

        // get Parameter
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("LastSearchValue");

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");

        String url = (String) siteMaps.get(MyApplication.DeleteAccountServlet.DELETE_ERR);
        
        RegistrationDeleteError err = new RegistrationDeleteError();
        boolean flag = false;
                
        try {
            //get session
            HttpSession session = request.getSession(false);

            if (session != null) {
                RegistrationDTO dto = (RegistrationDTO) session.getAttribute("User");
                if (username.equals(dto.getUsername())) {
                    System.out.println(username);
                    flag = true;
                    err.setDeleteCurrentAccount("Delete failed!!The account is available!!");
                }
                if(flag){
                    request.setAttribute("ERROR_DELETE", err);
                    url = (String) siteMaps.get(MyApplication.DeleteAccountServlet.SEARCH_RESULT)
                                + "?txtSearchValue=" + searchValue;
                }else{
                    RegistrationDAO dao = new RegistrationDAO();
                    boolean result = dao.deleteAccount(username);
                    if (result) {
                        String signal = "Delete successfull!";
                        request.setAttribute("STATUS", signal);
                        url = (String) siteMaps.get(MyApplication.DeleteAccountServlet.SEARCH_RESULT)
                                + "?txtSearchValue=" + searchValue;

                    }//check if username has login wasn't choose
                } 
            }//check if user has login

        } catch (SQLException ex) {
            log("DeleAccountServlet _SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleAccountServlet _Naming" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            // response.sendRedirect(url);
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
