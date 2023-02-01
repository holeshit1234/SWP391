/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.orderdetails;

import dunghm.cart.CartObj;
import dunghm.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author dunghm
 */
public class OrderDetailsDAO implements Serializable {

    public boolean addToDetailOrders(/*Map<String, Integer> items*/ CartObj cart, int key) throws SQLException,
             NamingException {
        Connection con = null;
        PreparedStatement stm1 = null;
        PreparedStatement stm2 = null;
        ResultSet rs = null;
        int rows = 0;
        boolean result = false;

        try {
            //1. get connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. sql command
                String sql1 = "select Sku, Price " 
                       +"from Product " 
                       +"where Sku=?";
                //3. create statement
                stm1 = con.prepareStatement(sql1);
                Map<String, Integer> items = cart.getItems();
                for(String  sku : items.keySet()){
                    stm1.setString(1, sku);
                    rs = stm1.executeQuery();
                    if(rs.next()){
                        float price = rs.getFloat("Price");
                        float total = price * items.get(sku);
                        String sql2 = "Insert into OrderDetails("
                                + " OrderID, Sku, Quantity, Price, Total"
                                + ") "
                                + "Values("
                                + " ?, ?, ?, ?, ?"
                                + ") ";
                        stm2 = con.prepareStatement(sql2);
                        stm2.setInt(1, key);
                        System.out.println(key);
                        stm2.setString(2, sku);
                        System.out.println(sku);
                        stm2.setInt(3, items.get(sku));
                        stm2.setFloat(4, price);
                        stm2.setFloat(5, total);
                        rows = stm2.executeUpdate();
                        System.out.println(key);
                    }// if insert cart to 
                } // finish traversal 
                if(rows> 0){
                    result = true;
                }
            }//con existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm2 != null) {
                stm2.close();
            }
            if (stm1 != null) {
                stm1.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
