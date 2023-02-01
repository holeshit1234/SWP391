/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.controller;

import dunghm.cart.CartObj;
import dunghm.registration.RegistrationDTO;
import dunghm.utils.MyApplication;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

//    private final String SHOPPING_PAGE = "Store.html";
//    private final String SHOPPING_PAGE="Store.jsp";
//    private final String CAN_NOT_SHOPPING = "cannot.html";

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

        //String url = SHOPPING_PAGE;
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITE_MAP");

        
        String url ="";
        //1. get paramater
        String searchValue = request.getParameter("LastSearchValue");
        try {
            //1. tạo sessionscope/ go to cart place
            HttpSession session = request.getSession();

            RegistrationDTO dto = (RegistrationDTO) session.getAttribute("User");
            if (dto != null) {
                //2. cus take cart, if not have will create
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart == null) {
                    cart = new CartObj();
                }
                //3.cus take items/ get parameter
                String sku = request.getParameter("txtSku");
                int Quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                //4.add item to cart/ thêm vào giỏ update lại giỏ
                if (sku != null) {                   
                        cart.addToCart(sku, Quantity);
                        session.setAttribute("CART", cart);                                 
                }//sku exsitd and finish add items
                url = (String)siteMaps.get(MyApplication.AddToCartServlet.SEARCH_PRODUCT_USER)
                        + "?txtSearchBook=" + searchValue;

            }//da login
            else {
                url = (String) siteMaps.get(MyApplication.AddToCartServlet.CAN_NOT_SHOPPING);

            }
        } finally {

            //response.sendRedirect(url);
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
