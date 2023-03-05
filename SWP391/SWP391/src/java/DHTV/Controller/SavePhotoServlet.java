/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
import DVHT.utils.DBHelpers;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author User
 */
@MultipartConfig
public class SavePhotoServlet extends HttpServlet {

    private final String UPDATE_SUCCESS = "ShowProfileServlet";

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

        Part part = request.getPart("txtPicture");
        String imaName = part.getSubmittedFileName();
        String button = request.getParameter("btAction");
        System.out.println(button);
        
        
        String userid = request.getParameter("txtuserid");
        int id = Integer.parseInt(userid);

        HttpSession session = request.getSession(false);
        UserDetailsDTO dto = new UserDetailsDTO();

        dto.setUserID(id);
        dto.setPicture(imaName);

        String url = "";

        try {
            if (button.equals("save photo")) {
                UserDetailsDAO dao = new UserDetailsDAO();

                boolean result = dao.UpdateImg(id, imaName);

                if (result) {
                    //String directory = "D:/copylaij/SWP391/SWP391/web/asset/images/useravatar"; // Change this to the directory of your choice
                    String directory = "E:/KY5/SWP/Project/2/SWP391/SWP391/web/asset/images/useravatar"; // Change this to the directory of your choice

                    // Create the directory if it doesn't exist
                    File dir = new File(directory);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    // Save the uploaded image to the specified directory
                    String path = directory + File.separator + dto.getPicture();
                    DBHelpers.deleteFile(path);

                    DBHelpers.saveFile(part.getInputStream(), path);

                    url = UPDATE_SUCCESS;
//                    url = "ShowProfileServlet";
                }
            }
        } catch (NamingException ex) {
            log("ChangeAvatarServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("ChangeAvatarServlet_SQL " + ex.getMessage());
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
