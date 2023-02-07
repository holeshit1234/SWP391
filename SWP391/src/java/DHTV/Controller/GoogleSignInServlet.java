package DHTV.Controller;

import DHTV.googlesignin.GoogleDTO;
import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
import DVHT.utils.GoogleSupport;
import DVHT.utils.MyAplications;
import java.io.IOException;
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
import javax.servlet.http.HttpSession;

@WebServlet(name = "GoogleSignInServlet", urlPatterns = {"/GoogleSignInServlet"})
public class GoogleSignInServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
         
        //1 get servlet context
        ServletContext context = this.getServletContext();
        //2 get sitemap
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");

        //String url = MyAplications.LoginServlet.SEARCH_STORE_PAGE;
            String url ="";
        try {
//            if (request.getSession(false).getAttribute("user") == null) {
                String code = request.getParameter("code");
                String accessToken = GoogleSupport.getToken(code);
                GoogleDTO userToken = GoogleSupport.getUserInfo(accessToken);
                String username = userToken.getId();
                log(username);
                if (username != null){
              url = siteMaps.getProperty(MyAplications.LoginServlet.SEARCH_STORE_PAGE);
                }
//
//                UserDetailsDTO user = null;
//
//                try {
//                    user = UserDetailsDAO.getUser(username);
//                } catch (SQLException ex) {
//                    log("GoogleSignInServlet_SQL_ " + ex.getMessage());
//                } catch (NamingException ex) {
//                    log("GoogleSignInServlet_Naming_ " + ex.getMessage());
//                }
//                if (user == null) {
//                    String email = userToken.getEmail();
//                    String firstName = userToken.getGiven_name();
//                    String lastName = userToken.getFamily_name();
//                    String picture = userToken.getPicture();
//
//                    user = new UserDetailsDTO(0, 0, username, picture, email, lastName, 0);
//
//                    try {
//                        UserDetailsDAO.addUser(user);
//                    } catch (SQLException ex) {
//                        log("GoogleSignInServlet_SQL_ " + ex.getMessage());
//                    } catch (NamingException ex) {
//                        log("GoogleSignInServlet_Naming_ " + ex.getMessage());
//                    }
//                }
//
//                HttpSession session = request.getSession();
//                session.setAttribute("user", user);
//
//            }
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
