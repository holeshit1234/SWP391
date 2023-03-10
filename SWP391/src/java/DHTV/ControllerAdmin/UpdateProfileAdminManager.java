/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.ControllerAdmin;

import DHTV.address.AddressDAO;
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
 * @author User
 */
@WebServlet(name = "UpdateProfileAdminManager", urlPatterns = {"/UpdateProfileAdminManager"})
public class UpdateProfileAdminManager extends HttpServlet {

    private final String UPDATE_FAIL = "profileAdminManager.jsp";
    private final String UPDATE_SUCCESS = "ShowProfileAdminManager";

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

        String fullName = request.getParameter("txtFullName");
        byte[] bytes = fullName.getBytes(StandardCharsets.ISO_8859_1);
        fullName = new String(bytes, StandardCharsets.UTF_8);

        String DOB = request.getParameter("txtDOB");

        String phone = request.getParameter("txtPhone");

        String password = request.getParameter("txtPassWord");

        String street = request.getParameter("txtStreet");
        byte[] bytes1 = street.getBytes(StandardCharsets.ISO_8859_1);
        street = new String(bytes1, StandardCharsets.UTF_8);

//        String addid = request.getParameter("txtaddressid");
//        int id = Integer.parseInt(addid);
        String province = request.getParameter("txtProvince");
        byte[] bytes2 = province.getBytes(StandardCharsets.ISO_8859_1);
        province = new String(bytes2, StandardCharsets.UTF_8);

        String district = request.getParameter("txtDistrict");
        byte[] bytes3 = district.getBytes(StandardCharsets.ISO_8859_1);
        district = new String(bytes3, StandardCharsets.UTF_8);

        String ward = request.getParameter("txtWard");
        byte[] bytes4 = ward.getBytes(StandardCharsets.ISO_8859_1);
        ward = new String(bytes4, StandardCharsets.UTF_8);

        String url = "";
        HttpSession session = request.getSession(false);

        UpdateUserDetailsErr err = new UpdateUserDetailsErr();
        boolean flag = false;

        try {
            if (button.equals("Save")) {
                if (session != null) {

                    UserDetailsDTO dto = (UserDetailsDTO) session.getAttribute("USER");
                    System.out.println(dto);

                    if (dto != null) {
                        int userid = dto.getUserID();

                        if (password.trim().length() < 8 || password.trim().length() > 20) {
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
                            Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(DOB);

                            UserDetailsDAO dao = new UserDetailsDAO();
                            boolean result = dao.updateProfile(userid, fullName, phone, dob, password);
                            if (result) {
                                AddressDAO dao1 = new AddressDAO();

                                boolean result1 = dao1.updateAddressAdminManager(userid, street, province, district, ward);
                                if (result1) {
                                    url = UPDATE_SUCCESS;
                                }
                            }
                        }
                    }
                }
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
