/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package A_Service_Team;

import AdminDAO.Method_Payment;
import AdminDAO.adminDAO;
import DHTV.product.ProductDAO;
import DHTV.product.ProductDTO;
import com.google.gson.Gson;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
 
@WebServlet(name = "AdminPage", urlPatterns = {"/AdminPage"})
public class AdminController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("add") != null) {
            try {
                adminDAO.AddMethod_AdminPage(req.getParameter("method"), 1);
            } catch (Exception e) {
                resp.getWriter().print(e);
            }
        }
        if (req.getParameter("update") != null) {
            String method = "";
            String status = "";
            String id = "";
            try {
                id = req.getParameter("id");
                method = req.getParameter("method");
                status = req.getParameter("status");
            } catch (Exception e) {
                resp.getWriter().print(e);
            }
            try {
                System.err.println("Update:" + id + method + status);
                new adminDAO().updateMethod_AdminPage(id, method, status);
            } catch (NamingException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        resp.sendRedirect("AdminPage");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("delete") != null) {
            try {
                adminDAO dao = new adminDAO();
                dao.DeleteMethod_AdminPagev2(request.getParameter("id"));
            } catch (NamingException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (request.getParameter("approve") != null) {
            try {
                response.getWriter().print(request.getParameter("id"));
                new adminDAO().Approve_AdminPage(request.getParameter("id"));
                request.setAttribute("list", new adminDAO().show_Order());
                request.getRequestDispatcher("admin_order.jsp").forward(request, response);
                return;
            } catch (NamingException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (request.getParameter("reject") != null) {
            try {
                response.getWriter().print(request.getParameter("id"));
                new adminDAO().Reject_AdminPage(request.getParameter("id"));
                request.setAttribute("list", new adminDAO().show_Order());
                request.getRequestDispatcher("admin_order.jsp").forward(request, response);
                 return;
            } catch (NamingException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(request.getParameter("orderdetail") != null){
            try {
                 request.setAttribute("list", new adminDAO().show_OrderDetail(request.getParameter("orderdetail")));
                request.getRequestDispatcher("adminOrderDetail.jsp").forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         if(request.getParameter("showReport") != null){
            try {
                 request.setAttribute("list", new adminDAO().show_Report(""));
                request.getRequestDispatcher("adminReport.jsp").forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(request.getParameter("orderpage") != null){
            try {
                 request.setAttribute("list", new adminDAO().show_Order());
                request.getRequestDispatcher("admin_order.jsp").forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            response.setContentType("text/html;charset=UTF-8");
            List<Method_Payment> result = new ArrayList<>();
            try {
                adminDAO dao = new adminDAO();
                result = dao.show_Method_Payment();
            } catch (NamingException ex) {
                log("ShowItemsServlet _ Naming _ " + ex.getMessage());
            } catch (SQLException ex) {
                log("ShowItemsServlet _ SQL _ " + ex.getMessage());
            } finally {
                request.setAttribute("list", result);

                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }
        }
            

        }
    

}
