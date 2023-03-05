/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.brand;

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
public class BrandDAO {
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
    
}
