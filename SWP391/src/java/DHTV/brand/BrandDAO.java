/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.brand;

import DVHT.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author vinht
 */
public class BrandDAO implements Serializable{
    public BrandDTO getInfoBrandByBrandID(int brandID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        BrandDTO result = new BrandDTO();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [BrandName],[Status],[Description] " +
                        "from Brand " + 
                        "where BrandID = ? and Status = 1";
                stm = con.prepareStatement(sql);
                stm.setInt(1, brandID);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                
                if(rs.next()){
                    String brandName = rs.getNString("BrandName");                    
                    String description = rs.getString("Description");                   
                    //create dto
                    BrandDTO dto = new BrandDTO(brandID, brandName, true, description);
                    System.out.println("Get Brand info");
                    System.out.println(dto);
                    result = dto;
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
    private List<BrandDTO> brandList;

    public List<BrandDTO> getBrandList() {
        return brandList;
    }
    public void listBrand()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.brandList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select BrandID,[BrandName],[Status],[Description] " +
                        "from Brand  "  
                        ;
                stm = con.prepareStatement(sql);
              
                //execute query  
                rs = stm.executeQuery();
                //5 process
                
               while(rs.next()){
                    int brandID = rs.getInt("BrandID");                    
                    String brandName = rs.getString("BrandName");                    
                    Boolean Status = rs.getBoolean("Status");                    
                    String description = rs.getString("Description");                   
                    //create dto
                    BrandDTO dto = new BrandDTO(brandID, brandName, Status, description);
                  
                    
                    if (this.brandList == null) {
                        this.brandList = new ArrayList<>();
                    }//end the list no exsited
                    this.brandList.add(dto);
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
    public void listBrandActive()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.brandList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select BrandID,[BrandName],[Status],[Description] " +
                        "from Brand "
                        + "Where Status = 1 "  
                        ;
                stm = con.prepareStatement(sql);
              
                //execute query  
                rs = stm.executeQuery();
                //5 process
                
               while(rs.next()){
                    int brandID = rs.getInt("BrandID");                    
                    String brandName = rs.getString("BrandName");                    
                    Boolean Status = rs.getBoolean("Status");                    
                    String description = rs.getString("Description");                   
                    //create dto
                    BrandDTO dto = new BrandDTO(brandID, brandName, Status, description);
                  
                    
                    if (this.brandList == null) {
                        this.brandList = new ArrayList<>();
                    }//end the list no exsited
                    this.brandList.add(dto);
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
    public int addBrandAdmin(BrandDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int key=0;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
             String sql = "Insert into Brand(BrandName,Status,Description) "
                        + "values (?,?,?) ";
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);                
                
                stm.setString(1, dto.getBrandName());
                stm.setBoolean(2, dto.isStatus());
                stm.setString(3, dto.getDescription());
                
              
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
        return key;
    }
        public boolean deleteBrandAdmin(int BrandID, boolean status)
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
                String sql = "Update [Brand] "
                        + "Set [Status]=?  "
                        + "Where [BrandID] = ? ";

                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setInt(2, BrandID);

                int rows = stm.executeUpdate();

                //5. process result
                if (rows > 0) {
                    result = true;
                }//end the list no exsited
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
