/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.bill;

import DHTV.order.OrderDTO;
import DHTV.order.OrderDetailDTO;
import DVHT.utils.DBHelpers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author mthin
 */
public class BillDetailDAO {

    private List<BillDetailDTO> orderList;

    public List<BillDetailDTO> getOrderList() {
        return orderList;
    }
    
    public static int addBillDetail  (int key, OrderDetailDTO dto)
            throws NamingException, SQLException, ParseException { 
     
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Insert into [BillDetail] ([BillID] ,[ProductID] ,[sizeID] ,[Quantity] ,[Price]) "
               
                        + "values (?,?,?,?,?) ";
                //3. Create Statement
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setInt(1, key);
                stm.setInt(2, dto.getProductID());
                stm.setInt(3, dto.getSizeID());
                stm.setInt(4, dto.getQuantity());
                stm.setDouble(5, dto.getPrice());
                
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
