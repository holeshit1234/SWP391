/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.cart.CartDAO;
import DHTV.cart.CartDTO;
import DHTV.order.OrderDAO;
import DHTV.order.OrderDTO;
import DHTV.order.OrderDetailDAO;
import DHTV.order.OrderDetailDTO;
import DHTV.product.ProductDetailDAO;
import DVHT.userdetails.UserDetailsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
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
@WebServlet(name = "CodeProductServlet", urlPatterns = {"/CodeProductServlet"})
public class CodeProductServlet extends HttpServlet {

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
        String url = "CheckOutServlet";

        try {

            //get parameter
            int paymentID = 0;
            String txtPayment = request.getParameter("txtPayment");
            if (txtPayment != null) {
                paymentID = Integer.parseInt(txtPayment);
            }

            int addressID = 0;
            String txtAddressID = request.getParameter("txtAddressID");
            if (txtAddressID != null) {
                addressID = Integer.parseInt(txtAddressID);
            }

            // check errors
            if (paymentID == 0) {
                String message = "Please enter your payment method!";
                if (!message.isEmpty()) {
                    request.setAttribute("PAYMENT", message);
                }
            }
            if (addressID == 0) {
                String message = "Please enter your address!";
                if (!message.isEmpty()) {
                    request.setAttribute("ADDRESS", message);
                }
            }
            int userID = 0;
            if (paymentID != 0 && addressID != 0) { //đặt lock               

                //check quantity lần cuối
                HttpSession session = request.getSession();
                UserDetailsDTO user = (UserDetailsDTO) session.getAttribute("USER");
                if (user != null) {
                    userID = user.getUserID();
                }
                CartDAO daoCart = new CartDAO();
                daoCart.getCartByUserID(userID);
                List<CartDTO> list = daoCart.getCartList();
                boolean error = false;
                double totalPrice = 0;
                for (CartDTO dto : list) {
                    int productID = dto.getProductID();
                    int sizeID = dto.getSizeID();
                    int storeID = dto.getStoreID();
                    int quantityInCart = dto.getQuantity();
                    int quantityInData = daoCart.getQuantityCartInStore(dto);
                    if (quantityInCart > quantityInData) {
                        error = true;
                    }
                    totalPrice += dto.getPrice();
                }
                if (error) {
                    //thong bao looi
                    
                    
                    
                    
                    
                    //------------------------------------------------------
                } else {
                    //add data vào 2 bảng
                    //Order table
                    double shippingFee = 0;
                    OrderDTO order = new OrderDTO(0, userID, paymentID, addressID, null, totalPrice, shippingFee, false, false);
                    //System.out.println("---" + dto);
                    OrderDAO daoOrder = new OrderDAO();
                    int keyOrder = daoOrder.addOrder(order);
                    order.setOrderID(keyOrder);
                    //Order Detail Table
                    for (CartDTO cart : list) {
                        int orderID = keyOrder;
                        int productID = cart.getProductID();
                        int sizeID = cart.getSizeID();
                        int quantity = cart.getQuantity();
                        double price = cart.getPrice();
                        OrderDetailDTO orderDetailDTO = new OrderDetailDTO(0, orderID, productID, sizeID, quantity, price);
                        OrderDetailDAO daoOrderDetail = new OrderDetailDAO();
                        boolean addOrderDetail = daoOrderDetail.addOrder(orderDetailDTO);
                    }

                    
                    //update quantity in data
                    ProductDetailDAO daoProductDetail = new ProductDetailDAO();
                    for (CartDTO cart : list) {
                        int productID = cart.getProductID();
                        int storeID = cart.getStoreID();
                        int sizeID = cart.getSizeID();
                        int quantity = cart.getQuantity();
                        daoProductDetail.minusProduct(productID, storeID, sizeID, quantity);
                    }
                    //xoa cart                    
                    daoCart.deleteCartByUserID(userID);
                    
                    
                    url = "codeproduct.jsp";
                }

            }
        } catch (SQLException ex) {
            log("CodeProduct Servlet SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("CodeProduct Servlet Naming: " + ex.getMessage());
        } catch (ParseException ex) {
            log("CodeProduct Servlet Parse: " + ex.getMessage());
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
