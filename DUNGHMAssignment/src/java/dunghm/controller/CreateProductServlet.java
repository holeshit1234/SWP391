/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.controller;

import dunghm.product.ProductDAO;
import dunghm.product.ProductDTO;
import dunghm.product.ProductErr;
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
@WebServlet(name = "CreateProductServlet", urlPatterns = {"/CreateProductServlet"})
public class CreateProductServlet extends HttpServlet {

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
        String sku = request.getParameter("txtSku");
        String name = request.getParameter("txtName");
        String description = request.getParameter("txtDescription");
        int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
        //String quantity = request.getParameter("txtQuantity");
        float price = Float.parseFloat(request.getParameter("txtPrice"));
        //String price = request.getParameter("txtPrice");

        //context servlet
        ServletContext context = this.getServletContext();
        //get sitemap
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");

        //call object err
        ProductErr errors = new ProductErr();
        boolean errorfound = false;
        String url = siteMaps.getProperty(MyApplication.createProductServlet.ERR_PAGE_PRODUCT);

        try {
            //1. check user error
            if (sku.trim().length() < 1 || sku.trim().length() > 10) {
                errorfound = true;
                errors.setSkuLengthErr("Sku need into 1 to 10");

            }
            if (name.trim().length() < 1 || name.trim().length() > 50) {
                errorfound = true;
                errors.setNameLengthErr("Name need into 1 to 50");

            }

            if (description.trim().length() < 1 || description.trim().length() > 50) {
                errorfound = true;
                errors.setDescriptionLengthErr("Description need into 1 to 50");
            }
             if(quantity<0){
                errorfound = true;
                errors.setNegativeQuantityErr("quantity is positive number");
            }
            if(price<0){
                errorfound = true;
                errors.setNegativePriceErr("price is positive number");
            }


            if (errorfound) {
                //luu vao bien loi
                request.setAttribute("ERRORS", errors);
            }

                     else {
                        //insert to database
                        // call dao
                        ProductDAO dao = new ProductDAO();
                        ProductDTO dto = new ProductDTO(sku, name, description, quantity, price, false);
                        boolean result = dao.createProductManager(dto);
                        if (result) {
                            url
                                    = siteMaps.getProperty(MyApplication.createProductServlet.CREATE_SUCCESS);
                        }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("createProductServlet _SQL " + ex.getMessage());
            if (msg.contains("duplicate")) {
                errors.setSkuIsExisted(sku + " is existed");
                request.setAttribute("ERRORS", errors);
            }
        } catch (NamingException ex) {
            log("createProductServlet _Naming " + ex.getMessage());
        } catch (NumberFormatException ex) {
            // String mss = ex.getMessage();
            log("createProductServlet _NumberFormat " + ex.getMessage());
//            if(mss.contains("For input string")){
//                errors.setWrongFormatErr("need number");
//                        request.setAttribute("ERRORS", errors);
//            }
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
