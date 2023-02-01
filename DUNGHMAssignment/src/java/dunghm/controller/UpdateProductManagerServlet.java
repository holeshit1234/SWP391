/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.controller;

import dunghm.product.ProductDAO;
import dunghm.product.ProductDTO;
import dunghm.product.ProductUpdateErr;
import dunghm.utils.MyApplication;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dunghm
 */
@WebServlet(name = "UpdateProductManagerServlet", urlPatterns = {"/UpdateProductManagerServlet"})
public class UpdateProductManagerServlet extends HttpServlet {

//    private final String UPDATE_ERR="update_err.html";
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

        //get parameter
        String searchValue = request.getParameter("LastSearchValue");
        String sku = request.getParameter("txtSku");
        //int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
        String quantity = request.getParameter("txtQuantity");
        //float price = Float.parseFloat(request.getParameter("txtPrice"));
        String price = request.getParameter("txtPrice");
        String statuscheck = request.getParameter("chkStatus");
        boolean status = false;
        if (statuscheck != null) {
            status = true;
        }
        //context servlet
        ServletContext context = this.getServletContext();
        //properties site map
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");

        String url = (String) siteMaps.get(MyApplication.UpdateProductManagerServlet.ERR_UPDATE_PAGE);

        ProductUpdateErr err = new ProductUpdateErr();
        boolean flag = false;
        try {
            //check user err
//            if(quantity<0){
//                flag = true;
//                err.setNegativeQuantityErr("quantity is positive number");
//            }
//            if(price<0){
//                flag = true;
//                err.setNoPriceErr("price is positive number");
//            }
            if (price.matches("[a-zA-Z]") || quantity.matches("[a-zA-Z]")) {
                flag = true;
                err.setWrongFormatErr("It needed a number");
                if (flag) {
                    request.setAttribute("ERROR_UPDATE_PRODUCT", err);
                    url = (String) siteMaps.get(MyApplication.UpdateProductManagerServlet.SEARCH_PRODUCT_RESULT_PAGE)
                            + "?txtSearchBookManager=" + searchValue;
                }
            }
            if (!price.equals("") && !quantity.equals("")) {
                int quan = Integer.parseInt(quantity);
                float pri = Float.parseFloat(price);
                if (quan < 0 || pri < 0) {
                    flag = true;
                    err.setNegativeErr("Need is a positive number");
                }

                if (flag) {
                    // luu bien loi
                    request.setAttribute("ERROR_UPDATE_PRODUCT", err);
                    url = (String) siteMaps.get(MyApplication.UpdateProductManagerServlet.SEARCH_PRODUCT_RESULT_PAGE)
                            + "?txtSearchBookManager=" + searchValue;
                } else {
                    //1.Call DAO
                    ProductDAO dao = new ProductDAO();
                    boolean result = dao.updateProductManager(sku, quan, pri, status);

                    //2 refesh data grid
                    if (result) {
                        url = (String) siteMaps.get(MyApplication.UpdateProductManagerServlet.SEARCH_PRODUCT_RESULT_PAGE)
                                + "?txtSearchBookManager=" + searchValue;
                    }
                }
            }
        } catch (SQLException ex) {
            log("UpdateProductServlet _SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateProductServlet _Naming " + ex.getMessage());
        } catch (NumberFormatException ex) {
//            String msg = ex.getMessage();
            log("UpdateProductServlet _NumFormat " + ex.getMessage());
//            if(msg.contains("For input string")){
//                err.setWrongFormatQuantityErr("Quantity is a number");
//                request.setAttribute("ERROR_UPDATE_PRODUCT", err);
//            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            //response.sendRedirect(url);
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
