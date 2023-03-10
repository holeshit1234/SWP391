/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.cart.CartDAO;
import DHTV.cart.CartDTO;
import DVHT.utils.DBHelpers;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/UpdateCartServlet"})
public class UpdateCartServlet extends HttpServlet {

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

        int cartId = Integer.parseInt(request.getParameter("cartId"));
        System.out.println(cartId);
        int productId = Integer.parseInt(request.getParameter("productId"));
        System.out.println(productId);
        String action = request.getParameter("action");
        System.out.println(action);

        try {

            // get the current quantity and price of the product in the cart from the database
            CartDAO cartDAO = new CartDAO();
            CartDTO cartDTO = cartDAO.getCartByProductId(cartId, productId);
            int quantity = cartDTO.getQuantity();
            double price = cartDTO.getPrice();
            // update the quantity and price based on the operation
            if ("inc".equals(action)) {
                int currentQuantity = cartDAO.getQuantityCartInStore(cartDTO);
                System.out.println(currentQuantity + " > " + quantity);
                if (currentQuantity > quantity) {
                    quantity++;
                    price += getProductPrice(productId);
                } else {
                    String message = "You have taken all the products in the store.";
                    if (!message.isEmpty()) {
                        request.setAttribute("FULL", message);
                    }
                }
            } else if ("des".equals(action)) {
                if (quantity > 1) {
                    quantity--;
                    price -= getProductPrice(productId);
                }
            }
            // update the cart in the database
            //CartDAO cartDAO = new CartDAO();
            cartDAO.updatecart(cartId, productId, quantity, price);

// calculate the total price of all items in the cart
            //double totalPrice = cartDAO.getTotalPrice(cartId);
// return the updated quantity, price, and total price as a JSON response
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("quantity", quantity);
            jsonObject.addProperty("price", price);
            //jsonObject.addProperty("totalPrice", totalPrice);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonObject.toString());

        } catch (SQLException ex) {
            log("Comment Servlet SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Comment Servlet Naming: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher("ViewCartServlet");
            rd.forward(request, response);
            //response.sendRedirect("ViewCartServlet");
        }
    }

    private double getProductPrice(int productId) throws SQLException, NamingException {
        double price = 0.0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "SELECT price FROM Product WHERE ProductID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    price = rs.getDouble("price");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return price;
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
