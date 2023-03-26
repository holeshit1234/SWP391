/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.ControllerAdmin;

import DHTV.order.OrderDAO;
import DVHT.userdetails.UserDetailsDTO;
import java.io.IOException;
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
 * @author mthin
 */
@WebServlet(name = "ChangeApprovalStatusServlet", urlPatterns = {"/ChangeApprovalStatusServlet"})
public class ChangeApprovalStatusServlet extends HttpServlet {

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
        String url = "";
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                UserDetailsDTO dto1 = (UserDetailsDTO) session.getAttribute("USER");

                if (dto1 != null) {
                    if (dto1.getRoleID() == 1) {
                        int orderID = Integer.parseInt(request.getParameter("txtOrderID"));
                        int ApprovalStatus = Integer.parseInt(request.getParameter("txtApprovalStatus"));
                        if (ApprovalStatus == 1) {
                            ApprovalStatus++;
                            OrderDAO dao = new OrderDAO();
                            dao.setApprovalStatusOrder(orderID, ApprovalStatus);
                            url = "showOrderConfirm";
                        } else if (ApprovalStatus == 2) {
                            ApprovalStatus++;
                            boolean paymentStatus = true;
                            OrderDAO dao = new OrderDAO();
                            dao.setApprovalAndPaymentStatusOrder(orderID, ApprovalStatus, paymentStatus);
                            url = "showOrder";
                        }

                        
                    }
                }
           }else{
            url = "erorr.jsp";
            }

        } catch (NamingException ex) {
            log("ChangeApprove _ Naming _ " + ex.getMessage());
        } catch (SQLException ex) {
            log("ChangeApprove _ SQL _ " + ex.getMessage());
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
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
