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
                String sql = "Select UserID, FullName, RoleID, Email, Phone, DOB, Gender "
                        + "From UserDetails "
                        + "Where UserName = ? And PassWord = ?";
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

                    Date DOB = rs.getDate("DOB");
                    java.sql.Date sqlDate = new java.sql.Date(DOB.getTime());

                    String gender = rs.getString("Gender");
                    result = new UserDetailsDTO(userid, role, username, password,
                            email, fullname, phone, sqlDate, gender);
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
                String sql = "insert into UserDetails ( RoleID, UserName, "
                        + "[PassWord], Email, FullName, Phone, DOB, Gender )"
                        + "values (?,?,?,?,?,?,?,?) ";
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

    public boolean updateProfile(int userid, String email, String fullName, String phone, Date DOB)
            throws NamingException, SQLException, ParseException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //get connection
            con = DBHelpers.getConnection();
            //sql commmands
            String sql = "Update UserDetails "
                    + "Set Email= ? , FullName =? , Phone=?, DOB=? "
                    + "Where UserID = ? ";

            //create statement
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, fullName);
            stm.setString(3, phone);
            if (DOB != null) {
                java.sql.Date sqlDate = new java.sql.Date(DOB.getTime());
                stm.setDate(4, sqlDate);
            } else {
                String date = "1-1-1999";
                DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
                Date defaultDate = df.parse(date);
                java.sql.Date sqlDate = new java.sql.Date(DOB.getTime());
                stm.setDate(4, sqlDate);
            }
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
                String sql = "Select UserName ,PassWord, FullName, RoleID, Email, Phone, DOB, Gender "
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
//                    if (DOB != null) {
//                       
//                       
//                    } else {
//                        String date = "1-1-1999";
//                        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
//                        Date defaultDate = Date.valueOf(date);
//                        sqlDate = new java.sql.Date(DOB.getTime());
//                        sqlDate = rs.getDate("DOB");
//                    }
                    String gender = rs.getString("Gender");
                    result = new UserDetailsDTO(userid, role, username,
                            password, email, fullname, phone, sqlDate, gender);
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
                        + "RoleID, Phone, DOB, Gender "
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
                    result = new UserDetailsDTO(userid, role, username,
                            password, email, fullname, phone, sqlDate, gender);
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
                        + "RoleID, Phone, DOB, Gender "
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
                    result = new UserDetailsDTO(userid, role, username,
                            password, email, fullname, phone, sqlDate, gender);
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
}
