/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.order;

import dunghm.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.NamingException;

/**
 *
 * @author dunghm
 */
public class OrderDAO implements Serializable {
    
    public int addToOrders(String username, String total) 
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        long milius = System.currentTimeMillis();
        Date date = new Date(milius);
        float totalBill = Float.parseFloat(total);
        int key = 0;
        
        try{
            //1 call DAo
            con = DBHelper.createConnection();
            if(con!= null){
                //sql command
                String sql = "Insert into Orders("
                        + " DateBuy, username, Total"
                        + ") "
                        + "Values("
                        + " ?, ?, ?"
                        + ")";
                //create stm
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setDate(1, date);
                stm.setString(2, username);
                stm.setFloat(3, totalBill);
                //4. execute 
                int rows = stm.executeUpdate();
                rs = stm.getGeneratedKeys();
                if(rs.next()){
                    key = rs.getInt(1);

                }
                //5. process

                while(rows > 0){
                    return key;                   
                }
            }
        }finally{
            if(rs!=null){
               rs.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(con!=null){
                con.close();
            }
        }
        return key;
    }   
}
