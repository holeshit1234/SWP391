/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.cart.CartDAO;
import DHTV.cart.CartDTO;
import DHTV.product.ProductDetailDAO;
import DVHT.userdetails.UserDetailsDTO;
import DVHT.utils.DBHelpers;
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
import javax.servlet.http.HttpSession;

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
        try {
            //get Para
            int productID = 0;
            String txtProductID = request.getParameter("txtProductID");
            if (txtProductID != null) {
                productID = Integer.parseInt(txtProductID);
            }
            int userID = 0;
            String txtUserID = request.getParameter("txtUserID");
            if (txtUserID != null) {
                userID = Integer.parseInt(txtUserID);
            }
            int storeID = 0;
            String txtStoreID = request.getParameter("txtStoreID");
            if (txtStoreID != null) {
                storeID = Integer.parseInt(txtStoreID);
            }
            int sizeID = 0;
            String txtSizeID = request.getParameter("txtSizeID");
            if (txtSizeID != null) {
                sizeID = Integer.parseInt(txtSizeID);
            }
            String pri = request.getParameter("txtPrice");
            double price = 0;
            if (pri != null) {
                price = Double.parseDouble(pri);
            }
            System.out.println(productID + " , " + userID + " , " + sizeID + " , " + price);
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            //------------------------------------------------------------------------------
            //checkLogin
            HttpSession session = request.getSession();
            UserDetailsDTO currentUserID = (UserDetailsDTO) session.getAttribute("USER");
            if (currentUserID == null) {
                
                session.setAttribute("productId", txtProductID);
                 
                url="login.jsp?txtProductID =" + txtProductID;
                
            } else {

                //check size not null
                if (txtSizeID == null) {
                    String message = "Please enter your size you want";
                    if (!message.isEmpty()) {
                        request.setAttribute("NULLSIZE", message);
                    }
                } else {
                    //Check quantity
                    ProductDetailDAO daoProDetail = new ProductDetailDAO();
                    int number = daoProDetail.getQuantity(productID, storeID, sizeID);
                    if (number > 0) {
                        CartDAO dao = new CartDAO();
                        // neeus cos ton tai thi +1 quantity
                        boolean existed = dao.cartExisted(productID, storeID, sizeID, userID);
                        if (existed) {
                            CartDTO dto = new CartDTO(0, productID, sizeID, storeID, userID, quantity, true, price);
                            quantity = dao.getQuantityCartInCart(productID, storeID, sizeID, userID);
                            dto.setQuantity(quantity);

                            //nếu còn hàng thì +1
                            int currentQuantity = dao.getQuantityCartInStore(dto); //check quantity trong data
                            System.out.println(currentQuantity + " > " + quantity);
                            if (currentQuantity > quantity) {
                                quantity++;
                                price += getProductPrice(productID);
                            }
                            dao.updatecartNoKey(sizeID, storeID, productID, userID, quantity, price);
                            String message = "Your product has been added to the cart.";
                            if (!message.isEmpty()) {
                                request.setAttribute("ADDTOCART", message);
                            }
                            System.out.println("ok ddax add to cart");

                        } else {
                            CartDTO dto = new CartDTO(0, productID, sizeID, storeID, userID, quantity, true, price);
                            boolean result = dao.saveCart(dto);
                            if (result) {
                                String message = "Your product has been added to the cart.";
                                if (!message.isEmpty()) {
                                    request.setAttribute("ADDTOCART", message);
                                }
                                System.out.println("ok ddax add to cart");
                            } else {
                                System.out.println("eos add to cart dc");
                            }
                        }
                    } else {
                        String message = "Item is currently out of stock.";
                        if (!message.isEmpty()) {
                            request.setAttribute("STOCK", message);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            log("Comment Servlet SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Comment Servlet Naming: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
