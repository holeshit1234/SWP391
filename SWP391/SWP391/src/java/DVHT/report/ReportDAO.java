/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.report;

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
public class ReportDAO {
    public boolean addReport(ReportDTO dto) throws NamingException, SQLException{
        //set current date
        long millis=System.currentTimeMillis();   
        java.sql.Date dateCurrent=new java.sql.Date(millis);   
        java.sql.Date sqlDate = new java.sql.Date(dateCurrent.getTime());       
        //
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "insert into [Report] ([UserID],[CommentID],[Date],[Description]) "
                        + "values (?,?,?,?) ";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getUserID());
                stm.setInt(2,dto.getCommentID());
                stm.setDate(3, sqlDate);
                stm.setString(4,dto.getDescription());
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
    public boolean reportExisted(int userID, int commentID)
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
                String sql = "select [ReportID],[UserID],[CommentID],[Date],[Description] "
                             + "from Report " 
                             + "where UserID =? and CommentID = ? ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                stm.setInt(2, commentID);
                //execute query  
                rs = stm.executeQuery();
                //5 process                
                if(rs.next()){
                    result = true;
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
    
    public int countFlagComment(int userID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select * from Report r inner join Comment c " +
                            "on c.CommentID = r.CommentID and c.UserID = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                //execute query  
                rs = stm.executeQuery();
                //5 process                
                while(rs.next()){
                    result++;
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
