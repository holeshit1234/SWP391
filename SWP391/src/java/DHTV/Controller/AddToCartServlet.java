/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.cart.CartDAO;
import DHTV.cart.CartDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author vinht
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

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
        String url = "CommentServlet";
        try{
            int productID = 0;
            String txtProductID = request.getParameter("txtProductID");
            if(txtProductID != null ) productID = Integer.parseInt(txtProductID);
            int userID = 0;
            String txtUserID = request.getParameter("txtUserID");
            if(txtUserID != null ) userID = Integer.parseInt(txtUserID);
            int storeID = 0;
            String txtStoreID = request.getParameter("txtStoreID");
            if(txtStoreID != null ) storeID = Integer.parseInt(txtStoreID);            
            int sizeID = 0;
            String txtSizeID = request.getParameter("txtSizeID");
            if(txtSizeID != null ) sizeID = Integer.parseInt(txtSizeID);            
            System.out.println(productID + " , " + userID + " , " + sizeID);
            int quantity = 1;
            CartDTO dto = new CartDTO(0, productID, sizeID, storeID, userID, quantity, true);
            CartDAO dao = new CartDAO();
            boolean result = dao.saveCart(dto);
            if(result){
                System.out.println("ok ddax add to cart");
            }
            else{
                System.out.println("eos add to cart dc");
            }
        }
         catch (SQLException ex) {
            log("Comment Servlet SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Comment Servlet Naming: " + ex.getMessage());
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
