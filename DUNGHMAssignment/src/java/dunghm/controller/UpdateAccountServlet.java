/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.controller;

import dunghm.registration.RegistrationCreateErr;
import dunghm.registration.RegistrationDAO;
import dunghm.registration.RegistrationUpdateError;
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

/**
 *
 * @author dunghm
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

    //private final String  UPDATE_ERR="update_err";
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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String admin = request.getParameter("chkisAdmin");
        boolean role = false;
        if (admin != null) {
            role = true;
        }
        String searchValue = request.getParameter("LastSearchValue");

        RegistrationUpdateError err = new RegistrationUpdateError();
        boolean flag = false;

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        String url = (String) siteMaps.get(MyApplication.UpdateAccountServlet.UPDATE_ERR);

        try {

            if (password.trim().length() > 20 || password.trim().length() < 6) {
                flag = true;
                err.setPasswordLengthError("Password about 6 to 20");
            }
            if (flag) {
                //luu loi
                request.setAttribute("ERROR", err);
                 url = (String) siteMaps.get(MyApplication.UpdateAccountServlet.SEARCH_RESULT_PAGE)
                            + "?txtSearchValue=" + searchValue;
            } else {
                //1.CAll DAO
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.UpdateAccount(username, password, role);

                // refesh data grid
                if (result) {
                    url = (String) siteMaps.get(MyApplication.UpdateAccountServlet.SEARCH_RESULT_PAGE)
                            + "?txtSearchValue=" + searchValue;
                }
            }
        } catch (SQLException ex) {
            log("UpdateAccountServlet _SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateAccountServlet _Naming" + ex.getMessage());
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
