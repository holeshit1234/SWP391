/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.userdetails;

import DVHT.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author User
 */
public class UserDetailsDAO implements Serializable {

    public UserDetailsDTO checkLogin(String username, String password)
            throws NamingException, SQLException {

        UserDetailsDTO result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Select UserID, FullName, RoleID, Email, Phone, DOB, Gender, Picture "
                        + "From UserDetails "
                        + "Where UserName = ? And PassWord = ? and Status = 1";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.execute query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    int userid = rs.getInt("UserID");
                    int role = rs.getInt("RoleID");
                    String fullname = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String phone = rs.getString("Phone");
                    String picture = rs.getString("Picture");

                    Date DOB = rs.getDate("DOB");
                    java.sql.Date sqlDate = new java.sql.Date(DOB.getTime());

                    String gender = rs.getString("Gender");
                    result = new UserDetailsDTO(userid, role, username, password,
                            email, fullname, phone, sqlDate, gender,picture);
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
    
    public static Boolean usernameExist(String username)
    throws NamingException, SQLException{
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Boolean result = false;
           try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Select UserID, PassWord, FullName, RoleID, Email, Phone "
                        + "From UserDetails "
                        + "Where UserName = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);              
                //4.execute query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    result = true;
                }
                else
                    result = false;
            } //end con is availible
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
    public static Boolean emailExist(String email)
    throws NamingException, SQLException{
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Boolean result = false;
           try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Select UserID, PassWord, FullName, RoleID, Email, Phone "
                        + "From UserDetails "
                        + "Where Email = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);              
                //4.execute query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    result = true;
                }
                else
                    result = false;
            } //end con is availible
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
    
    public static int addUser(UserDetailsDTO user)
            throws NamingException, SQLException, ParseException {

        int key = 0;
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        ResultSet rs = null;

        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "insert into UserDetails ( RoleID, UserName, [PassWord], Email, FullName, Phone, DOB, Gender, Picture, [Status]) "
                        + "values (?,?,?,?,?,?,?,?,?,?) ";
                //3. Create Statement
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setInt(1, user.getRoleID());
                stm.setString(2, user.getUserName());
                stm.setString(3, user.getPassWord());
                stm.setString(4, user.getEmail());
                stm.setString(5, user.getFullName());
                stm.setString(6, user.getPhone());
                if (user.getDOB() != null) {
                    java.sql.Date sqlDate = new java.sql.Date(user.getDOB().getTime());
                    stm.setDate(7, sqlDate);
                } else {
                    String date = "01-01-1999";
                    DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
                    Date defaultDate = df.parse(date);
                    java.sql.Date sqlDate = new java.sql.Date(defaultDate.getTime());
                    stm.setDate(7, sqlDate);
                }
                stm.setString(8, user.getGender());
                stm.setString(9, user.getPicture());
                stm.setBoolean(10, user.isStatus());
                //4.execute queryF
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
            } //end con is availible
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return key;
    }

    public boolean updateProfile(int userid, String fullName, 
            String phone, Date DOB, String password)
            throws NamingException, SQLException, ParseException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //get connection
            con = DBHelpers.getConnection();
            //sql commmands
            String sql = "Update UserDetails "
                    + "Set  FullName =? , Phone=?, DOB=?, PassWord=? "
                    + "Where UserID = ? ";

            //create statement
            stm = con.prepareStatement(sql);
           // stm.setString(1, email);
            stm.setString(1, fullName);
            stm.setString(2, phone);
            if (DOB != null) {
                java.sql.Date sqlDate = new java.sql.Date(DOB.getTime());
                stm.setDate(3, sqlDate);
            } else {
                String date = "1-1-1999";
                DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
                Date defaultDate = df.parse(date);
                java.sql.Date sqlDate = new java.sql.Date(DOB.getTime());
                stm.setDate(3, sqlDate);
            }
            stm.setString(4, password);
            stm.setInt(5, userid);
            //execute querry
            int rows = stm.executeUpdate();
            //process result
            if (rows > 0) {
                result = true;
            }
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

    public UserDetailsDTO getInfoUser(int userid)
            throws NamingException, SQLException {

        UserDetailsDTO result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //Date DOB=null;

        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Select UserID, UserName ,PassWord, FullName, "
                        + "RoleID, Email, Phone, DOB, Gender , Picture "
                        + "From UserDetails "
                        + "Where UserID = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, userid);
                //4.execute query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    String username = rs.getString("UserName");
                    String password = rs.getString("PassWord");
                    System.out.println(password);
                    int role = rs.getInt("RoleID");
                    String fullname = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String phone = rs.getString("Phone");
                    Date DOB = rs.getDate("DOB");
                    java.sql.Date sqlDate = new java.sql.Date(DOB.getTime());
                    String gender = rs.getString("Gender");
                    String picture = rs.getString("Picture");
                    result = new UserDetailsDTO(userid, role, username,
                            password, email, fullname, phone, sqlDate, gender,picture);
                }
            } //end con is availible
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

    public static UserDetailsDTO getUser(String email)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserDetailsDTO result = null;

        try {
            //1 get connecttion
            con = DBHelpers.getConnection();

            if (con != null) {
                //2 sql commands
                String sql = "Select UserID, UserName, PassWord, FullName, "
                        + "RoleID, Phone, DOB, Gender, Picture "
                        + "From UserDetails "
                        + "Where Email =? ";

                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);

                //4.execute query
                rs = stm.executeQuery();
                if (rs.next()) {
                    int userid = rs.getInt("UserID");
                    String password = rs.getString("PassWord");
                    int role = rs.getInt("RoleID");
                    String fullname = rs.getString("FullName");
                    String username = rs.getString("UserName");
                    String phone = rs.getString("Phone");
                    Date DOB = rs.getDate("DOB");
                    java.sql.Date sqlDate = new java.sql.Date(DOB.getTime());
                    String gender = rs.getString("Gender");
                    String picture = rs.getString("Picture");
                    result = new UserDetailsDTO(userid, role, username,
                            password, email, fullname, phone, sqlDate, gender, picture);
                }
            } //end con is availible   
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

    public UserDetailsDTO findEmail(String email)
            throws SQLException, /*ClassNotFoundException*/ NamingException {;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserDetailsDTO result = null;
        
        try {
            //1 get connecttion
            con = DBHelpers.getConnection();

            if (con != null) {
                //2 sql commands
                String sql = "Select UserID, UserName, PassWord, FullName, "
                        + "RoleID, Phone, DOB, Gender, Picture "
                        + "From UserDetails "
                        + "Where Email =? ";

                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);

                //4.execute query
                rs = stm.executeQuery();
                if (rs.next()) {
                    int userid = rs.getInt("UserID");
                    String password = rs.getString("PassWord");
                    int role = rs.getInt("RoleID");
                    String fullname = rs.getString("FullName");
                    String username = rs.getString("UserName");
                    String phone = rs.getString("Phone");
                    Date DOB = rs.getDate("DOB");
                    java.sql.Date sqlDate = new java.sql.Date(DOB.getTime());
                    String gender = rs.getString("Gender");
                    String picture = rs.getString("Picture");
                    result = new UserDetailsDTO(userid, role, username,
                            password, email, fullname, phone, sqlDate, gender,picture);
                }
            } //end con is availible   
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
    public boolean updatePassword(String email, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //Connect DB
            con = DBHelpers.getConnection();
            //Create SQL String
            String sql = "Update UserDetails "
                    + "Set PassWord = ? "
                    + "Where Email = ? ";
            //Create statement
            stm = con.prepareStatement(sql);
            stm.setString(1, password);
            stm.setString(2, email);
            //Execute query
            int effectedRows = stm.executeUpdate();
            //Process result
            if (effectedRows > 0) {
                result = true;
            }
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
    
    public boolean updateGgAc(int userid, String fullName, String phone, Date DOB, String gender)
            throws NamingException, SQLException, ParseException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //get connection
            con = DBHelpers.getConnection();
            //sql commmands
            String sql = "Update UserDetails "
                    + "Set    Phone=?, DOB=?, Gender=? "
                    + "Where UserID = ? ";

            //create statement
            stm = con.prepareStatement(sql);
            stm.setString(1, phone);
            if (DOB != null) {
                java.sql.Date sqlDate = new java.sql.Date(DOB.getTime());
                stm.setDate(2, sqlDate);
            } else {
                String date = "1-1-1999";
                DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
                Date defaultDate = df.parse(date);
                java.sql.Date sqlDate = new java.sql.Date(DOB.getTime());
                stm.setDate(2, sqlDate);
            }
            stm.setString(3, gender);
            stm.setInt(4, userid);
            //execute querry
            int rows = stm.executeUpdate();
            //process result
            if (rows > 0) {
                result = true;
            }
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
    
     public boolean UpdateImg(int id, String imaName) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
       boolean result = false;
        ResultSet rs = null;
       
       try{
           con = DBHelpers.getConnection();
           if(con!= null){
               String sql = "Update UserDetails "
                       + "set Picture=? "
                       + "where UserID = ? ";
               
               stm = con.prepareStatement(sql);
               stm.setString(1, imaName);
               stm.setInt(2, id);
               
               int rows = stm.executeUpdate();
               
               
               if(rows >0){
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
     
     public boolean banUser(int userid)
            throws NamingException, SQLException, ParseException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //get connection
            con = DBHelpers.getConnection();
            //sql commmands
            String sql = "Update UserDetails " +
                     "Set  Status = 0 " +
                     "Where UserID = ? ";

            //create statement
            stm = con.prepareStatement(sql);
           // stm.setString(1, email);
            stm.setInt(1, userid);
            //execute querry
            int rows = stm.executeUpdate();
            //process result
            if (rows > 0) {
                result = true;
            }
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
     
     
     
     
    private List<UserDetailsDTO> userList;

    public List<UserDetailsDTO> getUserList() {
        return userList;
    }

    public void showUserByManagerAccount() throws SQLException, NamingException {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "Select UserID,UserName ,PassWord,FullName, RoleID, "
                        + "Email, Phone, DOB, Gender, Picture "
                        + "from UserDetails "
                        + " where RoleID  IN (2,3) ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int userid = rs.getInt("UserID");
                    int role = rs.getInt("RoleID");
                    String fullname = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String phone = rs.getString("Phone");
                    String picture = rs.getString("Picture");
                    String username = rs.getString("UserName");
                    String password = rs.getString("PassWord");
                    Date DOB = rs.getDate("DOB");
                    java.sql.Date sqlDate = new java.sql.Date(DOB.getTime());

                    String gender = rs.getString("Gender");
                    UserDetailsDTO dto = new UserDetailsDTO(userid, role, username, password,
                            email, fullname, phone, sqlDate, gender, picture);

                    //add item to dto
                    if (this.userList == null) {
                        this.userList = new ArrayList<>();
                    }
                    this.userList.add(dto);
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

    }
    
    public UserDetailsDTO showUserById(int userid) throws SQLException, NamingException {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        UserDetailsDTO dto = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "Select UserID,UserName ,PassWord,FullName, RoleID, "
                        + "Email, Phone, DOB, Gender, Picture "
                        + "from UserDetails "
                        + " where UserID = ? ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, userid);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {

                    int role = rs.getInt("RoleID");
                    String fullname = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String phone = rs.getString("Phone");
                    String picture = rs.getString("Picture");
                    String username = rs.getString("UserName");
                    String password = rs.getString("PassWord");
                    Date DOB = rs.getDate("DOB");
                    java.sql.Date sqlDate = new java.sql.Date(DOB.getTime());

                    String gender = rs.getString("Gender");
                    dto = new UserDetailsDTO(userid, role, username, password,
                            email, fullname, phone, sqlDate, gender, picture);

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
        return dto;
    }

    public boolean updateProfileAdmin(int userid, String fullName,
            String phone, String password)
            throws NamingException, SQLException, ParseException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //get connection
            con = DBHelpers.getConnection();
            //sql commmands
            String sql = "Update UserDetails "
                    + "Set  FullName =? , Phone=?, PassWord=? "
                    + "Where UserID = ? ";

            //create statement
            stm = con.prepareStatement(sql);
            // stm.setString(1, email);
            stm.setString(1, fullName);
            stm.setString(2, phone);         
            stm.setString(3, password);
            stm.setInt(4, userid);
            //execute querry
            int rows = stm.executeUpdate();
            //process result
            if (rows > 0) {
                result = true;
            }
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

}
