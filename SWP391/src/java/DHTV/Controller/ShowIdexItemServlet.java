/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.product.ProductDAO;
import DHTV.product.ProductDTO;
import DHTV.product.ProductImgDAO;
import DVHT.utils.MyAplications;
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
 * @author vinht
 */
@WebServlet(name = "ShowIdexItemServlet", urlPatterns = {"/ShowIdexItemServlet"})
public class ShowIdexItemServlet extends HttpServlet {

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
         //1.get servlet Context
        ServletContext context = this.getServletContext();
        //Get siteMaps from context Scope
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        String url = (String)siteMaps.get(MyAplications.ShowIdexItemServlet.INDEX_PAGE);
      
        try {
               //call dao
               ProductDAO dao= new ProductDAO();
               
               dao.showProduct();
               // process
               List<ProductDTO> result = dao.getItemsList();
               // send to view
               request.setAttribute("ITEMS_RESULT", result);  
               
               ProductImgDAO dao2 = new ProductImgDAO();
               System.out.println(dao2.getOneImgByProductID(1));
                
        }catch(NamingException ex) {
            log("ShowItemsServlet _ Naming _ " + ex.getMessage());
        } 
        catch (SQLException ex) {
            log("ShowItemsServlet _ SQL _ " + ex.getMessage());
        }  
        finally {    
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