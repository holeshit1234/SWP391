/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.controller;


import dunghm.product.ProductDAO;
import dunghm.product.ProductDTO;
import dunghm.utils.MyApplication;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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
@WebServlet(name = "SearchProductManagerServlet", urlPatterns = {"/SearchProductManagerServlet"})
public class SearchProductManagerServlet extends HttpServlet {

    //private final String SEARCH_MANAGER="StoreManage.jsp";
    
    
    
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
        
       String searchValue = request.getParameter("txtSearchBookManager");
       
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        
        String url = (String)
                siteMaps.get(MyApplication.SearchProductManagerServlet.SEARCH_PRODUCT_RESULT_PAGE);
        try  {
            //1. call dao
            ProductDAO dao = new ProductDAO();
            dao.showProductManeger(searchValue);
            
            //2. call data grib
            List<ProductDTO> result = dao.getItemsList();
           
            //3. send to view
            request.setAttribute("SEARCH_RESULT", result);
            url = (String)
                siteMaps.get(MyApplication.SearchProductManagerServlet.SEARCH_PRODUCT_RESULT_PAGE);
            
            
        }catch(SQLException ex){
            log("SearchProductManagerServlet _SQL" + ex.getMessage());
        }catch(NamingException ex){
            log("SearchProductManagerServlet _Naming" + ex.getMessage());
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
