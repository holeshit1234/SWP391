/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.controller;

import dunghm.registration.RegistrationCreateErr;
import dunghm.registration.RegistrationDAO;
import dunghm.registration.RegistrationDTO;
import dunghm.utils.MyApplication;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dunghm
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    
//    private final String ERROR_PAGE = "SignUp.jsp";
//    private final String LOGIN_PAGE ="login.html";
    
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
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        
        RegistrationCreateErr errors = new RegistrationCreateErr();
        boolean errorfound = false;
        String url = (String) siteMaps.get(MyApplication.createAccountServlet.ERR_PAGE);
        
              
        try {
            //1. check all user error
            if(username.trim().length() < 6 || username.trim().length() >20 ){
                errorfound = true;
                errors.setUsernameLengthError("Username is required in 6 to 20");
            }
            
            if(password.trim().length() < 6 || password.trim().length() >20 ){
                errorfound = true;
                errors.setPasswordLengthError("Password is required in 6 to 20");
            }else if(!confirm.trim().equals(password.trim())){
                errorfound = true;
                errors.setConfirmNotMatched("It not match with password");
            }
            if(fullname.trim().length() < 6 || fullname.trim().length() >20 ){
                errorfound = true;
                errors.setFullnameLengthError("Fullname is required in 2 to 50");
            }
            
            if(errorfound){
                //1. set attribute để lưu trữ
                request.setAttribute("CREATE_ERROR", errors);               
            }else{
                //2. insert to database
                //2.1 call DAO
                RegistrationDTO dto = new RegistrationDTO(username,
                        password, fullname, false);
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.CreateAccount(dto);
                
                if(result){
                    //2.2 go to login
                    url = (String) 
                            siteMaps.get(MyApplication.createAccountServlet.LOGIN_PAGE);
                }
            }//account is created          
        }catch(SQLException ex){
            String msg = ex.getMessage();
            log("CreateAccountServlet _SQL " + ex.getMessage());
            if(msg.contains("duplicate")){
                errors.setUsernameIsExisted( username + "is existed");
                request.setAttribute("CREATE_ERROR", errors);
            }
        }catch(NamingException ex){
            log("CreateAccountServlet _Naming " + ex.getMessage());
        }
        finally{
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
