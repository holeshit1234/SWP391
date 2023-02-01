/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.controller;

import dunghm.cart.CartObj;
import dunghm.order.OrderDAO;
import dunghm.orderdetails.OrderDetailsDAO;
import dunghm.registration.RegistrationDTO;
import dunghm.utils.MyApplication;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.Servlet;
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
@WebServlet(name = "PayServlet", urlPatterns = {"/PayServlet"})
public class PayServlet extends HttpServlet {

    //private final String VIEW="CheckOut.jsp";
    
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

        String total = request.getParameter("txtTotal");
        HttpSession session = request.getSession(false);
        int key = 0;
         
         
         ServletContext context = this.getServletContext();
         Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
         String url ="";  
         
        try {
            if (session != null) {
                RegistrationDTO dto = (RegistrationDTO) session.getAttribute("User");
                if (dto != null) {
                    String username = dto.getUsername();
                    //call DAO
                    OrderDAO dao1 = new OrderDAO();                   
                    key = dao1.addToOrders(username, total);
                    if (key != 0) {
                        CartObj cart = (CartObj) session.getAttribute("CART");
                        if (cart != null) {
                            OrderDetailsDAO dao2 = new OrderDetailsDAO();
                            dao2.addToDetailOrders(cart, key);
                            url=(String) siteMaps.get(MyApplication.PayServlet.VIEW_CHECKOUT);
                            
                        }
                    }//existed cart
                }//existed username
            }//has session
            session.removeAttribute("CHECK_OUT");
            session.removeAttribute("CART");

        } catch (NullPointerException ex) {
            log("PayServlet _null " + ex.getMessage());
        } catch (SQLException ex) {
            log("PayServlet _SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("PayServlet _Naming" + ex.getMessage());
        } finally {
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
