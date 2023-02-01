///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package dunghm.controller;
//
//import dunghm.utils.MyApplication;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Properties;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author dunghm
// */
//@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
//public class DispatchController extends HttpServlet {
//
////    private final String FIRST_TIME_LOGIN_CONTROLLER = "FirstTimeLoginServlet";
////    private final String LOGIN_PAGE = "login.html";
////    private final String LOGIN_CONTROLLER = "LoginServlet";
////    private final String SEARCH_CONTROLLER = "SearchServlet";
//    private final String DELETE_CONTROLLER = "DeleteAccountServlet";
//    private final String UPDATE_CONTROLLER = "UpdateAccountServlet";
//    private final String LOGOUT_CONTROLLER = "LogoutAccountServlet";
//    private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
//    private final String REMOVE_FROM_CART_CONTROLLER = "RemoveFromCartServlet";
//    private final String MANAGE_ACCOUNT_PAGE = "search.jsp";
//    private final String MANAGE_STORE_CONTROLLER = "StoreManage.jsp";
//    private final String UPDATE_PRODUCT_MANAGER_CONTROLLER = "UpdateProductManagerServlet";
//    private final String Delete_PRODUCT_MANAGER_CONTROLLER = "DeleteProductManagerServlet";
//    private final String CREATE_PRODUCT_MANEGER_CONTROLLER = "CreateProductManagerServlet";
//    private final String SEARCH_PRODUCT_MANEGER_CONTROLLER = "SearchProductManagerServlet";
//    private final String SIGN_UP_PAGE = "SignUp.html";
//    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet";
//    private final String SEARCH_PRODUCT_USER_CONTROLLER = "SearchProductUserServlet";
//    private final String STORE_PAGE = "Store.html";
//    private final String VIEW_CART="view_cart.jsp";
//    private final String CHECKOUT_PAGE="CheckOut.jsp";
//    private final String PAY_CONTROLLER="PayServlet";
//
//    
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//        //1. take serlvet context
//        ServletContext context = this.getServletContext();
//        //2. get properties take sitemap
//        Properties siteMaps = (Properties)context.getAttribute("SITE_MAP");
//        
//        String url ="";
//        String button = request.getParameter("btAction");
////        String url = 
////                siteMaps.getProperty(MyApplication.DispatchController.LOGIN_PAGE);
//        try {
//            if (button == null) {
//                //url = FIRST_TIME_LOGIN_CONTROLLER;
//            } /*  //else if (button.equals("Login")) {
//               // url = LOGIN_CONTROLLER;
//               //url = 
//               // siteMaps.getProperty(MyApplication.DispatchController.LOGIN_CONTROLLER);
////             else if (button.equals("Search")) {
////                url = 
////                 siteMaps.getProperty(MyApplication.DispatchController.SEARCH_CONTROLLER);;
////            } else if (button.equals("Update")) {
////                url = UPDATE_CONTROLLER;
////            } else if (button.equals("Delete")) {
////                url = DELETE_CONTROLLER;
////            } else if (button.equals("Logout")) {
////                url = LOGOUT_CONTROLLER;
////             else if (button.equals("AddToCart")) {
////                url = ADD_TO_CART_CONTROLLER;
//            }*/ else if (button.equals("removeItems")) {
//                url = REMOVE_FROM_CART_CONTROLLER;
//            } else if (button.equals("view_your_cart")) {
//                url = VIEW_CART;
//            }else if (button.equals("Manage_User_Accounts")) {
//                url = MANAGE_ACCOUNT_PAGE;
//            } else if (button.equals("Manage_Store")) {
//                url = MANAGE_STORE_CONTROLLER;
//            } else if (button.equals("")) {
//                url = CREATE_PRODUCT_MANEGER_CONTROLLER;
//            } else if (button.equals("DeleteProduct")) {
//                url = Delete_PRODUCT_MANAGER_CONTROLLER;
//            } else if (button.equals("SearchBookManager")) {
//                url = SEARCH_PRODUCT_MANEGER_CONTROLLER;
//            } else if (button.equals("UpdateProduct")) {
//                url = UPDATE_PRODUCT_MANAGER_CONTROLLER;
//            } else if (button.equals("SignUP")) {
//                url = SIGN_UP_PAGE;
//            } else if (button.equals("create_New_Account")) {
//                url = CREATE_ACCOUNT_CONTROLLER;
//            }else if(button.equals("SearchBook")){
//                url=SEARCH_PRODUCT_USER_CONTROLLER;
//            }else if(button.equals("Click Here To Buy")){
//                url=STORE_PAGE;
//            }/*else if(button.equals("checkout")){
//                url=CHECKOUT_PAGE;
//            }*/else if(button.equals("Pay")){
//                url= PAY_CONTROLLER ;
//            }
//        } catch (NullPointerException ex) {
//            log("DisPatch _Null " + ex.getMessage());
//        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
