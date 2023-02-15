/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.Controller;

import DVHT.cart.CartObj;
import DVHT.utils.MyAplications;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;
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
@WebServlet(name = "DeleteItemsInCartServlet", urlPatterns = {"/DeleteItemsInCartServlet"})
public class DeleteItemsInCartServlet extends HttpServlet {

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
        Properties siteMaps = (Properties)context.getAttribute("SITEMAP");
        
        String url ="";
        try  {
           //1. go to cart place, check \
            System.out.println("Hi");
            HttpSession session = request.getSession(false);
            if(session!=null){
                //2. check cart, take cart
                System.out.println("Hi2");
                CartObj cart =  (CartObj)session.getAttribute("CART");
                if(cart!=null){
                    //3. take the items into cart
                    Map<String, Integer> items = cart.getItems();
                    System.out.println(items+"123");
                    if(items != null){
                        //4. customer take the selected item into cart
                        String[] SelectedItems = request.getParameterValues("chkItem");
                        if(SelectedItems != null){
                            for(String item : SelectedItems){
                                cart.removeFromCart(item);
                            }
                            //5. update cart
                            session.setAttribute("CART", cart);
                        }//selecteditems existed
                    }//items exited                    
                }//cart existed
            }//session existed           
            //6. refesh the view                              
        }finally{
            url = MyAplications.RemoveCartServlet.VIEW_CART;
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
