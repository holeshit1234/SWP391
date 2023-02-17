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
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "writeInformationGgServlet", urlPatterns = {"/writeInformationGgServlet"})
public class writeInformationGgServlet extends HttpServlet {

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
        
        
        
        
        String gender = request.getParameter("txtGender");
         byte[] bytes1 = gender.getBytes(StandardCharsets.ISO_8859_1);
        gender = new String(bytes1, StandardCharsets.UTF_8);

        String DOB = request.getParameter("txtDOB");
        String phone = request.getParameter("txtPhone");
        String province = request.getParameter("txtProvince");
         byte[] bytes2 = province.getBytes(StandardCharsets.ISO_8859_1);
        province = new String(bytes2, StandardCharsets.UTF_8);

        String district = request.getParameter("txtDistrict");
         byte[] bytes3 = district.getBytes(StandardCharsets.ISO_8859_1);
        district = new String(bytes3, StandardCharsets.UTF_8);

        String ward = request.getParameter("txtWard");
        byte[] bytes4 = ward.getBytes(StandardCharsets.ISO_8859_1);
        ward = new String(bytes4, StandardCharsets.UTF_8);

        String street = request.getParameter("txtStreet");
         byte[] bytes5 = street.getBytes(StandardCharsets.ISO_8859_1);
        street = new String(bytes5, StandardCharsets.UTF_8);


         //1 get servlet context
        ServletContext context = this.getServletContext();
        //2 get sitemap
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        
        HttpSession session = request.getSession(false);
        
        String url = (String) 
                siteMaps.getProperty(MyAplications.writeInformationGgServlet.WRITE_INFORMATION);
              
        try  {
            if(session != null){
               AddressDTO dto = (AddressDTO) session.getAttribute("USERE");
                System.out.println(dto);
                if(dto != null){
                     int userid = dto.getUserID();
                     
                     Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(DOB);
                     
                    UserDetailsDAO dao = new UserDetailsDAO();
                    boolean result = dao.updateGgAc(userid, phone, phone, dob, gender);
                    
                    AddressDAO dao1 = new AddressDAO();
                    boolean result1 = dao1.updateAddress(userid, street, province, district, ward);
                    
                    
                    
                    if(result && result1){
                        url = (String) 
                                siteMaps.getProperty(MyAplications.writeInformationGgServlet.PROFILE_PAGE);
                        //session.setAttribute("USER", result);
                    }
                }
            }
            
        }catch (NamingException ex) {
            log("UpdateProfileServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateProfileServlet_SQL " + ex.getMessage());
        } catch (ParseException ex) {
            log("UpdateProfileServelet_Parse " + ex.getMessage());
        }finally {
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
