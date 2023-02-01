/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.registration;

import DVHT.utils.DBHelpers;
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
            con = DBHelpers.getConnection();
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
}
