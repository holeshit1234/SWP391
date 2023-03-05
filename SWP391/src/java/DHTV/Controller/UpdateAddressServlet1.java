/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.address.AddressDAO;
import DVHT.userdetails.UpdateUserDetailsErr;
import DVHT.userdetails.UserDetailsDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
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
@WebServlet(name = "UpdateAddressServlet", urlPatterns = {"/UpdateAddressServlet"})
public class UpdateAddressServlet1 extends HttpServlet {

    private final String UPDATE_SUCCESS = "ShowAddressServlet";
    private final String UPDATE_FAIL = "updateaddress.jsp";

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

        String street = request.getParameter("txtAddress");
        byte[] bytes1 = street.getBytes(StandardCharsets.ISO_8859_1);
        street = new String(bytes1, StandardCharsets.UTF_8);

        String addid = request.getParameter("txtaddressid");
        int id = Integer.parseInt(addid);
        
        String province = request.getParameter("txtProvince");
        byte[] bytes2 = province.getBytes(StandardCharsets.ISO_8859_1);
        province = new String(bytes2, StandardCharsets.UTF_8);

        String district = request.getParameter("txtDistrict");
        byte[] bytes3 = district.getBytes(StandardCharsets.ISO_8859_1);
        district = new String(bytes3, StandardCharsets.UTF_8);

        String ward = request.getParameter("txtWard");
        byte[] bytes4 = ward.getBytes(StandardCharsets.ISO_8859_1);
        ward = new String(bytes4, StandardCharsets.UTF_8);

        UpdateUserDetailsErr err = new UpdateUserDetailsErr();
        boolean flag = false;

        String url = "";
        HttpSession session = request.getSession(false);

        try {
            if (session != null) {
                UserDetailsDTO dto = (UserDetailsDTO) session.getAttribute("USER");
                System.out.println(dto);

                if (dto != null) {
                    int userid = dto.getUserID();

                    if (street.trim().length() < 1 || street.trim().length() > 30) {
                        flag = true;
                        err.setEmptyStreet("Street can not empty");
                    }
                    if (province.trim().length() < 1 || province.trim().length() > 30) {
                        flag = true;
                        err.setEmptyPronvince("Province can not empty");
                    }
                    if (district.trim().length() < 1 || district.trim().length() > 30) {
                        flag = true;
                        err.setEmptyDistrict("District can not empty");
                    }
                    if (ward.trim().length() < 1 || ward.trim().length() > 30) {
                        flag = true;
                        err.setEmptyWard("Ward can not empty");
                    }
                    if (flag) {
                        request.setAttribute("UP_ERROR", err);
                        url = UPDATE_FAIL;
                    } else {

                        AddressDAO dao = new AddressDAO();
                        
                        boolean result = dao.updateAddress(id,userid, street, province, district, ward);

                        if (result) {
                            url = UPDATE_SUCCESS;
                        }
                    }
                }
            }
        } catch (NamingException ex) {
            log("UpdateAddServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateAddServlet_SQL " + ex.getMessage());
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
