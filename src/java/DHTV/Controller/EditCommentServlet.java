/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DVHT.comment.CommentDAO;
import DVHT.userdetails.UserDetailsDTO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "EditCommentServlet", urlPatterns = {"/EditCommentServlet"})
public class EditCommentServlet extends HttpServlet {

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
        String url = "CommentServlet";
        try {
            //get para
            int userID = 0;
            int productID = 0;
            String txtUserID = request.getParameter("txtUserID");
            if (txtUserID != null) {
                userID = Integer.parseInt(txtUserID.trim());
            }
            String txtProductID = request.getParameter("txtProductID");
            if (txtProductID != null) {
                productID = Integer.parseInt(txtProductID.trim());
            }
            String newComment = request.getParameter("txtNewComment");
            //System.out.println(newComment + "," + productID + " , " + userID);

            //-----------------------------------------------------------------------------------------------
//            HttpSession session = request.getSession();
//            UserDetailsDTO currentUserID = (UserDetailsDTO) session.getAttribute("USER");

            CommentDAO dao = new CommentDAO();
            boolean result = dao.editComment(userID, productID, newComment);
            if (result) {
                System.out.println("Edited comment!");
            }
            request.setAttribute("PRODUCTID", productID);
            request.setAttribute("USERID", userID);

        } catch (NamingException ex) {
            log("EditCommentServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("EditCommentServlet_SQL " + ex.getMessage());
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
