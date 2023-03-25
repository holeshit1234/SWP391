/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.ControllerAdmin;

import DVHT.userdetails.UpdateUserDetailsErr;
import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
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
@WebServlet(name = "UpdateUserInfoServelt", urlPatterns = {"/UpdateUserInfoServelt"})
public class UpdateUserInfoByManagerAdminServelt extends HttpServlet {

    private final String UPDATE_FAIL = "updateInfoUserByAdminManager.jsp";
//    private final String UPDATE_FAIL ="testUpdate.jsp";
    private final String UPDATE_SUCCESS = "GetUserUpdate";

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
//        String usermame = request.getParameter("username");
        String userid = request.getParameter("userid");
        int userID = Integer.parseInt(userid);
        String fullname = request.getParameter("fullname");
        byte[] bytes = fullname.getBytes(StandardCharsets.ISO_8859_1);
        fullname = new String(bytes, StandardCharsets.UTF_8);
        String phone = request.getParameter("phone");
//        String gender = request.getParameter("gender");
//        String email = request.getParameter("emal");
        String password = request.getParameter("password");

        String url = "";
        HttpSession session = request.getSession(false);

        UpdateUserDetailsErr err = new UpdateUserDetailsErr();
        boolean flag = false;

        try {
            if (session != null) {
                UserDetailsDTO dto1 = (UserDetailsDTO) session.getAttribute("USER");

                if (dto1 != null) {
                    if (dto1.getRoleID() == 1 || dto1.getRoleID() == 2) {
                        if (button.equals("Save")) {
                            if (password.trim().length() < 8 || password.trim().length() > 20) {
                                flag = true;
                                err.setNotEnoughWordPassWord("PassWord about 10 to 20");
                            }
                            if (fullname.trim().length() < 1 || fullname.trim().length() > 30) {
                                flag = true;
                                err.setNotEnoughWordFullName("FullName about 5 to 20");
                            }

                            if (phone.trim().length() > 11 || phone.trim().length() < 9) {
                                flag = true;
                                err.setEmptyPhone("phone has 10 number");
                            }

                            if (flag) {
                                request.setAttribute("UP_ERROR", err);
                                url = UPDATE_FAIL;
                            } else {
                                UserDetailsDAO dao = new UserDetailsDAO();

                                boolean result = dao.updateProfileAdmin(userID, fullname, phone, password);

                                if (result) {
                                    url = "GetUserUpdate?btAction=UpdateInfo";
                                }

                            }
                        }
                    }
                }
            }else{
            url = "erorr.jsp";
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
