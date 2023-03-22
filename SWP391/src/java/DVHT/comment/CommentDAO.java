/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.comment;

import DVHT.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
public class CommentDAO implements Serializable {

    public boolean addComment(CommentDTO dto) throws SQLException, NamingException {
        //set current date
        long millis = System.currentTimeMillis();
        java.sql.Date dateCurrent = new java.sql.Date(millis);
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
                String sql = "insert into [Comment] ([UserID],[ProductID],[Date],[Description],[Point], [Status])"
                        + "values (?,?,?,?,?,?) ";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getUserID());
                stm.setInt(2, dto.getProductID());
                stm.setDate(3, sqlDate);
                stm.setString(4, dto.getDescription());
                stm.setInt(5, dto.getPoint());
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

    public boolean editComment(int commentID, String newComment) throws SQLException, NamingException {
        //
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "UPDATE [Comment] "
                        + "SET [Description] = ? "
                        + "WHERE CommentID = ? ";

                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, newComment);
                stm.setInt(2, commentID);
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

    public boolean deleteComment(int commentID) throws SQLException, NamingException {
        //
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "UPDATE [Comment]"
                        + "SET [Status] = 0 "
                        + "WHERE [CommentID] = ?";

                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, commentID);
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

    private List<CommentDTO> commentList;

    public List<CommentDTO> getCommentList() {
        return commentList;
    }

