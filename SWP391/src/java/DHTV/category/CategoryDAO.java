/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.category;

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
public class CategoryDAO implements Serializable{

    public CategoryDTO getInfoCategoryByCategoryID(int categoryID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        CategoryDTO result = new CategoryDTO();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [CategoryID] ,[CategoryName] ,[Gender] ,[Description],Status "
                        + "from Category "
                        + "where CategoryID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, categoryID);
                //execute query  
                rs = stm.executeQuery();
                //5 process

                if (rs.next()) {
                    String categoryName = rs.getNString("CategoryName");
                    String gender = rs.getString("Gender");
                    String description = rs.getNString("Description");
                    boolean Status = rs.getBoolean("Status");
                    //create dto
                    CategoryDTO dto = new CategoryDTO(categoryID, categoryName, gender, description, Status);
                    System.out.println("Get Category info");
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
    private List<CategoryDTO> cateList;

    public List<CategoryDTO> getCateList() {
        return cateList;
    }

    public void showListCategory()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        CategoryDTO result = new CategoryDTO();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [CategoryID] ,[CategoryName] ,[Gender] ,[Description],Status "
                        + "from Category ";
                stm = con.prepareStatement(sql);

                //execute query  
                rs = stm.executeQuery();
                //5 process

                while (rs.next()) {
                    int categoryID = rs.getInt("CategoryID");
                    String categoryName = rs.getNString("CategoryName");
                    String gender = rs.getString("Gender");
                    String description = rs.getNString("Description");
                    boolean Status = rs.getBoolean("Status");
                    //create dto
                    CategoryDTO dto = new CategoryDTO(categoryID, categoryName, gender, description, Status);

                    if (this.cateList == null) {
                        this.cateList = new ArrayList<>();
                    }//end the list no exsited
                    this.cateList.add(dto);
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

    public int addCategoryAdmin(CategoryDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int key = 0;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "Insert into Category(CategoryName,Gender,Description, status) "
                        + "values (?,?,?,1) ";
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                stm.setString(1, dto.getCategoryName());
                stm.setString(2, dto.getGender());
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

    public boolean deleteCategoryAdmin(int cateID, boolean status)
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
                String sql = "Update [Category] "
                        + "Set [Status]=?  "
                        + "Where [CategoryID] = ? ";

                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setInt(2, cateID);

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
