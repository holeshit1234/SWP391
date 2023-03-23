/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.order.OrderDAO;
import DHTV.order.OrderDetailDAO;
import DHTV.order.OrderDetailDTO;
import DVHT.comment.CommentDAO;
import DVHT.comment.CommentDTO;
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
@WebServlet(name = "RatingOrderServlet", urlPatterns = {"/RatingOrderServlet"})
public class RatingOrderServlet extends HttpServlet {

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
        String url = "ordertracking.jsp";

        try {
            System.out.println("Start into Rating Servlet");
            //--------------------------------

            //get UserID
            int userID = 0;
            HttpSession session = request.getSession();
            UserDetailsDTO user = (UserDetailsDTO) session.getAttribute("USER");
            if (user != null) {
                userID = user.getUserID();
            }
            //get OrderID           
            String txtorderID = request.getParameter("orderID");
            int orderID = 0;
            if (txtorderID != null) {
                orderID = Integer.parseInt(txtorderID.trim());
            }
            if (userID > 0) {
                //process
                String rate = request.getParameter("rating");
                int point = 0;
                if (rate != null) {
                    point = Integer.parseInt(rate.trim());
                }
//                CommentDAO dao = new CommentDAO();
//                OrderDetailDAO daoOrderDetail = new OrderDetailDAO();
//                daoOrderDetail.showOrderDetailByOrderID(orderID);
//                List<OrderDetailDTO> list =daoOrderDetail.getOrderDetailList();
//                for (OrderDetailDTO dto:list){
//                    int productID = dto.getProductID();
//                    String des = null;                    
//                    CommentDTO cmt = new CommentDTO(0, userID, productID, null, des, point ,true );
//                    boolean result = dao.addComment(cmt);
//                }
                OrderDAO daoOrder = new OrderDAO();
               
                boolean result = daoOrder.updateOrderPoint(orderID, point);
                if(!result){
                    System.out.println("Rating order lỗi cmnr");
                }
            } else {
                String message = "Please sign in again!";
                if (!message.isEmpty()) {
                    request.setAttribute("SIGN_IN", message);
                }
            }

        } catch (SQLException ex) {
            log("Rating Servlet SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Rating Servlet Naming: " + ex.getMessage());
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
