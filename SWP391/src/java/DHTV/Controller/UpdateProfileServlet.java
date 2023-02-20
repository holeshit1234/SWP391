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
import DVHT.utils.MyAplications;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        byte[] bytes = fullName.getBytes(StandardCharsets.ISO_8859_1);
        fullName = new String(bytes, StandardCharsets.UTF_8);

        String DOB = request.getParameter("txtDOB");
        //Date dob = Date.valueOf(DOB);
        String email = request.getParameter("txtEmail");
        String phone = request.getParameter("txtPhone");

        String street = request.getParameter("txtStreet");
        byte[] bytes1 = street.getBytes(StandardCharsets.ISO_8859_1);
        street = new String(bytes1, StandardCharsets.UTF_8);

        String province = request.getParameter("txtProvince");
        byte[] bytes2 = province.getBytes(StandardCharsets.ISO_8859_1);
        province = new String(bytes2, StandardCharsets.UTF_8);

        String district = request.getParameter("txtDistrict");
        byte[] bytes3 = district.getBytes(StandardCharsets.ISO_8859_1);
        district = new String(bytes3, StandardCharsets.UTF_8);

        String ward = request.getParameter("txtWard");
        byte[] bytes4 = ward.getBytes(StandardCharsets.ISO_8859_1);
        ward = new String(bytes4, StandardCharsets.UTF_8);

        String password = request.getParameter("txtPassWord");

        //1.get servlet Context
        ServletContext context = this.getServletContext();
        //2. get siteMap
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        String url = "ShowProfileServlet";
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
//                    if(password.trim().length() < 1 || password.trim().length() >20 ){
//                        flag = true;
//                        err.setNotEnoughWordPassWord("PassWord about 10 to 20");
//                    }
                    if (fullName.trim().length() < 1 || fullName.trim().length() > 30) {
                        flag = true;
                        err.setNotEnoughWordFullName("FullName about 5 to 20");
                    }
                    if (DOB.trim().length() < 1 || DOB.trim().length() > 20) {
                        flag = true;
                        err.setEmptyDOB("Form of DOB is yyyy-mm-dd");
                    }
                    if (phone.trim().length() > 11 || phone.trim().length() < 9) {
                        flag = true;
                        err.setEmptyDOB("phone has 10 number");
                    }
                    if (street.trim().length() < 1 || street.trim().length() > 30) {
                        flag = true;
                        err.setEmptyStreet("Street can not empty");
                    }
                    if (province.trim().length() < 1 || phone.trim().length() > 30) {
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
                        url = (String) siteMaps.get(MyAplications.UpdateProfileServlet.UPDATE_PAGE);
                    } else {
                        Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(DOB);

                        UserDetailsDAO dao = new UserDetailsDAO();
                        boolean result = dao.updateProfile(userid, email, fullName, phone, dob);

                        //AddressDTO adto = (AddressDTO) session.getAttribute("INFO");
                        //if (adto != null) {
                        // int addressid = adto.getAddressID();
                        AddressDAO dao2 = new AddressDAO();
                        //boolean result2 = dao2.updateAddress(userid, street, province, district, ward, addressid);
                        boolean result2 = dao2.updateAddress(userid, street, province, district, ward);
                        //refesh data grid

                        if (result || result2) {
                            url = (String) siteMaps.get(MyAplications.UpdateProfileServlet.UPDATE_PAGE);
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
