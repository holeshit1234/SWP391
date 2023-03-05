/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.payment;

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
public class PaymentMethodDAO {
    private List<PaymentMethodDTO> paymentList;

    public List<PaymentMethodDTO> getPaymentList() {
        return paymentList;
    }

    public void showPaymentMethod()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                 String sql = "select [PaymentID],[PaymentMethod] ,[Status] "+
                                "from  [PaymentMethod] ";                                
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while(rs.next()){
                    int paymentID=rs.getInt("PaymentID");
                    String paymentMethod= rs.getString("PaymentMethod");
                    boolean status = rs.getBoolean("Status");                    
                    //create dto
                    PaymentMethodDTO dto = new PaymentMethodDTO(paymentID, paymentMethod, status);
                    System.out.println(dto);
                    //add item to dto
                    if (this.paymentList == null) {
                        this.paymentList = new ArrayList<>();
                    }//end the list no exsited
                    this.paymentList.add(dto);
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
     
    private List<PaymentMethodDTO> listMethod;

    public List<PaymentMethodDTO> getListMethod() {
        return listMethod;
    }

    public void getPaymentMethod() throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select PaymentID, PaymentMethod, Status "
                        + "from PaymentMethod ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int paymentid = rs.getInt("PaymentID");
                    String paymentMethod = rs.getString("PaymentMethod");
                    boolean status = rs.getBoolean("Status");
                    PaymentMethodDTO dto = new PaymentMethodDTO(paymentid, paymentMethod, status);
                    //add item to dto
                    if (this.listMethod == null) {
                        this.listMethod = new ArrayList<>();
                    }
                    this.listMethod.add(dto);
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
    
    public boolean addPayment(String name, boolean avai) throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "insert into PaymentMethod (PaymentMethod, Status) "
                        + "values (?,?) ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setBoolean(2, avai);
                //execute query  
                int rows = stm.executeUpdate();
                //5 process
               if(rows >0){
                   result = true;
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
        return result;
    }
    
     public boolean updatePayment(int id, boolean avai) throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "Update PaymentMethod "
                        + "set Status=? "
                        + "where PaymentID =? ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                 stm.setBoolean(1, avai);
                stm.setInt(2, id);
               
                //execute query  
                int rows = stm.executeUpdate();
                //5 process
               if(rows >0){
                   result = true;
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
        return result;
    }
     
       public boolean deletePayment(int id) throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "Delete PaymentMethod "
                        + "Where PaymentID = ? ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                 
                stm.setInt(1, id);
               
                //execute query  
                int rows = stm.executeUpdate();
                //5 process
               if(rows >0){
                   result = true;
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
        return result;
    }
}
