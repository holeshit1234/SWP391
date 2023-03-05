/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.cart;

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
 * @author vinht
 */
public class CartDAO {

    public boolean saveCart(CartDTO cart) throws NamingException, SQLException {
        int key = 0;
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                //create sql
                String sql = "INSERT INTO Cart (ProductID,SizeID,StoreID,UserID,Quantity,Status) "
                        + "values (?,?,?,?,?,? ) ";
                //create stm
                stm = con.prepareStatement(sql);
                stm.setInt(1, cart.getProductID());
                stm.setInt(2, cart.getSizeID());
                stm.setInt(3, cart.getStoreID());
                stm.setInt(4, cart.getUserID());
                stm.setInt(5, cart.getQuantity());
                stm.setBoolean(6, true);
                //execute
                int rows = stm.executeUpdate();
                if (rows > 0) {
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
    private List<CartDTO> cartList;

    public List<CartDTO> getCartList() {
        return cartList;
    }

    public void getCartByUserID(int userID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                 String sql = "select [CartID],[ProductID],[SizeID],[StoreID],[UserID],[Quantity],[Status] " +
                                "from  Cart " +
                                "where Status =1 and UserID = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while(rs.next()){
                    int cartID = rs.getInt("CartID");
                    int productID= rs.getInt("ProductID");
                    int sizeID = rs.getInt("SizeID");
                    int storeID = rs.getInt("StoreID");                 
                    int quantity = rs.getInt("Quantity");
                    boolean status = rs.getBoolean("Status");
       
                    //create dto
                    CartDTO dto = new CartDTO(cartID, productID, sizeID, storeID, userID, quantity, status);
                    System.out.println(dto);
                    //add item to dto
                    if (this.cartList == null) {
                        this.cartList = new ArrayList<>();
                    }//end the list no exsited
                    this.cartList.add(dto);
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
    }
}
