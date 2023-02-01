/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.controller;

import dunghm.product.ProductDAO;
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
@WebServlet(name = "DeleteProductManagerServlet", urlPatterns = {"/DeleteProductManagerServlet"})
public class DeleteProductManagerServlet extends HttpServlet {

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
        
        
        String sku = request.getParameter("pk");
        String searchValue = request.getParameter("LastSearchValue");
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        
        String url = (String)
                siteMaps.get(MyApplication.DeleteProductManagerServlet.ERR_DELETE_PAGE);

        try {
            
            
            // 1. call DAo and call method
            ProductDAO dao = new ProductDAO();
            boolean result = dao.deleteProductManager(sku);
              
            //2. call search refesh data grid
            if(result){
            url = (String) siteMaps.get(MyApplication.DeleteProductManagerServlet.SEARCH_PRODUCT_RESULT_PAGE)
                    + "?txtSearchBookManager=" + searchValue;
            }
        } catch (SQLException ex) {
            log("DeleProductServlet _SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleProductServlet _Naming" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
//        response.sendRedirect(url);
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
