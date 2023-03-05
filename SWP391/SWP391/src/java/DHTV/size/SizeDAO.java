/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.size;

import DHTV.category.CategoryDTO;
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
public class SizeDAO {
    private List<SizeDTO> sizeList;

    public List<SizeDTO> getSizeList() {
        return sizeList;
    } 
    
    public void showSizeList()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                 String sql = "select [SizeID], [NameSize] "+
                                "from  Size ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while(rs.next()){
                    int SizeID=rs.getInt("SizeID");
                    String NameSize= rs.getString("NameSize");                          
                    //create dto
                    SizeDTO dto = new SizeDTO(SizeID, NameSize);
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
    
    public SizeDTO getInfoSizeBySizeID(int sizeID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        SizeDTO result = new SizeDTO();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                 String sql = "select [NameSize] "+
                                "from  Size " +
                                "where SizeID = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, sizeID);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                if(rs.next()){
                    
                    String nameSize= rs.getString("NameSize");                   
                    //create dto
                    result = new SizeDTO(sizeID, nameSize);                   
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
