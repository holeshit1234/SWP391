/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.ControllerAdmin;

import DHTV.brand.BrandDAO;
import DHTV.brand.BrandDTO;
import DHTV.category.CategoryDAO;
import DHTV.category.CategoryDTO;
import DVHT.userdetails.UserDetailsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
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
@WebServlet(name = "AddBrandServlet", urlPatterns = {"/AddBrandServlet"})
public class AddBrandServlet extends HttpServlet {

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

        String url = "showBrandServlet";
        try {
            if (session != null) {
                UserDetailsDTO dto1 = (UserDetailsDTO) session.getAttribute("USER");

                System.out.println(dto1.getRoleID());
                if (dto1 != null) {
                    if (dto1.getRoleID() == 1 || dto1.getRoleID() == 2) {
                        String BrandName = request.getParameter("txtBrand");
                        byte[] bytes1 = BrandName.getBytes(StandardCharsets.ISO_8859_1);
                        BrandName = new String(bytes1, StandardCharsets.UTF_8);

                        String txtStatus = request.getParameter("txtStatus");
                        boolean valid = true;
                        if (txtStatus.equals("1")) {
                            valid = true;
                        } else {
                            valid = false;
                        }
                        //boolean status = Boolean.getBoolean(txtStatus);

                        String Description = request.getParameter("txtDescription");
                        byte[] bytes2 = Description.getBytes(StandardCharsets.ISO_8859_1);
                        Description = new String(bytes2, StandardCharsets.UTF_8);
                        BrandDTO dto = new BrandDTO();
                        dto.setBrandName(BrandName);
                        dto.setStatus(valid);
                        dto.setDescription(Description);
                        BrandDAO dao = new BrandDAO();
                        dao.addBrandAdmin(dto);
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
