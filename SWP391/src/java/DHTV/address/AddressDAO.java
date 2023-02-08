/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.address;

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
 * @author User
 */
public class AddressDAO implements Serializable {
    
    
    private List<AddressDTO> infoList;

    public List<AddressDTO> getInfoList() {
        return infoList;
    }
         
    
    public void getAddress(int userid)
            throws NamingException, SQLException {

        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. sql command
                String sql = "select [Address].AddressID, [Address].Province, "
                        + " [Address].Street, [Address].Ward, "
                        + " [Address].Notice "
                        + "from UserDetails inner join [Address]  "
                        + "on UserDetails.UserID = [Address].UserId "
                        + "where [Address].UserId = ? ";

                //3. create stm
                stm = con.prepareStatement(sql);
                stm.setInt(1, userid);
                //4. execute query
                
                rs = stm.executeQuery();
                //5. process result
                
                while(rs.next()){
                    int addressid = rs.getInt("AddressID");
                    String province = rs.getString("Province");
                    //System.out.println("province = "+province);   // print to check
                    String street = rs.getString("Street");
                    String ward = rs.getString("Ward");                   
                    String notice = rs.getString("Notice");
                    AddressDTO result = new AddressDTO(addressid, userid, province, ward, street, notice);  
                    //add item to dto
                    if (this.infoList == null) {
                        this.infoList = new ArrayList<>();
                    }//end the list no exsited
                    this.infoList.add(result);
                    /*
                    System.out.println(result.getStreet()+" = street");
                    System.out.println(result.getAddressID()+" = add");
                    System.out.println(result.getNotice()+" = notice");
                    System.out.println(result.getWard()+" = ward");
                    System.out.println(result.getUserID()+" = userID");
                    System.out.println(result.getProvice()+" = provice");
                    */
                }
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

}