    public void selectCommentListFromSQL(int productID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.commentList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [CommentID],[UserID],[ProductID],[Date],[Description],[Point] "
                        + "from Comment "
                        + "where ProductID = ? and Status = 1";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                //execute query  
                rs = stm.executeQuery();
                //5 process

                while (rs.next()) {
                    int UserID = rs.getInt("UserID");
                    int ProductID = rs.getInt("ProductID");
                    Date date = rs.getDate("Date");
                    String Description = rs.getString("Description");
                    int Point = rs.getInt("Point");
                    int commentID = rs.getInt("CommentID");
                    //create dto
                    CommentDTO dto = new CommentDTO(commentID, UserID, ProductID, date, Description, Point, true);
                    System.out.println("Get data Comment:");
                    System.out.println(dto);
                    //add item to dto
                    if (this.commentList == null) {
                        this.commentList = new ArrayList<>();
                    }//end the list no exsited
                    this.commentList.add(dto);
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

    public void selectCommentListByStar(int productID, int star)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.commentList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [CommentID], [UserID],[ProductID],[Date],[Description],[Point] "
                        + "from Comment "
                        + "where ProductID = ? and Point = ? and Status = 1 ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                stm.setInt(2, star);
                //execute query  
                rs = stm.executeQuery();
                //5 process

                while (rs.next()) {
                    int UserID = rs.getInt("UserID");
                    int ProductID = rs.getInt("ProductID");
                    Date date = rs.getDate("Date");
                    String Description = rs.getString("Description");
                    int Point = rs.getInt("Point");
                    int commentID = rs.getInt("CommentID");

                    //create dto
                    CommentDTO dto = new CommentDTO(commentID, UserID, ProductID, date, Description, Point, true);
                    System.out.println("Get data Comment:");
                    System.out.println(dto);
                    //add item to dto
                    if (this.commentList == null) {
                        this.commentList = new ArrayList<>();
                    }//end the list no exsited
                    this.commentList.add(dto);
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

    public int countCommentListByStar(int productID, int star)
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
                String sql = "select [UserID],[ProductID],[Date],[Description],[Point] "
                        + "from Comment "
                        + "where ProductID = ? and Point = ? and Status =1";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                stm.setInt(2, star);
                //execute query  
                rs = stm.executeQuery();
                //5 process                
                while (rs.next()) {
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

    public void selectCommentListIncreateDate(int productID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.commentList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [CommentID], [UserID],[ProductID],[Date],[Description],[Point] "
                        + "from Comment "
                        + "where ProductID = ? and Status = 1 "
                        + "order by [Date]";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                //execute query  
                rs = stm.executeQuery();
                //5 process

                while (rs.next()) {
                    int UserID = rs.getInt("UserID");
                    int ProductID = rs.getInt("ProductID");
                    Date date = rs.getDate("Date");
                    String Description = rs.getString("Description");
                    int Point = rs.getInt("Point");
                    int commentID = rs.getInt("CommentID");
                    //create dto
                    CommentDTO dto = new CommentDTO(commentID, UserID, ProductID, date, Description, Point, true);
                    System.out.println("Get data Comment:");
                    System.out.println(dto);
                    //add item to dto
                    if (this.commentList == null) {
                        this.commentList = new ArrayList<>();
                    }//end the list no exsited
                    this.commentList.add(dto);
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

    public void selectCommentListDecreaseDate(int productID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.commentList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [CommentID],[UserID],[ProductID],[Date],[Description],[Point] "
                        + "from Comment "
                        + "where ProductID = ? and Status = 1 "
                        + "order by [Date] desc";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                //execute query  
                rs = stm.executeQuery();
                //5 process

                while (rs.next()) {
                    int UserID = rs.getInt("UserID");
                    int ProductID = rs.getInt("ProductID");
                    Date date = rs.getDate("Date");
                    String Description = rs.getString("Description");
                    int Point = rs.getInt("Point");
                    int commentID = rs.getInt("CommentID");
                    //create dto
                    CommentDTO dto = new CommentDTO(commentID, UserID, ProductID, date, Description, Point, true);
                    System.out.println("Get data Comment:");
                    System.out.println(dto);
                    //add item to dto
                    if (this.commentList == null) {
                        this.commentList = new ArrayList<>();
                    }//end the list no exsited
                    this.commentList.add(dto);
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

    public void selectCommentListByUserID(int productID, int userID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.commentList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [CommentID],[UserID],[ProductID],[Date],[Description],[Point] "
                        + "from Comment "
                        + "where ProductID = ? and UserID = ? and Status = 1 ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                stm.setInt(2, userID);
                //execute query  
                rs = stm.executeQuery();
                //5 process

                while (rs.next()) {
                    int UserID = rs.getInt("UserID");
                    int ProductID = rs.getInt("ProductID");
                    Date date = rs.getDate("Date");
                    String Description = rs.getString("Description");
                    int Point = rs.getInt("Point");
                    int commentID = rs.getInt("CommentID");
                    //create dto
                    CommentDTO dto = new CommentDTO(commentID, UserID, ProductID, date, Description, Point, true);
                    System.out.println("Get data Comment:");
                    System.out.println(dto);
                    //add item to dto
                    if (this.commentList == null) {
                        this.commentList = new ArrayList<>();
                    }//end the list no exsited
                    this.commentList.add(dto);
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

    public int getUserOfThisComment(int commentID) throws SQLException, NamingException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int UserID = 0;
        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Select [UserID]"
                        + "from [Comment] "
                        + "WHERE [CommentID] = ?  ";

                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, commentID);
                //4.execute query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    UserID = rs.getInt("UserID");
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
        return UserID;
    }
    private List<CommentDTO> ListUserReport;

    public List<CommentDTO> getListUserReport() {
        return ListUserReport;
    }

    public void getUserNeedCare(int commentID) throws SQLException, NamingException {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        CommentDTO result = null;
        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Select  cm.[UserID], us.FullName, cm.Description, us.[Status] "
                        + "from [Comment] cm "
                        + "INNER JOIN [UserDetails] us on cm.UserID = us.UserID "
                        + "WHERE cm.[CommentID] = ?  ";

                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, commentID);
                //4.execute query
                rs = stm.executeQuery();
                //5. process result
                while (rs.next()) {
                    int userid = rs.getInt("UserID");
                    String fullname = rs.getString("FullName");
                    boolean status = rs.getBoolean("Status");
                    String des = rs.getString("Description");
                    result = new CommentDTO(commentID, userid, des, status, fullname);

                    if (this.ListUserReport == null) {
                        this.ListUserReport = new ArrayList<>();
                    }
                    this.ListUserReport.add(result);
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
    }
    
  
    
    
    
    public void selectAllComment()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.commentList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select cm.[CommentID], cm.[UserID], cm.[ProductID], cm.[Date], cm.[Description], "
                        + "cm.[Status] ,p.ProductName, us.FullName "
                        + "from Comment cm "
                        + "inner join UserDetails us on us.UserID = cm.UserID "
                        + "inner join Product p on p.ProductID = cm.ProductID "
                        + "ORDER BY [CommentID] DESC";

                // 3 stm create
                stm = con.prepareStatement(sql);

                //execute query  
                rs = stm.executeQuery();
                //5 process

                while (rs.next()) {
                    int UserID = rs.getInt("UserID");
                    int ProductID = rs.getInt("ProductID");
                    Date date = rs.getDate("Date");
                    String Description = rs.getString("Description");                  
                    int commentID = rs.getInt("CommentID");
                    String  prodname = rs.getString("ProductName");
                    String  fullname = rs.getString("FullName");
                    boolean  status = rs.getBoolean("Status");
                    //create dto
                    CommentDTO dto = new CommentDTO(commentID, UserID, ProductID, date, Description, status, fullname, prodname);
     
                    //add item to dto
                    if (this.commentList == null) {
                        this.commentList = new ArrayList<>();
                    }//end the list no exsited
                    this.commentList.add(dto);
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
}
