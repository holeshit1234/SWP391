/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.product.ProductDAO;
import DHTV.product.ProductDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

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

        String txtSearch = request.getParameter("txtSearch");
        //System.out.println("kt parameter "+txtSearch);
//        byte[] bytes1 = txtSearch.getBytes(StandardCharsets.ISO_8859_1);
//        txtSearch = new String(bytes1, StandardCharsets.UTF_8);
       
        String url = "indexSearch.jsp";
        try {
            
            //1. check valid search value --> search
            if (txtSearch.trim().length() > 0) {
                //2. call DAO
               ProductDAO dao =new ProductDAO();
               dao.searchProduct(txtSearch);
                //.out.println("Kt search đã add chưa"+dao);
                //3. process
                List<ProductDTO> Product = dao.getItemsList();
                //4. send to view
                //System.out.println("Kiemtra DAO đã get chưa "+dao.getItemsList());
                request.setAttribute("ITEMS_RESULT", Product);
                String json = new Gson().toJson(Product);

            HttpSession session = request.getSession();
            request.setAttribute("products", json);
                //   System.out.println("Kt search result"+Product);  

            }
            else{
                //call dao
            ProductDAO dao = new ProductDAO();

            dao.showProduct();
            // process
            List<ProductDTO> result = dao.getItemsList();
            // send to view
            request.setAttribute("ITEMS_RESULT", result);

//               ProductImgDAO dao2 = new ProductImgDAO();
//               System.out.println(dao2.getOneImgByProductID(1));              
            String json = new Gson().toJson(result);

            HttpSession session = request.getSession();
            request.setAttribute("products", json);

            }
        } catch (NamingException ex) {
//            ex.printStackTrace();
            log( ex.getMessage());
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
