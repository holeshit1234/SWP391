/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.registration;

import dunghm.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author dunghm
 */
public class RegistrationDAO {
    public RegistrationDTO checkLogin(String username, String password)
            throws /*ClassNotFoundException,*/ SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //boolean result = false;
        RegistrationDTO result = null;

        try {
            //1.Conect Database
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Select username, lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? And password = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.execute query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    Boolean role = rs.getBoolean("isAdmin");
                    String fullname = rs.getString("lastname");
                    result = new RegistrationDTO(username, password,
                            fullname, role);
                }
            } //end con is availible
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
    
    private List<RegistrationDTO> accountLists;

    public List<RegistrationDTO> getAccountLists() { // thuoc tinh bat cau
        return accountLists;
    }

    public void searchLastname(String searchValue)
            throws /*ClassNotFoundException,*/ SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Conect Database
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname like ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4.execute query
                rs = stm.executeQuery();
                //5. process result
                while (rs.next()) {
                    //get field/column
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    Boolean role = rs.getBoolean("isAdmin");
                    //create DTO instance
                    RegistrationDTO dto
                            = new RegistrationDTO(username, password,
                                    lastname, role);
                    //add to account list
                    if (this.accountLists == null) {
                        this.accountLists = new ArrayList<>();
                    }//end account List is not exited                   
                    this.accountLists.add(dto); //account is availible
                }//end rs has more than 1 record

            } //end of con
        }finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    public boolean deleteAccount(String username)
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1.Conect Database
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Delete From Registration "
                        + "where username = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);              
                //4.execute query
                int effectiveRows = stm.executeUpdate();
                //5. process result
                if(effectiveRows>0){
                    result = true;
                }
            } //end of con
        }finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public boolean UpdateAccount(String username, String password, boolean role) 
            throws SQLException, NamingException{
       boolean result = false; 
       Connection con = null;
       PreparedStatement stm = null;
       
       try{
           //get connection
           con = DBHelper.createConnection();
           //sql commmands
           String sql = "Update Registration "
                   + "Set password=?,isAdmin=? "
                   + "Where username=?";
           
           //create statement
           stm = con.prepareStatement(sql);
           stm.setString(1, password );
           stm.setBoolean(2, role );
           stm.setString(3, username );          
           //execute querry
           int rows = stm.executeUpdate();
           //process result
           if(rows > 0){
               result = true;
           }
       }finally{
           if (stm != null){
               stm.close();
           }
           
           if(con != null){
               con.close();
           }
       }
       
       return result;
    }
    
    public boolean CreateAccount(RegistrationDTO dto) 
            throws SQLException, NamingException{
        boolean result = false;
        if(dto == null){
            return result;
        }
        Connection con = null;
        PreparedStatement stm = null;
        
        try{
            //1. get connection
            con = DBHelper.createConnection();
            if(con!= null){
                //2 sql
                String sql="Insert Into Registration(username, password, lastname, isAdmin) "
                        + "Values(?, ?, ?, ?)";
                //create stm
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                //execute
                int row = stm.executeUpdate();
                //process
                if(row >0){
                    result = true;
                }
            }// end con existed
        }finally{
            if(stm!=null){
                stm.close();
            }
            if(con!=null){
                con.close();
            }
        }       
        return result;
    }
}
