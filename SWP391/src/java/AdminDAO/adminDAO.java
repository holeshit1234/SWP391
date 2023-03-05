/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminDAO;

import DHTV.product.ProductDTO;
import DVHT.utils.DBHelpers;
import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author BachDuc
 */
public class adminDAO {

    public static int AddMethod_AdminPage(String method, int status)
            throws NamingException, SQLException {
        int re = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Boolean result = false;
        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "insert into PaymentMethod(PaymentMethod,Status) values(?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, method);
                stm.setInt(2, status);
                re = stm.executeUpdate();
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
            return re;
        }
    }

    public static int DeleteMethod_AdminPage(String id)
            throws NamingException, SQLException {
        int re = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "delete from PaymentMethod where PaymentID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                re = stm.executeUpdate();
            } //end con is availible
        } finally {
            return re;
        }
    }

    public int DeleteMethod_AdminPagev2(String id)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "delete from PaymentMethod where PaymentID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                return stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            return 0;
        }
    }

    public int updateMethod_AdminPage(String id, String method, String status)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "update PaymentMethod set PaymentMethod=?,Status=? where PaymentID =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, method);
                stm.setString(2, status);
                stm.setString(3, id);
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            return 0;
        }
    }

    public int Approve_AdminPage(String id)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "update [order] set ApprovalStatus = 1 where OrderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            return 0;
        }
    }

    public int Reject_AdminPage(String id)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "update [order] set ApprovalStatus = 0 where OrderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            return 0;
        }
    }

    public List<Method_Payment> show_Method_Payment()
            throws NamingException, SQLException {
        List<Method_Payment> itemsList = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select * from PaymentMethod";
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    String id = rs.getString("PaymentID");
                    String method = rs.getString("PaymentMethod");
                    int status = rs.getInt("Status");
                    //create dto
                    Method_Payment mp = new Method_Payment(id, method, status);
                    System.out.println(mp);
                    //add item to dto
                    if (itemsList == null) {
                        itemsList = new ArrayList<>();
                    }//end the list no exsited
                    itemsList.add(mp);
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
            return itemsList;
        }
    }

    public List<Order> show_Order()
            throws NamingException, SQLException {
        List<Order> itemsList = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "select OrderID,UserID,ApprovalStatus from [order]";
                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                while (rs.next()) {
                    Order mp = new Order(rs.getString("OrderID"), rs.getString("UserID"), rs.getString("ApprovalStatus"), show_OrderDetail(rs.getString("OrderID")), show_Report(rs.getString("UserID")));
                    System.out.println(mp);
                    if (itemsList == null) {
                        itemsList = new ArrayList<>();
                    }
                    itemsList.add(mp);
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
            return itemsList;
        }
    }

    public List<OrderDeteil> show_OrderDetail(String id)
            throws NamingException, SQLException {
        List<OrderDeteil> itemsList = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "SELECT o.OderDetailID, o.OrderID, o.ProductID,"
                        + " o.SizeID, o.Quantity, o.Price, p.ProductName\n"
                        + "FROM [OderDetail] o JOIN Product p ON p.ProductID = o.ProductID"
                        + " where o.OrderID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    OrderDeteil mp = new OrderDeteil(rs.getString("OrderID"), rs.getString("ProductID"),
                            rs.getString("SizeID"), rs.getString("Quantity"), rs.getString("Price"));
                    mp.setProductName(rs.getString("ProductName"));
                    System.out.println(mp);
                    if (itemsList == null) {
                        itemsList = new ArrayList<>();
                    }
                    itemsList.add(mp);
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
            return itemsList;
        }
    }

    public List<Report> show_Report(String id)
            throws NamingException, SQLException {
        List<Report> itemsList = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "select ReportID,UserID,CommentID,Date,Description from Report where UserID like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + id + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    Report mp = new Report(rs.getString("ReportID"), rs.getString("UserID"), rs.getString("CommentID"), rs.getString("Date"), rs.getString("Description"));
                    System.out.println(mp);
                    if (itemsList == null) {
                        itemsList = new ArrayList<>();
                    }
                    itemsList.add(mp);
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
            return itemsList;
        }
    }

    public static void main(String[] args) {
        try {
            new adminDAO().show_Order();
        } catch (NamingException ex) {
            Logger.getLogger(adminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(adminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
