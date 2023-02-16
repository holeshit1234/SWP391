/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.address.AddressDTO;
import DVHT.cart.CartObj;
import DVHT.userdetails.UserDetailsDTO;
import DVHT.utils.MyAplications;
import static DVHT.utils.MyAplications.firstTimeServlet.LOGIN_PAGE;
import static DVHT.utils.MyAplications.showItems.STORE_SHOW_PAGE;
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
 * @author mthin
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

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        String url = (String) siteMaps.get(
                MyAplications.AddToCartServlet.SEARCH_STORE_SERVLET);
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                AddressDTO dto1 = (AddressDTO) session.getAttribute("USERE");
                UserDetailsDTO dto = (UserDetailsDTO) session.getAttribute("User");
                if (dto == null) {
                    //2. cus take cart, if not have will create
                    CartObj cart = (CartObj) session.getAttribute("CART");
                    if (cart == null) {
                        cart = new CartObj();
                    }
                    //3.cus take items/ get parameter
                    String sku = request.getParameter("txtProductName");
                    System.out.println(sku+"Hi");
                    int Quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                     System.out.println(Quantity+"Hi");
                    //4.add item to cart/ thêm vào giỏ update lại giỏ
                    if (sku != null) {
                        cart.addToCart(sku, Quantity);
                        session.setAttribute("CART", cart);

                    }//sku exsitd and finish add items
                    System.out.println("TC"+cart.getItems());
                    
                } else if (dto1 == null) {
                    CartObj cart = (CartObj) session.getAttribute("CART");
                    if (cart == null) {
                        cart = new CartObj();
                    }
                    //3.cus take items/ get parameter
                    String sku = request.getParameter("txtProductName");
                    int Quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                    //4.add item to cart/ thêm vào giỏ update lại giỏ
                    if (sku != null) {
                        cart.addToCart(sku, Quantity);
                        session.setAttribute("CART", cart);

                    }//sku exsitd and finish add items
                    System.out.println("TC"+cart.getItems());
                    
                }
            } else {
                url = LOGIN_PAGE;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
//response.sendRedirect(url);
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
