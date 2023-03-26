/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.order.OrderDAO;
import DHTV.order.OrderDTO;
import DHTV.order.OrderDetailDAO;
import DHTV.order.OrderDetailDTO;
import DHTV.product.ProductDetailDAO;
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

/**
 *
 * @author vinht
 */
@WebServlet(name = "CancelOrderServlet", urlPatterns = {"/CancelOrderServlet"})
public class CancelOrderServlet extends HttpServlet {

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

        String txtorderID = request.getParameter("txtOrderID");
        int orderID = 0;
        if (txtorderID != null) {
            orderID = Integer.parseInt(txtorderID.trim());
        }

        String url = "ordertracking.jsp";
        String button = request.getParameter("btAction");
        OrderDAO daoOrder = new OrderDAO();
        try {
            //get back quantity if this order chưa xác nhận
            OrderDTO dtoOrder = daoOrder.getOrderByOrderID(orderID);
            if(dtoOrder.getApprovalStatus()==1){ //check approval
                
                //get order detail list
                OrderDetailDAO daoOrderDetail = new OrderDetailDAO();
                daoOrderDetail.showListOrderDetail(orderID);
                List<OrderDetailDTO> list = daoOrderDetail.getOrderDetailList();
                //update quantity for each product
                ProductDetailDAO daoPro = new ProductDetailDAO();
                for(OrderDetailDTO dto : list){
                    daoPro.plusProduct(dto.getProductID(), 1, dto.getSizeID(), dto.getQuantity());
                }
            }
            
            //set cancel this order            
            boolean result = daoOrder.setApprovalStatusOrder(orderID, 4);            
            
        } catch (SQLException ex) {
            log("CancelOrderServlet SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Servlet Naming: " + ex.getMessage());
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
