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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author mthin
 */
public class ProductDAO {

    private List<ProductDTO> itemsList;

    public List<ProductDTO> getItemsList() {
        return itemsList;
    }

    public void showProduct()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                 String sql = "select [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description] "+
                                "from  Product " +
                                "where Status =1";
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while(rs.next()){
                    int productID=rs.getInt("ProductID");
                    String productName= rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");                    
                    float price= rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    //create dto
                    ProductDTO dto = new ProductDTO(productID, productName, brandID, categoryID, price, status, description);
                    //System.out.println(dto);
                    //add item to dto
                    if (this.itemsList == null) {
                        this.itemsList = new ArrayList<>();
                    }//end the list no exsited
                    this.itemsList.add(dto);
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
   
    
    
    
    public ProductDTO getInfoProductByProductID(int productID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        ProductDTO result = new ProductDTO();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                 String sql = "select [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description]   "+
                                "from  Product " +
                                "where ProductID = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                if(rs.next()){
                    
                    String productName= rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");                    
                    float price= rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    //create dto
                    result = new ProductDTO(productID, productName, brandID, categoryID, price, status, description);
                    System.out.println(result);
                   
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
            return result;
        }
    }
}
