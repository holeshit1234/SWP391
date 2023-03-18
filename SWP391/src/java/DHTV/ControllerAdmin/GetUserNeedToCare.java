/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.ControllerAdmin;

import DVHT.comment.CommentDAO;
import DVHT.comment.CommentDTO;
import DVHT.report.ReportDAO;
import DVHT.report.ReportDTO;
import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "GetUserNeedToCare", urlPatterns = {"/GetUserNeedToCare"})
public class GetUserNeedToCare extends HttpServlet {

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

        String url = "";
        try {
            ReportDAO dao = new ReportDAO();

            dao.getUserNeedToCare();

            List<ReportDTO> result = dao.getGetCareList();
            List<CommentDTO> allResults = new ArrayList<>();
            if (result != null) {

                for (ReportDTO report : result) {
                    int commentid = report.getCommentID();

                    CommentDAO dao1 = new CommentDAO();

                    dao1.getUserNeedCare(commentid);

                    List<CommentDTO> result1 = dao1.getListUserReport();

                    allResults.addAll(result1);
                    //}
                }
            }
            //for (CommentDTO comment : result1) {
            //int userid = comment.getUserID();
            // UserDetailsDAO dao2 = new UserDetailsDAO();
            //dao2.showUserById(userid);
            // List<UserDetailsDTO> result2 = dao2.getListReport();
            //request.setAttribute("REPORT", result);
            request.setAttribute("COMENT", allResults);
            request.setAttribute("COUNTREPORT", result);

            url = "ShowUserAlert.jsp";

        } catch (NamingException ex) {
            log("GetUserNeedToCare _Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("GetUserNeedToCare _SQL " + ex.getMessage());
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
