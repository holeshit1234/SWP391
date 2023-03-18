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
import DVHT.bill.BillDAO;
import DVHT.bill.BillDTO;
import DVHT.bill.BillDetailDAO;
import DVHT.bill.BillDetailDTO;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

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
        try {
            BillDetailDAO dao = new BillDetailDAO();

            dao.getTop10Products();

            List<BillDetailDTO> top10Products = dao.getListdto();
            // Create a dataset
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (BillDetailDTO product : top10Products) {
                dataset.addValue((Number) product.getQuantity(), product.getProductName(), "");
            }
            // Create a chart
            JFreeChart chart = ChartFactory.createBarChart(
                    "Top 10 Products",
                    "Product Name",
                    "Sales",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false);

            // Customize the chart
            chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 18));
            chart.setBackgroundPaint(Color.WHITE);

            // Generate a PNG image of the chart
            byte[] chartImage = ChartUtils.encodeAsPNG(chart.createBufferedImage(800, 400));

            // Store the chart image as a Base64-encoded string in a request attribute
            String base64EncodedChart = Base64.getEncoder().encodeToString(chartImage);
            request.setAttribute("base64EncodedChart", base64EncodedChart);
            
            
            
            
            BillDAO dao1 = new BillDAO();
            dao1.getTotalPriceWithMonth();
            List<BillDTO> totalPriceWithMonths = dao1.getListPriceMonths();
            // Create a dataset
            DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
            for (BillDTO order : totalPriceWithMonths) {
                dataset1.addValue(order.getTotalPrice(),"Total Price", order.getMonth());
            }
            // Create a chart
            JFreeChart chart1 = ChartFactory.createBarChart(
                    "Total Price With Month",
                    "Month",
                    "total",
                    dataset1,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false);

            // Customize the chart
            chart1.getTitle().setFont(new Font("SansSerif", Font.BOLD, 18));
            chart1.setBackgroundPaint(Color.WHITE);

            // Generate a PNG image of the chart
            byte[] chartImage1 = ChartUtils.encodeAsPNG(chart1.createBufferedImage(800, 400));

            // Store the chart image as a Base64-encoded string in a request attribute
            String base64EncodedChart1 = Base64.getEncoder().encodeToString(chartImage1);
            request.setAttribute("base64EncodedChart2", base64EncodedChart1);


        } catch (NamingException ex) {
            log("Naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("SQL" + ex.getMessage());
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashBoard.jsp");
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
