/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.product;

import DHTV.size.SizeDTO;
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
    private List<ProductDetailDTO> detailList;

    public List<ProductDetailDTO> getDetailList() {
        return detailList;
    }

    public ProductDetailDTO getSizeIdById(int productID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
         this.detailList = new ArrayList<>();
        ProductDetailDTO result = new ProductDetailDTO();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "SELECT ProductID, StoreID, SizeID,Quantity "
                        + "FROM [ProductDetails] "
                        + "where ProductID = ?  ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                //execute query  
                rs = stm.executeQuery();
                //5 process

                while (rs.next()) {
                    int ProductID = rs.getInt("ProductID");
                    int StoreID = rs.getInt("ProductID");
                    int sizeID = rs.getInt("SizeID");
                    int Quantity = rs.getInt("Quantity");

                    //create dto
                    ProductDetailDTO dto = new ProductDetailDTO(ProductID, StoreID, sizeID, Quantity);
                    System.out.println("Check dto" + dto);
                    System.out.println("============================================================");
                    result = dto;
                    if (this.detailList == null) {
                        this.detailList = new ArrayList<>();
                    }//end the list no exsited
                    this.detailList.add(dto);
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

    public boolean addProductDetailAdmin(int key, int size, int quantity, int store)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "Insert into ProductDetails  (ProductID,[SizeID],[Quantity],StoreID) "
                        + "VALUES (?, ?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, key);
                stm.setInt(2, size);
                stm.setInt(3, quantity);
                stm.setInt(4, store);

                int rows = stm.executeUpdate();

                if (rows > 0) {
                    result = true;
                    System.out.println("OKe jngon đã add xong");
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

    public boolean updateQuantityProduct(int key, int size, int quantity, int store)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "UPDATE ProductDetails "
                        + "SET "
                        + "[Quantity] = ? "
                        + "WHERE ProductID = ? AND StoreID = ? AND SizeID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setInt(2, key);
                stm.setInt(3, store);
                stm.setInt(4, size);

                int rows = stm.executeUpdate();

                if (rows > 0) {
                    result = true;
                    System.out.println("OKe jngon đã add xong");
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
    
    private List<SizeDTO> sizeList;

    public List<SizeDTO> getSizeList() {
        return sizeList;
    } 
    
    public void showSizeListByProductID(int productID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.sizeList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                 String sql = "select [SizeID] "+
                                "from [ProductDetails] "+
                                "where quantity>0 and [ProductID] =?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while(rs.next()){
                    int SizeID=rs.getInt("SizeID");
                    //create dto
                    SizeDTO dto = new SizeDTO(SizeID, null);
                    System.out.println(dto);
                    //add item to dto
                    if (this.sizeList == null) {
                        this.sizeList = new ArrayList<>();
                    }//end the list no exsited
                    this.sizeList.add(dto);
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
