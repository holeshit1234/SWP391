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
import java.util.List;
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
@WebServlet(name = "ShowProfileServlet", urlPatterns = {"/ShowProfileServlet"})
public class ShowProfileServlet extends HttpServlet {

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

        //1.get servlet Context
        ServletContext context = this.getServletContext();
        //2. get siteMap
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        String url = "";
        HttpSession session = request.getSession(false);

        try {
            if (session != null) {
                //AddressDTO dto1 = (AddressDTO) session.getAttribute("USERE");
                //System.out.println(dto1);

                UserDetailsDTO dto = (UserDetailsDTO) session.getAttribute("USER");
                System.out.println(dto);

                if (dto != null) {
                    //AddressDTO dto1 = (AddressDTO) session.getAttribute("USER");                
                    int userid = dto.getUserID();
                    System.out.println(userid);
                    UserDetailsDAO dao = new UserDetailsDAO();
                    UserDetailsDTO result = dao.getInfoUser(userid);
                    System.out.println(result);

                    if (result != null) {
                        // call DAO
                        AddressDAO dao1 = new AddressDAO();
                        dao1.getAddress(userid);
                        //take data grid
                        List<AddressDTO> result1 = dao1.getInfoList();

                        request.setAttribute("INFO", result1);
                        request.setAttribute("USERS", result);

                        url = siteMaps.getProperty(MyAplications.ShowProfileServlet.PROFILE_PAGE);

                    }
                }
//                } else if (dto1 != null) {
//                    int userid = dto1.getUserID();
//                    //userid = dto1.getUserID();
//                    System.out.println(userid);
//                    UserDetailsDAO dao = new UserDetailsDAO();
//                    UserDetailsDTO result = dao.getInfoUser(userid);
//                    System.out.println(result);
//
//                    if (result != null) {
//                        // call DAO
//                        AddressDAO dao1 = new AddressDAO();
//                        dao1.getAddress(userid);
//                        //take data grid
//                        List<AddressDTO> result1 = dao1.getInfoList();
//
//                        request.setAttribute("INFO", result1);
//                        request.setAttribute("USERS", result);
//
//                        url = siteMaps.getProperty(MyAplications.ShowProfileServlet.PROFILE_PAGE);
//
//                    }
//                }

//                if(dto == null) {
//                  
//                    if (dto1 != null) {
//                        int userid = dto.getUserID();
//                        System.out.println(userid);
//                        UserDetailsDAO dao = new UserDetailsDAO();
//                        UserDetailsDTO result = dao.getInfoUser(userid);
//                        System.out.println(result);
//
//                        if (result != null) {
//                            // call DAO
//                            AddressDAO dao1 = new AddressDAO();
//                            dao1.getAddress(userid);
//                            //take data grid
//                            List<AddressDTO> result1 = dao1.getInfoList();
//
//                            request.setAttribute("INFO", result1);
//                            request.setAttribute("USERS", result);
//
//                            url = siteMaps.getProperty(MyAplications.ShowProfileServlet.PROFILE_PAGE);
//                        }
//                    }
//                }
            }

        } catch (NamingException ex) {
            log("ShowProfileServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("ShowProfileServlet_SQL " + ex.getMessage());
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
