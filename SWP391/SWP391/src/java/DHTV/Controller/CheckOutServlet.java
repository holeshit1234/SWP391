/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.cart.CartDAO;
import DHTV.cart.CartDTO;
import DVHT.userdetails.UserDetailsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

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
        String url = "ViewCartServlet";

        double sum = 0;
        int quantity = 0;
        try {
            int userID = 0;
            HttpSession session = request.getSession();
            UserDetailsDTO user = (UserDetailsDTO) session.getAttribute("USER");
            if (user != null) {
                userID = user.getUserID();
            }
            //check cart rỗng
            CartDAO daoCart = new CartDAO();
            boolean notEmptyCart = daoCart.cartUserExisted(userID);
            if (notEmptyCart) {
                url = "checkout.jsp";

                if (user != null) {

                    CartDAO dao = new CartDAO();
                    dao.getCartByUserID(user.getUserID());
                    List<CartDTO> cartList = dao.getCartList();
                    request.setAttribute("CART_RESULT", cartList);

                    sum = dao.getTotalCartPrice(user.getUserID());
                    request.setAttribute("TOTAL_PRICE", sum);

                    quantity = dao.getQuantityProductCart(user.getUserID());
                    request.setAttribute("QUANTITIES", quantity);

                } else {
                    url = "ShowIdexItemServlet";
                }
            } else {
                String message = "Your cart is empty!";
                if (!message.isEmpty()) {
                    request.setAttribute("EMPTYCART", message);
                }
            }
            request.setAttribute("USERID", userID);
        } catch (SQLException ex) {
            log("Checkout Servlet SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Checkout Servlet Naming: " + ex.getMessage());
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
