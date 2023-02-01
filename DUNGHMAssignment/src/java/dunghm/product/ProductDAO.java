/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.product;

import dunghm.utils.DBHelper;
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
public class ProductDAO {

    private List<ProductDTO> itemsList;

    public List<ProductDTO> getItemsList() {
        return itemsList;
    }

    public void showProduct(String searchValue)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1. get connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2.sql commands
                String sql = "Select Sku, Name, Description, Quantity, Price, status "
                        + "From Product "
                        + "where Name like ? and status = 1";
                //3.stm create
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. execute querry
                rs = stm.executeQuery();
                //5.process
                while (rs.next()) {
                    //get fields
                    String sku = rs.getString("Sku");
                    String name = rs.getString("Name");
                    String description = rs.getString("Description");
                    int quantity = rs.getInt("Quantity");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("status");
                    //create dto
                    ProductDTO dto = new ProductDTO(sku, name,
                            description, quantity, price, status);
                    //add item to dto
                    if (this.itemsList == null) {
                        this.itemsList = new ArrayList<>();
                    }//end the list no exsited
                    this.itemsList.add(dto);
                }//end traversal
            }//con existed
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

    
    public void showProductManeger(String searchValue)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1. get connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2.sql commands
                String sql = "select Sku , Name, Description, Quantity, Price, status "
                        + "from Product "
                        + "where Name like ?";
                //3.stm create
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. execute querry
                rs = stm.executeQuery();
                //5.process
                while (rs.next()) {
                    //get fields
                    String sku = rs.getString("Sku");
                    String name = rs.getString("Name");
                    String description = rs.getString("Description");
                    int quantity = rs.getInt("Quantity");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("status");
                    //create dto
                    ProductDTO dto = new ProductDTO(sku, name,
                            description, quantity, price, status);
                    //add item to dto
                    if (this.itemsList == null) {
                        this.itemsList = new ArrayList<>();
                    }//end the list no exsited
                    this.itemsList.add(dto);
                }//end traversal
            }//con existed
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

    public boolean updateProductManager(String sku, int quantity, 
            float price, boolean status)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. get connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2.sql commands
                String sql = "Update Product "
                        + "set Quantity = ?, Price = ?, status=? "
                        + "where sku =? ";
                //3.stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setFloat(2, price);
                stm.setBoolean(3, status);
                stm.setString(4, sku);
                //4. execute querry
                int effrows = stm.executeUpdate();
                //5.process                
                if (effrows > 0) {
                    result = true;
                }//end traversal
            }//con existed
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean deleteProductManager(String sku)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. get connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2.sql commands
                String sql = "Delete From Product "
                        + "where sku=? ";
                //3.stm create
                stm = con.prepareStatement(sql);
                stm.setString(1, sku);
                //4. execute querry
                int effrows = stm.executeUpdate();
                //5.process                
                if (effrows > 0) {
                    result = true;
                }//end traversal
            }//con existed
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean createProductManager(/*String sku, String name, String description, 
            int Quantity, float price, boolean status*/ProductDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. get connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2.sql commands
                String sql = "Insert into Product(Sku, Name, Description, Quantity, Price, status) "
                        + "Values (?,?,?,?,?,?)";
                //3.stm create
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getSku());
                stm.setString(2, dto.getName());
                stm.setString(3, dto.getDescription());
                stm.setInt(4, dto.getQuantity());
                stm.setFloat(5, dto.getPrice());
                stm.setBoolean(6, dto.isStatus());
                //4. execute querry
                int effrows = stm.executeUpdate();
                //5.process                
                if (effrows > 0) {
                    result = true;
                }//end traversal
            }//con existed
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    private List<ProductDTO> getProduct;

    public List<ProductDTO> getGetProduct() {
        return getProduct;
    }
    
    public void getProductByID(String id) throws SQLException, NamingException{
        Connection con  = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try{
            //1. get connection
            con = DBHelper.createConnection();
            if(con!=null){
                //2. sql command
                String sql = "Select sku, Name, Description, Quantity, Price, status "
                        + "From Product "
                        + "Where sku = ?";
                //3. create stm
                stm= con.prepareStatement(sql);
                stm.setString(1, id);
                //4. execute
                rs = stm.executeQuery();
                //5. process
                while(rs.next()){
                    String sku = rs.getString("sku");
                    String name = rs.getString("Name");
                    String description = rs.getString("Description");
                    int quantity = rs.getInt("Quantity");
                    float price = rs.getFloat("Price");
                    Boolean status = rs.getBoolean("Status");
                    // create dto
                    ProductDTO dto = new 
                                ProductDTO(sku, name, description, quantity, price, status);
                    //add to to account
                    if(this.getProduct ==null){
                        this.getProduct = new ArrayList<>();
                    }// not have array
                    this.getProduct.add(dto); // add already                                      
                }// add more than 1 record
            }// con existed
        }finally{
            if(rs!=null){
                rs.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }
}
