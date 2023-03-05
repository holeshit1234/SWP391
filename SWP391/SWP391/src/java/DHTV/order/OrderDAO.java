/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.order;

import DVHT.utils.DBHelpers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import javax.naming.NamingException;

/**
 *
 * @author vinht
 */
public class OrderDAO {
    public static int addOrder(OrderDTO dto)
            throws NamingException, SQLException, ParseException {
        //set current date
        long millis=System.currentTimeMillis();   
        java.sql.Date dateCurrent=new java.sql.Date(millis);   
        java.sql.Date sqlDate = new java.sql.Date(dateCurrent.getTime());   
        
        int key = 0;
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        ResultSet rs = null;

        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Insert into [Order] ([UserID] ,[PaymentID] ,[AddressID] ,[Date] ,[TotalPrice]"
                        + " ,[Shippingfee] ,[ApprovalStatus] ,[PaymentStatus]) "
                        + "values (?,?,?,?,?,?,?,?) ";
                //3. Create Statement
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setInt(1, dto.getUserID());
                stm.setInt(2, dto.getPaymentID());
                stm.setInt(3, dto.getAddressID());
                stm.setDate(4, sqlDate);
                stm.setDouble(5, dto.getTotalPrice());
                stm.setDouble(6, dto.getShippingFee());
                stm.setBoolean(7, dto.isApprovalStatus());
                stm.setBoolean(8, dto.isPaymentStatus());
            
                //4.execute query
                int rows = stm.executeUpdate();
                rs = stm.getGeneratedKeys();
                //5. process result
                if (rs.next()) {
                    // result = true;
                    key = rs.getInt(1);
                }
                while (rows > 0) {
                    return key;
                }
            } //end con is availible
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return key;
    }
}
