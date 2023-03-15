/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.product.ProductDAO;
import DHTV.product.ProductDTO;
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
 * @author mthin
 */
@WebServlet(name = "ShowProuductByBrandID", urlPatterns = {"/ShowProuductByBrandID"})
public class ShowProuductByBrandID extends HttpServlet {

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
        String url = "index_ProductBrand.jsp";

        String indexPage = request.getParameter("index");

        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        try {
            int brandID = Integer.parseInt(request.getParameter("brandID"));
            ProductDAO dao = new ProductDAO();

            int size = dao.getTotalProductByBrand(brandID);
            System.out.println(size);
            //paging 
            int recordsPerPage = 12;
            int endPage = 0;
            endPage = size / recordsPerPage;
            if (size % recordsPerPage != 0) {
                endPage++;
            }
            System.out.println(endPage);

//                List<ProductDTO> paging = dao.pagingProductNam();
            List<ProductDTO> paging = dao.pagingProductByBrand(brandID ,index, recordsPerPage);

            System.out.println(paging);

            request.setAttribute("PRODUCT_BRAND_RESULT", paging);
            request.setAttribute("END_PAGE", endPage);
            request.setAttribute("CURRENT_PAGE", index);

        } catch (NamingException ex) {
//            ex.printStackTrace();
            log(ex.getMessage());
        } catch (SQLException ex) {
//            ex.printStackTrace();
            log(ex.getMessage());
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
