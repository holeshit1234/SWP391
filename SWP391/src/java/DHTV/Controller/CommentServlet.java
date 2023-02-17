/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DVHT.comment.CommentDAO;
import DVHT.comment.CommentDTO;
import DVHT.rate.RateDAO;
import DVHT.rate.RateDTO;
import DVHT.utils.MyAplications;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
@WebServlet(name = "CommentServlet", urlPatterns = {"/CommentServlet"})
public class CommentServlet extends HttpServlet {

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
        String url = "product.jsp";
        
        
        try{
            //Insert to data new Comment
            //String productID = request.getParameter("txtProductID");
            //String userID = request.getParameter("txtUserID");
            int userID = 3;
            int productID = 1;
           
            
            String description = request.getParameter("txtDescription");
            String rate = request.getParameter("rating");
            int point=1;
            if(rate != null)
                point = Integer.parseInt(rate.trim());
            CommentDAO dao = new CommentDAO();
            
            if(description != null){
            
                CommentDTO dto = new CommentDTO(0, userID, productID, null, description ,point);
                boolean result = dao.addComment(dto);
            }
            
            //Get all comment
            
            dao.selectCommentListFromSQL(productID);
            List<CommentDTO> list = dao.getCommentList();
            HttpSession session = request.getSession();
            session.setAttribute("INFOCOMMENT", list);
            
        }      
         catch (SQLException ex) {
            log("Comment Servlet SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Comment Servlet Naming: " + ex.getMessage());
        }
        finally{
            response.sendRedirect(url);
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
