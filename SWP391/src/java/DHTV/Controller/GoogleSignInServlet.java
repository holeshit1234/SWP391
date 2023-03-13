package DHTV.Controller;

import DHTV.address.AddressDAO;
import DHTV.address.AddressDTO;
import DHTV.googlesignin.GoogleDTO;
import DVHT.userdetails.UserDetailsDAO;
import DVHT.userdetails.UserDetailsDTO;
import DVHT.utils.GoogleSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "GoogleSignInServlet", urlPatterns = {"/GoogleSignInServlet"})
public class GoogleSignInServlet extends HttpServlet {

    private final String SHOW_ITEM_PAGE = "ShowIdexItemServlet";
    private final String WRITE_INFOR_PAGE = "ResignGGAccount.jsp";
    private final String LOGIN = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int key = 0;

        //String url = MyAplications.LoginServlet.SEARCH_STORE_PAGE;
        String url = "";
        try {
            if (request.getSession(false).getAttribute("user") == null) {
                String code = request.getParameter("code");
                String accessToken = GoogleSupport.getToken(code);
                GoogleDTO userToken = GoogleSupport.getUserInfo(accessToken);
                String username = userToken.getId();
                String email = userToken.getEmail();

                UserDetailsDTO user = null;

                try {
                    user = UserDetailsDAO.getUser(email);
                    if (user != null) {

                        if (user.isStatus() == true) {
                            url = SHOW_ITEM_PAGE;

                            HttpSession session = request.getSession();
                            session.setAttribute("USER", user);
                        } else {
                            String message = "email ban";

                            if (!message.isEmpty()) {
                                HttpSession session = request.getSession();
                                session.setAttribute("LOGIN_ERROR", message);
                            }
                            url = LOGIN;
                        }

                    }
                } catch (SQLException ex) {
                    log("GoogleSignInServlet_SQL_ " + ex.getMessage());
                } catch (NamingException ex) {
                    log("GoogleSignInServlet_Naming_ " + ex.getMessage());
                }
                if (user == null) {

                    String fullname = userToken.getGiven_name();

                    //user = new UserDetailsDTO(0, 3, email, "user", email, fullname, "other", null, "other");
                    UserDetailsDTO newUser = new UserDetailsDTO(0, 3, email, "user", email, fullname, "other", null, "other", "logo.png");

                    try {

                        key = UserDetailsDAO.addUser(newUser);
                        newUser.setUserID(key);
                        if (key != 0) {
                            //                           AddressDAO dao = new AddressDAO();
                            AddressDTO addr = null;
                            addr = new AddressDTO(0, key, "other", "other", "other", "other", true);
                            System.out.println(newUser);

                            AddressDAO.addAddressGooogle(addr, key);

                            HttpSession session = request.getSession();

                            //session.setAttribute("USERE", addr);
                            session.setAttribute("USER", newUser);

                        }
                        url = WRITE_INFOR_PAGE;
                    } catch (SQLException ex) {
                        log("GoogleSignInServlet_SQL_ " + ex.getMessage());
                    } catch (NamingException ex) {
                        log("GoogleSignInServlet_Naming_ " + ex.getMessage());
                    } catch (ParseException ex) {
                        log("GoogleSignInServlet_Parse_ " + ex.getMessage());
                    }
                }

            }
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
