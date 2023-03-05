/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.Controller;

import DHTV.product.ProductImgDAO;
import DHTV.product.ProductImgDTO;
import DVHT.comment.CommentDAO;
import DVHT.comment.CommentDTO;
import DVHT.rate.RateDAO;
import DVHT.rate.RateDTO;
import DVHT.userdetails.UserDetailsDTO;;
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

        try {
            System.out.println("Start into Comment Servlet");
            //String userID = request.getParameter("txtUserID");
            //--------------------------------

            //Get button submit comment
            String button = request.getParameter("txtSubmitComment");
            System.out.println("button = " + button);

            //get UserID
            int userID = 0;
            HttpSession session = request.getSession();
            UserDetailsDTO user = (UserDetailsDTO) session.getAttribute("USER");
            if (user != null) {
                userID = user.getUserID();
            }
            //get ProductID
            String txtproductID = request.getParameter("txtProductID");
            int productID = 1;
            if (txtproductID != null) {
                productID = Integer.parseInt(txtproductID.trim());
            }
            System.out.println("productID = " + txtproductID);
            System.out.println("UserID " + userID);

            if (userID > 0) {
                //process
                String description = request.getParameter("txtDescription");
                String rate = request.getParameter("rating");
                int point = 0;
                if (rate != null) {
                    point = Integer.parseInt(rate.trim());
                }
                CommentDAO dao = new CommentDAO();

                if (description != null && point > 0) {
                    CommentDTO dto = new CommentDTO(0, userID, productID, null, description, point ,true );
                    boolean result = dao.addComment(dto);
                } else {
                    if (button != null) {
                        String message = "Please enter at least your rating !";
                        if (!message.isEmpty()) {
                            request.setAttribute("MESSAGE2", message);
                        }
                    }
                }
            } else {
                if (button != null) {
                    String message = "Please sign in !";
                    if (!message.isEmpty()) {
                        request.setAttribute("MESSAGE", message);
                    }
                }
            }

            //-----------------------Get Comment to show-----------------------
            String txtStar = request.getParameter("star");
            if (txtStar != null) {
                System.out.println("star = " + txtStar);
                int star = Integer.parseInt(txtStar);

                if (star >= 1 && star <= 5) { //Get comment by star
                    CommentDAO dao = new CommentDAO();
                    dao.selectCommentListByStar(productID, star);
                    List<CommentDTO> list = dao.getCommentList();
                    //HttpSession session = request.getSession();
                    session.setAttribute("INFOCOMMENT", list);
                }
                if (star == 0) { //Get all comment

                    CommentDAO dao = new CommentDAO();
                    dao.selectCommentListFromSQL(productID);
                    List<CommentDTO> list = dao.getCommentList();
                    //HttpSession session = request.getSession();
                    session.setAttribute("INFOCOMMENT", list);
                }
                if (star == 6) { //Get comment by date

                    CommentDAO dao = new CommentDAO();
                    dao.selectCommentListDecreaseDate(productID);
                    List<CommentDTO> list = dao.getCommentList();
                    //HttpSession session = request.getSession();
                    session.setAttribute("INFOCOMMENT", list);
                }
                if (star == 7) { //Get comment by date

                    CommentDAO dao = new CommentDAO();
                    dao.selectCommentListIncreateDate(productID);
                    List<CommentDTO> list = dao.getCommentList();
                    //HttpSession session = request.getSession();
                    session.setAttribute("INFOCOMMENT", list);
                }
                if (star == 8) { //Get your comment by userID

                    CommentDAO dao = new CommentDAO();
                    dao.selectCommentListByUserID(productID, userID);
                    List<CommentDTO> list = dao.getCommentList();
                    //HttpSession session = request.getSession();
                    session.setAttribute("INFOCOMMENT", list);
                }

            } else {
                //Get all comment
                CommentDAO dao = new CommentDAO();
                dao.selectCommentListFromSQL(productID);
                List<CommentDTO> list = dao.getCommentList();
                //HttpSession session = request.getSession();
                session.setAttribute("INFOCOMMENT", list);
            }
            //get img product
            ProductImgDAO daoImg =new  ProductImgDAO();            
            daoImg.getImgByProductID(productID);
            List<ProductImgDTO> listImg = daoImg.getImgList();
            request.setAttribute("IMG_LIST", listImg);
            
            //save productID 
            request.setAttribute("PRODUCTID", productID);
            request.setAttribute("USERID", userID);

        } catch (SQLException ex) {
            log("Comment Servlet SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Comment Servlet Naming: " + ex.getMessage());
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
