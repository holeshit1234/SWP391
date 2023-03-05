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
                 String sql = "select [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description], Image "+
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
                    String image = rs.getString("Image");
                    //create dto
                    ProductDTO dto = new ProductDTO(productID, productName, brandID, categoryID, price, status, description,image);
                    System.out.println(dto);
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
       
 
    public int getTotalProduct()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description],[Image] "
                        + "from  Product "
                        + "where Status =1";
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    //create dto
                    ProductDTO dto = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, image);
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
        return this.itemsList.size();
    }

    public List<ProductDTO> pagingProduct(int index, int recordsPerPage)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();

        try {
            con = DBHelpers.getConnection();
            String sql = "SELECT [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description], [Image] "
                    + "FROM Product\n"
                    + "where Status =1 "
                    + "ORDER BY ProductID\n"
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            stm = con.prepareStatement(sql);

            stm.setInt(1, (index - 1) * recordsPerPage);
            stm.setInt(2, recordsPerPage);
            rs = stm.executeQuery();

            while (rs.next()) {
                int productid = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                int brandid = rs.getInt("BrandID");
                int cate = rs.getInt("CategoryID");
                float price = rs.getFloat("Price");
                boolean status = rs.getBoolean("Status");
                String des = rs.getString("Description");
                String image = rs.getString("Image");

                ProductDTO dto = new ProductDTO(productid, name, brandid, cate, price, status, des, image);
                list.add(dto);
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
        return list;
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
                 String sql = "select [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description], Image  "+
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
                    String image = rs.getString("Image");
                    //create dto
                    result = new ProductDTO(productID, productName, brandID, categoryID, price, status, description,image);
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
    
    
    public void searchProduct( String searchValue)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                 String sql = "select [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description] ,[Image]"+
                                "from  Product " +
                                "where [ProductName] Like  ?  ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
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
                    String img = rs.getString("Image");
                    //create dto
                    ProductDTO dto = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, img);
                    System.out.println("kt dto có show ko :"+dto);
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
    
}
