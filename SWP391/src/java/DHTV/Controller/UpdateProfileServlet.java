/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.address.AddressDAO;
import DHTV.address.AddressDTO;
import DVHT.userdetails.UpdateUserDetailsErr;
import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/UpdateProfileServlet"})
public class UpdateProfileServlet extends HttpServlet {
    
    
    private final String UPDATE_PAGE = "ShowProfileServlet";
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
        byte[] bytes = fullName.getBytes(StandardCharsets.ISO_8859_1);
        fullName = new String(bytes, StandardCharsets.UTF_8);

        String DOB = request.getParameter("txtDOB");
        //Date dob = Date.valueOf(DOB);
        //String email = request.getParameter("txtEmail");
        String phone = request.getParameter("txtPhone");

        
        String password = request.getParameter("txtPassWord");

        String url ="";
        HttpSession session = request.getSession(false);

        UpdateUserDetailsErr err = new UpdateUserDetailsErr();
        boolean flag = false;

        try {
            if (session != null) {
////                AddressDTO dto1 = (AddressDTO) session.getAttribute("USERE");
////                System.out.println(dto1);

                UserDetailsDTO dto = (UserDetailsDTO) session.getAttribute("USER");
                System.out.println(dto);

//                if(dto == null){
//                    AddressDTO dto1 = (AddressDTO) session.getAttribute("USER");
//                
                if (dto != null) {
                    int userid = dto.getUserID();
                    if(password.trim().length() < 8 || password.trim().length() >20 ){
                        flag = true;
                        err.setNotEnoughWordPassWord("PassWord about 10 to 20");
                    }
                    if (fullName.trim().length() < 1 || fullName.trim().length() > 30) {
                        flag = true;
                        err.setNotEnoughWordFullName("FullName about 5 to 20");
                    }
                   
                    if (phone.trim().length() > 11 || phone.trim().length() < 9) {
                        flag = true;
                        err.setEmptyPhone("phone has 10 number");
                    }
                    
                    if (flag) {
                        request.setAttribute("UP_ERROR", err);
                        url = UPDATE_PAGE;
                    } else {
                        Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(DOB);

                        UserDetailsDAO dao = new UserDetailsDAO();
                        boolean result = dao.updateProfile(userid, fullName, phone, dob, password);

                        //AddressDTO adto = (AddressDTO) session.getAttribute("INFO");
                        //if (adto != null) {
                        // int addressid = adto.getAddressID();
                        
                        //refesh data grid

                        if (result) {
                            url = UPDATE_PAGE;
                        }
                    }
                }
//                } else if (dto1 != null) {
//                    int userid = dto1.getUserID();
////                    if(password.trim().length() < 1 || password.trim().length() >20 ){
////                        flag = true;
////                        err.setNotEnoughWordPassWord("PassWord about 10 to 20");
////                    }
//                    if (fullName.trim().length() < 1 || fullName.trim().length() > 30) {
//                        flag = true;
//                        err.setNotEnoughWordFullName("FullName about 5 to 20");
//                    }
//                    if (DOB.trim().length() < 1 || DOB.trim().length() > 20) {
//                        flag = true;
//                        err.setEmptyDOB("Form of DOB is yyyy-mm-dd");
//                    }
//                    if (phone.trim().length() > 11 || phone.trim().length() < 9) {
//                        flag = true;
//                        err.setEmptyDOB("phone has 10 number");
//                    }
//                    if (street.trim().length() < 1 || street.trim().length() > 30) {
//                        flag = true;
//                        err.setEmptyStreet("Street can not empty");
//                    }
//                    if (province.trim().length() < 1 || phone.trim().length() > 30) {
//                        flag = true;
//                        err.setEmptyPronvince("Province can not empty");
//                    }
//                    if (district.trim().length() < 1 || district.trim().length() > 30) {
//                        flag = true;
//                        err.setEmptyDistrict("District can not empty");
//                    }
//                    if (ward.trim().length() < 1 || ward.trim().length() > 30) {
//                        flag = true;
//                        err.setEmptyWard("Ward can not empty");
//                    }
//                    if (flag) {
//                        request.setAttribute("UP_ERROR", err);
//                        url = (String) siteMaps.get(MyAplications.UpdateProfileServlet.UPDATE_PAGE);
//                    } else {
//                        Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(DOB);
//
//                        UserDetailsDAO dao = new UserDetailsDAO();
//                        boolean result = dao.updateProfile(userid, email, fullName, phone, dob);
//
//                        //AddressDTO adto = (AddressDTO) session.getAttribute("INFO");
//                        //if (adto != null) {
//                        // int addressid = adto.getAddressID();
//                        AddressDAO dao2 = new AddressDAO();
//                        //boolean result2 = dao2.updateAddress(userid, street, province, district, ward, addressid);
//                        boolean result2 = dao2.updateAddress(userid, street, province, district, ward);
//                        //refesh data grid
//
//                        if (result || result2) {
//                            url = (String) siteMaps.get(MyAplications.UpdateProfileServlet.UPDATE_PAGE);
//                        }
//                    }
//                }
//                }
//                }
            }
        } catch (NamingException ex) {
            log("UpdateProfileServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateProfileServlet_SQL " + ex.getMessage());
        } catch (ParseException ex) {
            log("UpdateProfileServelet_Parse " + ex.getMessage());
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
