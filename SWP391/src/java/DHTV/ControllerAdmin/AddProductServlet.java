/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.ControllerAdmin;

import DHTV.brand.BrandDAO;
import DHTV.brand.BrandDTO;
import DHTV.cart.CartDAO;
import DHTV.cart.CartDTO;
import DHTV.product.ProductDAO;
import DHTV.product.ProductDTO;
import DHTV.product.ProductDetailDAO;
import DHTV.product.ProductDetailDTO;
import DVHT.userdetails.UserDetailsDTO;
import DVHT.utils.DBHelpers;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.ProcessBuilder.Redirect.to;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import static org.bouncycastle.asn1.iana.IANAObjectIdentifiers.directory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mthin
 */
@MultipartConfig
@WebServlet(name = "AddProductServlet", urlPatterns = {"/AddProductServlet"})
public class AddProductServlet extends HttpServlet {

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
        String url = "ShowAllListProductServlet";
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                UserDetailsDTO dto1 = (UserDetailsDTO) session.getAttribute("USER");

                if (dto1 != null) {
                    if (dto1.getRoleID() == 1 || dto1.getRoleID() == 2) {
                        String ProductName = request.getParameter("txtProductName");//1
                        byte[] bytes1 = ProductName.getBytes(StandardCharsets.ISO_8859_1);
                        ProductName = new String(bytes1, StandardCharsets.UTF_8);
                        String Description = request.getParameter("txtDescription");//2
                        byte[] bytes2 = Description.getBytes(StandardCharsets.ISO_8859_1);
                        Description = new String(bytes2, StandardCharsets.UTF_8);
                        //3
                        Part part = request.getPart("txtImage");
                        String Image = part.getSubmittedFileName();

                        String txtBrand = request.getParameter("txtBrand");//4
                        int brandID = Integer.parseInt(txtBrand);
                        String txtCate = request.getParameter("txtCate");//5
                        int cateID = Integer.parseInt(txtCate);

                        int status = 1; //9

                        String txtPrice = request.getParameter("txtPrice");//6
                        float price = Float.parseFloat(txtPrice);
                        int key = 0;
                        //set 1-2-3-4-5-6 vào Product
                        ProductDTO dto = new ProductDTO();
                        dto.setProductName(ProductName);
                        dto.setBrandID(brandID);
                        dto.setDescription(Description);
                        dto.setPrice(price);
                        dto.setImage(Image);
                        dto.setStatus(true);
                        dto.setCategoryID(cateID);
                        ProductDAO dao = new ProductDAO();

                        key = dao.addProductAdmin(dto);
                        String directory = "D:/FPT/SWP/Moi/SWP391/SWP391/web/asset/images/productpictures"; // Change this to the directory of your choice

                        // Create the directory if it doesn't exist
                        File dir = new File(directory);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }

                        // Save the uploaded image to the specified directory
                        String path = directory + File.separator + dto.getImage();
                        DBHelpers.deleteFile(path);

                        DBHelpers.saveFile(part.getInputStream(), path);
                        System.out.println("Ket" + key);
                        //Get id vừa add vô sp
                        //ProductDTO getIdDTO = new ProductDTO();
                        ProductDAO getIdDAO = new ProductDAO();
                        getIdDAO.getProductIdByInfo(dto);
                        int getID = getIdDAO.getProductIdByInfo(dto).getProductID();
                        System.out.println("CheckID sp mới " + getID);
//            //set 1-7-8-9 vào productdetail

                        String[] Quantity = request.getParameterValues("txtQuantity");
                        String[] Size = request.getParameterValues("txtSize");

                        int storeID = 1;
                        if (key != 0) {
                            for (int i = 0; i <= Size.length; i++) {
                                int size = Integer.parseInt(Size[i]);
                                int quantity = Integer.parseInt(Quantity[i]);
                                ProductDetailDTO productDetailDTO = new ProductDetailDTO();

                                productDetailDTO.setProductID(getID);
                                productDetailDTO.setQuantity(quantity);
                                productDetailDTO.setSizeID(size);
                                productDetailDTO.setStoreID(storeID);

                                ProductDetailDAO productDetailDAO = new ProductDetailDAO();
                                productDetailDAO.addProductDetailAdmin(key, size, quantity, storeID);
                                url = "ShowAllListProductServlet";
                                System.out.println("-----------------------------");
                            }
                        }
                    }
                }
            }else{
            url = "erorr.jsp";
            }
        } catch (NamingException ex) {
            log(" _ Naming _ " + ex.getMessage());
        } catch (SQLException ex) {
            log(" _ SQL _ " + ex.getMessage());
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
