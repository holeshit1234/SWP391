/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.order;

import DVHT.utils.DBHelpers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import javax.naming.NamingException;

/**
 *
 * @author vinht
 */
public class OrderDetailDAO {
    public static boolean addOrder(OrderDetailDTO dto)
            throws NamingException, SQLException, ParseException {
        //set current date
        long millis=System.currentTimeMillis();   
        java.sql.Date dateCurrent=new java.sql.Date(millis);   
        java.sql.Date sqlDate = new java.sql.Date(dateCurrent.getTime());   
        
        int key = 0;
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Insert into [OrderDetail] ([OrderID],[ProductID],[SizeID],[Quantity],[Price])"
                        + "values (?,?,?,?,?) ";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getOrderID());
                stm.setInt(2, dto.getProductID());
                stm.setInt(3, dto.getSizeID());
                stm.setInt(4, dto.getQuantity());
                stm.setDouble(5, dto.getPrice());            
                //4.execute query
                int rows = stm.executeUpdate();
                if(rows > 0) {
                    result = true;
                }
            } //end con is availible
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            return result;
        }
       
    }
}
