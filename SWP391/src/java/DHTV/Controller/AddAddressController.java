/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.address.AddressDAO;
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
 * @author vinht
 */
@WebServlet(name = "AddAddressController", urlPatterns = {"/AddAddressController"})
public class AddAddressController extends HttpServlet {

    private final String REFESH_PAGE = "ShowAddressServlet";
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
        
        
        String button = request.getParameter("btAction");
        String userid = request.getParameter("txtuserid");
        int id = Integer.parseInt(userid); 
        
        String province = request.getParameter("txtProvinceDataName");
        byte[] bytes1 = province.getBytes(StandardCharsets.ISO_8859_1);
        province = new String(bytes1, StandardCharsets.UTF_8);
        
        String district = request.getParameter("txtDistrictDataName");
        byte[] bytes2 = district.getBytes(StandardCharsets.ISO_8859_1);
        district = new String(bytes2, StandardCharsets.UTF_8);
        
        
        String ward = request.getParameter("txtWardDataName");
        byte[] bytes3 = ward.getBytes(StandardCharsets.ISO_8859_1);
        ward = new String(bytes3, StandardCharsets.UTF_8);
        
        String street = request.getParameter("txtAddress");
        byte[] bytes4 = street.getBytes(StandardCharsets.ISO_8859_1);
        street = new String(bytes4, StandardCharsets.UTF_8);
        
        String checkout = request.getParameter("checkout");
        
        HttpSession session = request.getSession(false);
        UserDetailsDTO dto = new UserDetailsDTO();

        dto.setUserID(id);
        
        String url = "";
        try  {
            if(button.equals("Add Address")){
                AddressDAO dao = new AddressDAO();
                
                boolean result = dao.addNewAddress(id, province, ward, street, district, true);
                
                if(result){
                    url =REFESH_PAGE;
                    if(checkout !=null) url = "CheckOutServlet";
                }
            }
            
        }catch (NamingException ex) {
            log("AddAddressServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("AddAddressServlet_SQL " + ex.getMessage());
        }finally{
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
