/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.product;

import DVHT.utils.DBHelpers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author vinht
 */
public class ProductDetailDAO {
    public int getQuantity (int productID, int storeID, int sizeID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int quantity = 0;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                 String sql = "SELECT [Quantity] " +
                            "FROM [ProductDetails] " +
                            "where ProductID = ?  and [StoreID] = ? and [SizeID] =?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                stm.setInt(2, storeID);
                stm.setInt(3, sizeID);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                if(rs.next()){
                    quantity= rs.getInt("Quantity");
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
            return quantity;
        }
    }
    
    
    public void minusProduct (int productID, int storeID, int sizeID , int quantity)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int oldQuantity = getQuantity(productID, storeID, sizeID);
        int newQuantity = oldQuantity - quantity;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                 String sql = "UPDATE [ProductDetails] " +
                            "SET [Quantity] = ? " +
                            "where ProductID = ?  and [StoreID] = ? and [SizeID] =?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, newQuantity);
                stm.setInt(2, productID);
                stm.setInt(3, storeID);
                stm.setInt(4, sizeID);
                //execute query  
                int rows = stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
