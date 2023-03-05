/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.product.ProductDAO;
import DHTV.product.ProductDTO;
import DHTV.product.ProductImgDAO;
import DHTV.product.ProductImgDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author User
 */
    @WebServlet(name = "ShowProduct", urlPatterns = {"/ShowProduct"})
public class ShowProduct extends HttpServlet {

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

        String url = "showproduct.jsp";
//        String indexPage = request.getParameter("index");
//        if (indexPage == null) {
//            indexPage = "1";
//        }
//        int index = Integer.parseInt(indexPage);
        try {
            ProductDAO dao = new ProductDAO();

            int size = dao.showProducts();
            // process
            List<ProductDTO> list = dao.getItemsList();
            // send to view
            //request.setAttribute("ITEMS_RESULT", result);
             request.setAttribute("PRODUCT_RESULT", list);
            
//            ProductDTO dto = new ProductDTO();
//             int id = dto.getProductID();
//             System.out.println(id);
            
//            
//            dao2.getImgByProductID(id);
            
//            List<ProductImgDTO> list2 = dao2.getImgList();
//            
//            String json2 = new Gson().toJson(list2);
            
            
            

//            int recordsPerPage = 5;
//            int endPage = 0;
//            endPage = size / recordsPerPage;
//            if (size % recordsPerPage != 0) {
//                endPage++;
//            }
//            
//            List<ProductDTO> pagingList = dao.pagingAccount(index, recordsPerPage);
//                request.setAttribute("PAGING_RESULT", pagingList);
//                request.setAttribute("END_PAGE", endPage);
//                request.setAttribute("CURRENT_PAGE", index);
//            
                
//              List<ProductDTO> list2 = dao.pagingAccount(index, recordsPerPage);
                String json = new Gson().toJson(list);
                
                HttpSession session = request.getSession();
                request.setAttribute("products", json);
       
                
        } catch (NumberFormatException ex) {
            log("NumberFormatException :" + ex.getMessage());
        } catch (NamingException ex) {
            log("ShowItemsServlet _ Naming _ " + ex.getMessage());
        } catch (SQLException ex) {
            log("ShowItemsServlet _ SQL _ " + ex.getMessage());
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
