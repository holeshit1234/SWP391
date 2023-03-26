/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.ControllerAdmin;

import DHTV.order.OrderDAO;
import DHTV.order.OrderDTO;
import DHTV.order.OrderDetailDAO;
import DHTV.order.OrderDetailDTO;
import DVHT.userdetails.UserDetailsDTO;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author User
 */
@WebServlet(name = "ShowDashBoard", urlPatterns = {"/ShowDashBoard"})
public class ShowDashBoard extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        String url = "";
        try {
            if (session != null) {
                UserDetailsDTO dto1 = (UserDetailsDTO) session.getAttribute("USER");
                System.out.println(dto1);
                if (dto1 != null) {
                    if (dto1.getRoleID() == 1 || dto1.getRoleID() == 2) {
                        OrderDetailDAO dao = new OrderDetailDAO();

                        dao.getTop10Products();

                        List<OrderDetailDTO> top10Products = dao.getListdto();

                        request.setAttribute("top10Products", top10Products);

                        OrderDAO dao1 = new OrderDAO();

//            BillDTO result = dao1.getTotalPriceAtMonth();
                        OrderDTO result1 = dao1.getTotalPriceAtYear();

                        if (result1 != null) {
//                request.setAttribute("date", result);

                            request.setAttribute("date1", result1);
                        }
//
                        dao1.getTotalPriceWithMonth();
                        List<OrderDTO> totalPriceWithMonths = dao1.getListPriceMonths();
                        request.setAttribute("totalPriceWithMonths", totalPriceWithMonths);
                         url = "dashBoard.jsp";
                    }
                }
            }else{
            url = "erorr.jsp";
            }
        } catch (NamingException ex) {
            log("Naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("SQL" + ex.getMessage());
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
