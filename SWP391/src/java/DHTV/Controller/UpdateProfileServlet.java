/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.address.AddressDAO;
import DHTV.address.AddressDTO;
import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
import DVHT.utils.MyAplications;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/UpdateProfileServlet"})
public class UpdateProfileServlet extends HttpServlet {

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

        String fullName = request.getParameter("txtFullName");
        String email = request.getParameter("txtEmail");
        String phone = request.getParameter("txtPhone");
        String street = request.getParameter("txtStreet");
        String province = request.getParameter("txtProvince");
        String district = request.getParameter("txtDistrict");
        String ward = request.getParameter("txtWard");

        //1.get servlet Context
        ServletContext context = this.getServletContext();
        //2. get siteMap
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        String url = "ShowProfileServlet";
        HttpSession session = request.getSession(false);

        try {
            if (session != null) {
                UserDetailsDTO dto = (UserDetailsDTO) session.getAttribute("User");
                if (dto != null) {
                    int userid = dto.getUserID();

                    UserDetailsDAO dao = new UserDetailsDAO();
                    boolean result = dao.updateProfile(userid, email, fullName, phone);

                    //AddressDTO adto = (AddressDTO) session.getAttribute("INFO");
                    //if (adto != null) {
                       // int addressid = adto.getAddressID();
                        AddressDAO dao2 = new AddressDAO();
                        //boolean result2 = dao2.updateAddress(userid, street, province, district, ward, addressid);
                        boolean result2 = dao2.updateAddress(userid, street, province, district, ward);
                        //refesh data grid
                        if (result && result2) {
                            //url = (String) siteMaps.get(MyAplications.UpdateProfileServlet.UPDATE_PAGE);
                            url ="ShowProfileServlet";
                        }
                    }

//                }
            }
        } catch (NamingException ex) {
            log("UpdateProfileServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateProfileServlet_SQL " + ex.getMessage());
        } finally {
            System.out.println("URL = " + url);
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
