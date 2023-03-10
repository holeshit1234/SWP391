/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.order;

import DVHT.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author vinht
 */
public class OrderDetailDAO implements Serializable{
    public static boolean addOrder(OrderDetailDTO dto)
            throws NamingException, SQLException, ParseException {
        //set current date
        long millis=System.currentTimeMillis();   
        java.sql.Date dateCurrent=new java.sql.Date(millis);   
        java.sql.Date sqlDate = new java.sql.Date(dateCurrent.getTime());   
        
        int key = 0;
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Insert into [OrderDetail] ([OrderID],[ProductID],[SizeID],[Quantity],[Price])"
                        + "values (?,?,?,?,?) ";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getOrderID());
                stm.setInt(2, dto.getProductID());
                stm.setInt(3, dto.getSizeID());
                stm.setInt(4, dto.getQuantity());
                stm.setDouble(5, dto.getPrice());            
                //4.execute query
                int rows = stm.executeUpdate();
                if(rows > 0) {
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
            return result;
        }
       
    }
    
    private List<OrderDetailDTO> orderDetailList;

    public List<OrderDetailDTO> getOrderDetailList() {
        return orderDetailList;
    }

    public void showOrderDetailByOrderID(int orderID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.orderDetailList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                
                //2 sql commands
                 String sql = "select * "+
                                "from  [OrderDetail] " +
                                "where OrderID = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
               
                //execute query  
                rs = stm.executeQuery();
                //5 process
                System.out.println(orderID);
                while(rs.next()){
                    int orderDetailID=rs.getInt("OrderDetailID");
                    int productID = rs.getInt("ProductID");                    
                    int sizeID = rs.getInt("SizeID");
                    int quantity = rs.getInt("Quantity");
                    double price = rs.getFloat("Price");
                    //create dto
                    OrderDetailDTO dto = new OrderDetailDTO(orderDetailID, orderID, productID, sizeID, quantity, price);
                    System.out.println(dto);
                    
                    //add item to dto
                    if (this.orderDetailList == null) {
                        this.orderDetailList = new ArrayList<>();
                    }//end the list no exsited
                    this.orderDetailList.add(dto);
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
     public OrderDetailDTO showListOrderDetail(int orderID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        OrderDetailDTO result= new OrderDetailDTO();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [OrderDetailID] ,[OrderID] ,[ProductID] ,[SizeID],Quantity,Price "
                        + "from OrderDetail "
                        + "where OrderID= ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                //execute query  
                rs = stm.executeQuery();
                
                //5 process

                while (rs.next()) {
                    int OrderID = rs.getInt("OrderID");
                    int OrderDetailID = rs.getInt("OrderDetailID");
                    int ProductID = rs.getInt("ProductID");
                    int SizeID = rs.getInt("SizeID");
                    int Quantity = rs.getInt("Quantity");
                    Double Price = rs.getDouble("Price");
                   

                    //create dto
                    OrderDetailDTO dto = new OrderDetailDTO(OrderDetailID, OrderID, ProductID, SizeID, Quantity, Price);
                    result=dto;
                    if (this.orderDetailList == null) {
                        this.orderDetailList = new ArrayList<>();
                    }//end the list no exsited
                    this.orderDetailList.add(dto);
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
}
