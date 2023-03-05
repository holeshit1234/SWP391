///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package DHTV.Controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author User
// */
//@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
//public class DispatchController extends HttpServlet {
//
//    private final String FIRST_TIME_REQUEST = "FirstTimeRequestServlet";
//    private final String LOGIN_CONTROLLER = "LoginServlet";
//    private final String USER_VERIFY_CONTROLLER = "UserVerifyServlet";
//    private final String CODE_VERIFY_CONTROLLER = "VerifyCodeServlet";
//    private final String RESET_PASSWORD_CONTROLLER = "ResetPasswordServlet";
//    private final String LOGOUT_CONTROLLER = "LogoutAccountServlet";
//    private final String WRITE_INFO_GG = "writeInformationGgServlet";
//    private final String REGISTER_CONTROLLER = "SignUpPageServlet";
//    private final String UPDATE_PROFILE_CONTROLLER = "UpdateProfileServlet";
//    private final String SHOW_PROFILE = "ShowProfileServlet";
//    private final String SAVE_PHOTO = "ChangeAvatarServlet";
//    private final String DETAIl_PRODUCT = "CommentServlet";
//    private final String UPDATE_ADDRESS_CONTROLLER = "UpdateAddressServlet";
//    private final String DELETE_ADDRESS_CONTROLLER = "DeleteAddressServlet";
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
//        String button = request.getParameter("btAction");
//        String url = "";
//        System.out.println(button);
//
//        try {
//            if (button == null) {
//                url = FIRST_TIME_REQUEST;
//            } else if (button.equals("login")) {
//                url = LOGIN_CONTROLLER;
//            } else if (button.equals("Send")) {
//                url = USER_VERIFY_CONTROLLER;
//            } else if (button.equals("VERIFY")) {
//                url = CODE_VERIFY_CONTROLLER;
//            } else if (button.equals("SAVE")) {
//                url = RESET_PASSWORD_CONTROLLER;
//            } else if (button.equals("Logout")) {
//                url = LOGOUT_CONTROLLER;
//            } else if (button.equals("Summit")) {
//                url = WRITE_INFO_GG;
//            } else if (button.equals("Register")) {
//                url = REGISTER_CONTROLLER;
//            } else if (button.equals("Save Change")) {
//                url = UPDATE_PROFILE_CONTROLLER;
//            } else if (button.equals("show")) {
//                url = SHOW_PROFILE;
//            }else if (button.equals("save photo")){
//                url=SAVE_PHOTO;
//            }else if (button.equals("Detail")){
//                url=DETAIl_PRODUCT;
//            }else if (button.equals("Update")){
//                url=UPDATE_ADDRESS_CONTROLLER;
//            }else if (button.equals("Delete")){
//                url=DELETE_ADDRESS_CONTROLLER;
//            }
//
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
