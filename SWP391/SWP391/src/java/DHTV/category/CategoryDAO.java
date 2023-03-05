/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.category;

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
public class CategoryDAO {

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
                String sql = "select [CategoryID] ,[CategoryName] ,[Gender] ,[Description] "
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
                    //create dto
                    CategoryDTO dto = new CategoryDTO(categoryID, categoryName, gender, description);
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

}
