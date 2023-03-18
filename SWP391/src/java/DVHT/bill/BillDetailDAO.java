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
import java.util.ArrayList;
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
        
    
      private List<BillDetailDTO> billDetailList;

    public List<BillDetailDTO> getBillDetailList() {
        return billDetailList;
    }

      
      
      
    public BillDetailDTO showListBillDetail(int BillID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        BillDetailDTO result = new BillDetailDTO();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [BillDetailID] ,[BillID] ,[ProductID] ,[SizeID],Quantity,Price "
                        + "from BillDetail "
                        + "where BillID= ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, BillID);
                //execute query  
                rs = stm.executeQuery();

                //5 process
                while (rs.next()) {
//                    int BillID = rs.getInt("BillID");
                    int BillDetailID = rs.getInt("BillDetailID");
                    int ProductID = rs.getInt("ProductID");
                    int SizeID = rs.getInt("SizeID");
                    int Quantity = rs.getInt("Quantity");
                    Double Price = rs.getDouble("Price");

                    //create dto
                    BillDetailDTO dto = new BillDetailDTO(BillDetailID, BillID, ProductID, SizeID, Quantity, SizeID);
                    
                    result = dto;
                    if (this.billDetailList == null) {
                        this.billDetailList = new ArrayList<>();
                    }//end the list no exsited
                    this.billDetailList.add(dto);
                }
            }
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
