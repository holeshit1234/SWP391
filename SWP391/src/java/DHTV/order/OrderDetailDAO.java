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
public class OrderDetailDAO implements Serializable {

    public static boolean addOrder(OrderDetailDTO dto)
            throws NamingException, SQLException, ParseException {
        //set current date
        long millis = System.currentTimeMillis();
        java.sql.Date dateCurrent = new java.sql.Date(millis);
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
                String sql = "select * "
                        + "from  [OrderDetail] "
                        + "where OrderID = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);

                //execute query  
                rs = stm.executeQuery();
                //5 process
                System.out.println(orderID);
                while (rs.next()) {
                    int orderDetailID = rs.getInt("OrderDetailID");
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

    public void showListOrderDetail(int orderID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        OrderDetailDTO result = new OrderDetailDTO();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [OrderDetailID] ,[OrderID] ,[ProductID] ,[SizeID],Quantity,Price "
                        + "from OrderDetail "
                        + "where OrderID = ? ";
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
                    result = dto;
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

    private List<OrderDetailDTO> listdto;

    public List<OrderDetailDTO> getListdto() {
        return listdto;
    }

    public void getTop10Products() throws NamingException, SQLException {
//        List<ProdcutDTO> products = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect to the database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Define the SQL query to retrieve the top 10 products based on their quantity
                String sql = "SELECT TOP 10 ord.ProductID, SUM(Quantity) AS Quantity, p.ProductName "
                        + "FROM [OrderDetail] ord "
                        + "INNER JOIN Product p ON ord.ProductID = p.ProductID  "
                        + "INNER JOIN [Order] orn ON orn.OrderID = ord.OrderID  "
                        + "Where MONTH(orn.[Date]) = MONTH(GETDATE()) And YEAR(orn.[Date]) = YEAR(GETDATE()) And orn.ApprovalStatusID = 3  "
                        + "GROUP BY ord.ProductID, p.ProductName "
                        + "ORDER BY Quantity DESC";
                //3. Create the PreparedStatement
                stm = con.prepareStatement(sql);
                //4. Execute the query and retrieve the results
                rs = stm.executeQuery();
                while (rs.next()) {
                    //5. Retrieve the data from the result set and create a ProductDTO object
                    OrderDetailDTO product = new OrderDetailDTO();
                    int pro = rs.getInt("ProductID");
                    int quan = rs.getInt("Quantity");
                    String name = rs.getString("ProductName");
                    //6. Add the product to the list
                    product = new OrderDetailDTO(pro, quan, name);

                    if (this.listdto == null) {
                        this.listdto = new ArrayList<>();
                    }
                    this.listdto.add(product);
                }
            } //end con is available
        } finally {
            //7. Close the database resources
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

    public void getTop10ItemsInMonthYear(String month, String year) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // 1. Connect to the database
            con = DBHelpers.getConnection();
            if (con != null) {
                // 2. Create SQL query
                String sql = "SELECT TOP 10 p.ProductName ,p.ProductID, SUM(Quantity) AS Quantity, ord.OrderID "
                        + "FROM [OrderDetail] ord "
                        + "INNER JOIN [Order] orn ON ord.OrderID = orn.OrderID  "
                        + "INNER JOIN Product p ON ord.ProductID = p.ProductID  "
                        + "WHERE  MONTH(orn.[Date]) = ? AND YEAR(orn.[Date]) = ? AND orn.ApprovalStatusID = 3 "
                        + "GROUP BY p.ProductName ,p.ProductID, ord.OrderID "
                        + "ORDER BY Quantity DESC ";
                // 3. Create prepared statement

                stm = con.prepareStatement(sql);
                stm.setString(1, month);
                stm.setString(2, year);
                // 4. Execute query
                rs = stm.executeQuery();
                // 5. Process results
                while (rs.next()) {
                    OrderDetailDTO product = new OrderDetailDTO();
                    int pro = rs.getInt("ProductID");
                    int quan = rs.getInt("Quantity");
                    String name = rs.getString("ProductName");
                    //6. Add the product to the list
                    product = new OrderDetailDTO(pro, quan, name);

                    if (this.listdto == null) {
                        this.listdto = new ArrayList<>();
                    }
                    this.listdto.add(product);
                }
            } // end if con is available
        } finally {
            // 6. Close connections
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
    public int getQuantitySoldByProductID (int productID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int quantity = 0;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                 String sql = "SELECT [Quantity] from [OrderDetail] d, [Order] o "+
                                "where d.OrderID=o.OrderID and o.ApprovalStatusID=3 and ProductID = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while(rs.next()){
                    quantity += rs.getInt("Quantity");
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
            return quantity;
        }
    }
}
