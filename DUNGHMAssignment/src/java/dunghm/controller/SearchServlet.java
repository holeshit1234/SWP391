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
import java.util.List;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    
//    private final String SEARCH_RESULT_PAGE="search.jsp";
//    private final String SEARCH_PAGE="search.html";
    
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
        
        
        //1. get contextservlet
        ServletContext context = this.getServletContext();
        //2.get propertites take sitemap
        Properties siteMaps = (Properties)context.getAttribute("SITE_MAP");
        
        // get parameter
        String searchValue = request.getParameter("txtSearchValue");
        String url = siteMaps.getProperty(MyApplication.SearchServlet.SEARCH_RESULT_PAGE);
        
        try  {
            //1. call dao
            RegistrationDAO dao = new RegistrationDAO();
            dao.searchLastname(searchValue);
            
            //2. call data grib
            List<RegistrationDTO> result = dao.getAccountLists();
            
            //3. send to view
            request.setAttribute("SEARCH_RESULT", result);
            url = siteMaps.getProperty(MyApplication.SearchServlet.SEARCH_RESULT_PAGE);
            
            
        }catch(SQLException ex){
            log("SearchServlet _SQL" + ex.getMessage());
        }catch(NamingException ex){
            log("SearchServlet _Naming" + ex.getMessage());
        }
        finally{
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
