/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.controller;

import dunghm.cart.CartObj;
import dunghm.product.ProductDAO;
import dunghm.product.ProductDTO;
import dunghm.utils.MyApplication;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "ShowDetailServlet", urlPatterns = {"/ShowDetailServlet"})
public class ShowDetailServlet extends HttpServlet {

//    private final String INVALID_PAGE = "invalid.html";
//    private final String VIEW_CART = "checkOut.jsp";
    
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
        
        //1. servlet context
       ServletContext context = this.getServletContext();
        //2. get properties get sitemap
        Properties siteMaps= (Properties)context.getAttribute("SITE_MAP");
        
        HttpSession session = request.getSession();
        CartObj cart = (CartObj)session.getAttribute("CART");
        Map<String, Integer> items = cart.getItems();
        String url = (String)siteMaps.get(MyApplication.ShowDetailServlet.INVALID_PAGE);
        try {
            //1.Call DAO
            ProductDAO dao = new ProductDAO();
            //2. Get item in cart
            if (cart != null) {
                if(items !=null){                         
            for (String id : items.keySet()) {
                dao.getProductByID(id);  
            }
            //get list
            List<ProductDTO> list = dao.getGetProduct();
            url = (String)siteMaps.get(MyApplication.ShowDetailServlet.VIEW_CART);  
            
            session.setAttribute("CHECK_OUT", list);
                }//end has items
            }//end has cart
        } catch (NamingException ex) {
            log("Naming exception :" + ex.getMessage());
        } catch (SQLException ex) {
            log("SQL exception :" + ex.getMessage());
        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
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
