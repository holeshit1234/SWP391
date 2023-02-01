/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.userdetails;

import DVHT.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author User
 */
public class UserDetailsDAO implements Serializable{
    
    public UserDetailsDTO checkLogin(String username, String password)
    throws NamingException, SQLException{
        
        UserDetailsDTO result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
           try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Select UserID, FullName, RoleID, Email, Phone "
                        + "From UserDetails "
                        + "Where UserName = ? And PassWord = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.execute query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    int userid = rs.getInt("UserID");
                    int role = rs.getInt("RoleID");                  
                    String fullname = rs.getString("FullName");
                    String email = rs.getString("Email");
                    int phone = rs.getInt("Phone");
                    result = new UserDetailsDTO(userid, role, username, 
                            password, email, fullname, phone);
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
}
