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
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "GetChartDetailServlet", urlPatterns = {"/GetChartDetailServlet"})
public class GetChartDetailServlet extends HttpServlet {

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

        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String url = "dashBoard.jsp";
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                UserDetailsDTO dto1 = (UserDetailsDTO) session.getAttribute("USER");

                if (dto1 != null) {
                    if (dto1.getRoleID() == 1 || dto1.getRoleID() == 2) {
                        OrderDetailDAO dao = new OrderDetailDAO();

                        dao.getTop10ItemsInMonthYear(month, year);

                        List<OrderDetailDTO> top10Products = dao.getListdto();

                        request.setAttribute("top10Products", top10Products);
                        // Create a dataset
//            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//            for (OrderDetailDTO product : top10Products) {
//                dataset.addValue((Number) product.getQuantity(), product.getProductName(), "");
//            }
//            // Create a chart
//            JFreeChart chart = ChartFactory.createBarChart(
//                    "Top 10 Products",
//                    "Product Name",
//                    "Sales",
//                    dataset,
//                    PlotOrientation.VERTICAL,
//                    true,
//                    true,
//                    false);
//
//            // Customize the chart
//            chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 24));
//            chart.setBackgroundPaint(Color.WHITE);
//
//            // Generate a PNG image of the chart
//            byte[] chartImage = ChartUtils.encodeAsPNG(chart.createBufferedImage(800, 400));
//
//            // Store the chart image as a Base64-encoded string in a request attribute
//            String base64EncodedChart = Base64.getEncoder().encodeToString(chartImage);
//            request.setAttribute("base64EncodedChart", base64EncodedChart);

                        OrderDAO dao1 = new OrderDAO();

                        OrderDTO result1 = dao1.getTotalPriceAtYear(year);

                        if (result1 != null) {
//                request.setAttribute("date", result);
                            request.setAttribute("date1", result1);
                        }

                        dao1.getTotalPriceWithMonthByYear(year);
                        List<OrderDTO> totalPriceWithMonths = dao1.getListPriceMonths();

                        request.setAttribute("totalPriceWithMonths", totalPriceWithMonths);
                        // Create a dataset
//            DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
//            for (OrderDTO order : totalPriceWithMonths) {
//                dataset1.addValue(order.getTotalPrice(), "Total Price", order.getMonth());
//            }
//            // Create a chart
//            JFreeChart chart1 = ChartFactory.createBarChart(
//                    "Total Price With Month",
//                    "Month",
//                    "total",
//                    dataset1,
//                    PlotOrientation.VERTICAL,
//                    true,
//                    true,
//                    false);
//
//            // Customize the chart
//            chart1.getTitle().setFont(new Font("SansSerif", Font.BOLD, 18));
//            chart1.setBackgroundPaint(Color.WHITE);
//
//            // Generate a PNG image of the chart
//            byte[] chartImage1 = ChartUtils.encodeAsPNG(chart1.createBufferedImage(800, 400));
//
//            // Store the chart image as a Base64-encoded string in a request attribute
//            String base64EncodedChart1 = Base64.getEncoder().encodeToString(chartImage1);
//            request.setAttribute("base64EncodedChart2", base64EncodedChart1);
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
