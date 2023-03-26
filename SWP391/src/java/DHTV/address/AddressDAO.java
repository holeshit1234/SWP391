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
                        + " [Address].Street, [Address].Ward, [Address].district "
                        + "from UserDetails inner join [Address]  "
                        + "on UserDetails.UserID = [Address].UserId "
                        + "where [Address].UserId = ? and [Address].Status = 1 ";

                //3. create stm
                stm = con.prepareStatement(sql);
                stm.setInt(1, userid);
                //4. execute query

                rs = stm.executeQuery();
                //5. process result

                while (rs.next()) {
                    int addressid = rs.getInt("AddressID");
                    String province = rs.getString("Province");
                    //System.out.println("province = "+province);   // print to check
                    String street = rs.getString("Street");
                    String ward = rs.getString("Ward");
                    String district = rs.getString("district");
                    AddressDTO result = new AddressDTO(addressid, userid, province, ward, 
                            street, district, true);
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

    public AddressDTO getAddressDetail(int userid, int addid)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        AddressDTO result = null;

        try {
            //1. get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. sql command
                String sql = "select  [Address].Province, "
                        + " [Address].Street, [Address].Ward, [Address].district, "
                        + " [Address].Status "
                        + "from UserDetails inner join [Address]  "
                        + "on UserDetails.UserID = [Address].UserId  "
                        + "where [Address].UserId = ? and [Address].AddressID = ? ";

                //3. create stm
                stm = con.prepareStatement(sql);
                stm.setInt(1, userid);
                stm.setInt(2, addid);
                //4. execute query

                rs = stm.executeQuery();
                //5. process result

                while (rs.next()) {
      
                    String province = rs.getString("Province");
                    String street = rs.getString("Street");
                    String ward = rs.getString("Ward");
             
                    String district = rs.getString("district");
                    boolean status = rs.getBoolean("Status");
                    result = new AddressDTO(addid, userid, province, ward, street, district, status);
                                   
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
        return result;
    }
    
    public boolean updateAddress(int addid, int userid, String street,
            String province, String district, String ward)
            throws NamingException, SQLException {

        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
       
        try {
            //1. get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. sql command
                String sql = "Update Address "
                        + "set Street=?, Province=?, district=? ,Ward =? "
                        + "where UserId = ? and AddressID= ? ";

                //3. create stm
                stm = con.prepareStatement(sql);
                stm.setString(1, street);
                stm.setString(2, province);
                stm.setString(3, district);
                stm.setString(4, ward);
                stm.setInt(5, userid);
                stm.setInt(6, addid);

                //4. execute query
                int rowseff = stm.executeUpdate();
                //5. process result
                if (rowseff > 0) {
                    result = true;
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
        return result;
    }
    
    public boolean deleteAddress(int addid)
            throws NamingException, SQLException {

        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
       
        try {
            //1. get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. sql command
                String sql = "Update Address "
                        + "Set Status = 0 "
                        + "where AddressID = ? ";

                //3. create stm
                stm = con.prepareStatement(sql);
          
                stm.setInt(1, addid);

                //4. execute query
                int rowseff = stm.executeUpdate();
                //5. process result
                if (rowseff > 0) {
                    result = true;
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
        return result;
    }
    public boolean updateAddressGG(int userid, String street,
            String province, String district, String ward)
            throws NamingException, SQLException {

        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
       
        try {
            //1. get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. sql command
                String sql = "Update Address "
                        + "set Street=?, Province=?, district=? ,Ward =? "
                        + "where UserId = ? ";

                //3. create stm
                stm = con.prepareStatement(sql);
                stm.setString(1, street);
                stm.setString(2, province);
                stm.setString(3, district);
                stm.setString(4, ward);
                stm.setInt(5, userid);

                //4. execute query
                int rowseff = stm.executeUpdate();
                //5. process result
                if (rowseff > 0) {
                    result = true;
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
        return result;
    }

    public static boolean addAddressGooogle ( AddressDTO addr ,int key) 
            throws SQLException, NamingException{
         Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int rows = 0;
        //AddressDTO result = null;
        boolean result = false;
        try{
            //1. get connection 
            con = DBHelpers.getConnection();
            if(con != null){
                //2.sql commnands 
                String sql = "insert into Address (UserId, Province, Ward, "
                        + "Street, district, Status ) "
                        + "values(?,?,?,?,?,? ) ";
                stm= con.prepareStatement(sql);
                stm.setInt(1, key);
                stm.setString(2, addr.getProvice());
                stm.setString(3, addr.getWard());
                stm.setString(4, addr.getStreet());
                stm.setString(5, addr.getDistrict());                
                stm.setBoolean(6, addr.isStatus());                
                rows = stm.executeUpdate();
                
                if(rows>0){
                    result = true;
                }               
            }
        }finally{
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }

        }
        
        return result; 
    }
    
    
    public static boolean addAddress(AddressDTO dto)
            throws NamingException, SQLException {

        int key = 0;
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
  
        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "insert into [Address] (UserId, Province, Ward, Street, District, Status) "
                        + "values (?,?,?,?,?,?) ";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getUserID());
                stm.setString(2, dto.getProvice());
                stm.setString(3, dto.getWard());
                stm.setString(4, dto.getStreet());
                stm.setString(5, dto.getDistrict());
                stm.setBoolean(6, dto.isStatus());
                //4.execute query
                int rows = stm.executeUpdate();
                //5. process result
                
                if (rows > 0) {
                    result = true;
                }
            } //end con is availible
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
    
    public boolean addNewAddress(int id, String province, String ward, String street,
            String district, boolean status)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "insert into [Address] (UserId, Province, Ward, Street, District, Status) "
                        + "values (?,?,?,?,?,?) ";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setString(2, province);
                stm.setString(3, ward);
                stm.setString(4, street);
                stm.setString(5, district);
                stm.setBoolean(6,status );
                //4.execute query
                int rows = stm.executeUpdate();
                //5. process result

                if (rows > 0) {
                    result = true;
                }
            } //end con is availible
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
    
    private List<AddressDTO> infoListS;

    public List<AddressDTO> getInfoListS() {
        return infoListS;
    }

    public void getAddressByAdmin(int userid)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
      
        try {
            //1. get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. sql command
                String sql = "select [Address].UserId, [Address].AddressID, [Address].Province, "
                        + " [Address].Street, [Address].Ward, [Address].district, "
                        + "  [Address].Status "
                        + "from UserDetails inner join [Address]  "
                        + "on UserDetails.UserID = [Address].UserId  "
                        + "where [Address].UserId = ? ";

                //3. create stm
                stm = con.prepareStatement(sql);
                stm.setInt(1, userid);

                //4. execute query
                rs = stm.executeQuery();
                //5. process result

                while (rs.next()) {
                    int addid = rs.getInt("AddressID");
                    String province = rs.getString("Province");
                    String street = rs.getString("Street");
                    String ward = rs.getString("Ward");
                    String district = rs.getString("district");
                    boolean status = rs.getBoolean("Status");
                     AddressDTO dto = new AddressDTO(addid, userid, province, ward, street, district, status);
                    if (this.infoListS == null) {
                        this.infoListS = new ArrayList<>();
                    }//end the list no exsited
                    this.infoListS.add(dto);
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
    public boolean updateAddressAdminManager(int userid, String street,
            String province, String district, String ward)
            throws NamingException, SQLException {

        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. sql command
                String sql = "Update Address "
                        + "set Street=?, Province=?, district=? ,Ward =? "
                        + "where UserId = ? ";

                //3. create stm
                stm = con.prepareStatement(sql);
                stm.setString(1, street);
                stm.setString(2, province);
                stm.setString(3, district);
                stm.setString(4, ward);
                stm.setInt(5, userid);

                //4. execute query
                int rowseff = stm.executeUpdate();
                //5. process result
                if (rowseff > 0) {
                    result = true;
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
        return result;
    }

}
