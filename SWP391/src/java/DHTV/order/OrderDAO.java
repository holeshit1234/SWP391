/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.order;


import DVHT.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author vinht
 */
public class OrderDAO implements Serializable {

    public static int addOrder(OrderDTO dto)
            throws NamingException, SQLException, ParseException {
        //set current date
        long millis = System.currentTimeMillis();
        java.sql.Date dateCurrent = new java.sql.Date(millis);
        java.sql.Date sqlDate = new java.sql.Date(dateCurrent.getTime());

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
                String sql = "Insert into [Order] ([UserID] ,[PaymentID] ,[AddressID] ,[Date] ,[TotalPrice]"
                        + " ,[Shippingfee] ,[ApprovalStatusID] ,[PaymentStatus]) "
                        + "values (?,?,?,?,?,?,?,?) ";
                //3. Create Statement
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setInt(1, dto.getUserID());
                stm.setInt(2, dto.getPaymentID());
                stm.setInt(3, dto.getAddressID());
                stm.setDate(4, sqlDate);
                stm.setDouble(5, dto.getTotalPrice());
                stm.setDouble(6, dto.getShippingFee());
                stm.setInt(7, dto.getApprovalStatus());
                stm.setBoolean(8, dto.isPaymentStatus());

                //4.execute query
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
    public boolean updateOrderPoint(int orderID, int point) throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "UPDATE [Order] SET point=? "
                        + "Where OrderID = ? ";
                // 3 stm create
                stm = con.prepareStatement(sql);

                stm.setInt(1, point);
                stm.setInt(2, orderID);

                //execute query  
                int rows = stm.executeUpdate();
                //5 process
                if (rows>0) {
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
    public boolean checkOrderPoint(int orderID) throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "Select * from [Order] "
                        + "Where OrderID = ? and point is null ";
                // 3 stm create
                stm = con.prepareStatement(sql);

         
                stm.setInt(1, orderID);

                //execute query  
                rs = stm.executeQuery();
                //5 process
                if (rs.next()) {
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

    private List<OrderDTO> orderList;

    public List<OrderDTO> getOrderList() {
        return orderList;
    }

    public void showOrderByUserID(int userID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.orderList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select * "
                        + "from  [Order] "
                        + "where UserID = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);

                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    int paymentID = rs.getInt("PaymentID");
                    int addressID = rs.getInt("AddressID");
                    Date date = rs.getDate("Date");
                    double totalPrice = rs.getFloat("TotalPrice");
                    double shippingfee = rs.getFloat("Shippingfee");
                    int approvalStatusID = rs.getInt("ApprovalStatusID");
                    boolean paymentStatus = rs.getBoolean("PaymentStatus");
                    //create dto
                    OrderDTO dto = new OrderDTO(orderID, userID, paymentID, addressID, date, totalPrice, shippingfee, approvalStatusID, paymentStatus);
                    System.out.println(dto);

                    //add item to dto
                    if (this.orderList == null) {
                        this.orderList = new ArrayList<>();
                    }//end the list no exsited
                    this.orderList.add(dto);
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
    
    public void showOrderList()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.orderList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select * "
                        + "from  [Order] ";

                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    int userID = rs.getInt("UserID");
                    int paymentID = rs.getInt("PaymentID");
                    int addressID = rs.getInt("AddressID");
                    Date date = rs.getDate("Date");
                    double totalPrice = rs.getFloat("TotalPrice");
                    double shippingfee = rs.getFloat("Shippingfee");
                    int approvalStatusID = rs.getInt("ApprovalStatusID");
                    boolean paymentStatus = rs.getBoolean("PaymentStatus");
                    int point = rs.getInt("point");
                    //create dto
                    OrderDTO dto = new OrderDTO(orderID, userID, paymentID, addressID, date, totalPrice, shippingfee, approvalStatusID, paymentStatus,point);
                    System.out.println(dto);

                    //add item to dto
                    if (this.orderList == null) {
                        this.orderList = new ArrayList<>();
                    }//end the list no exsited
                    this.orderList.add(dto);
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

    public void showOrderByUserIDAndStatus(int userID, int status)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.orderList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select * "
                        + "from  [Order] "
                        + "where UserID = ? and [ApprovalStatusID] = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                stm.setInt(2, status);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    int paymentID = rs.getInt("PaymentID");
                    int addressID = rs.getInt("AddressID");
                    Date date = rs.getDate("Date");
                    double totalPrice = rs.getFloat("TotalPrice");
                    double shippingfee = rs.getFloat("Shippingfee");
                    int approvalStatusID = rs.getInt("ApprovalStatusID");
                    boolean paymentStatus = rs.getBoolean("PaymentStatus");
                    //create dto
                    OrderDTO dto = new OrderDTO(orderID, userID, paymentID, addressID, date, totalPrice, shippingfee, approvalStatusID, paymentStatus);
                    System.out.println(dto);

                    //add item to dto
                    if (this.orderList == null) {
                        this.orderList = new ArrayList<>();
                    }//end the list no exsited
                    this.orderList.add(dto);
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

    public String getApprovalStatus(int id) throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        String result = "";
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "Select ApprovalStatus  from [ApprovalStatus]"
                        + "Where [ApprovalStatusID] = ? ";
                // 3 stm create
                stm = con.prepareStatement(sql);

                stm.setInt(1, id);

                //execute query  
                rs = stm.executeQuery();
                //5 process
                if (rs.next()) {
                    result = rs.getString("ApprovalStatus");
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

    public boolean setApprovalStatusOrder(int orderID, int status) throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "Update [Order] "
                        + "Set [ApprovalStatusID] = ?  "
                        + "Where [OrderID] = ? ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setInt(2, orderID);

                //execute query  
                int rows = stm.executeUpdate();
                //5 process
                if (rows > 0) {
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

    public void showListOrder()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.orderList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [OrderID] ,[UserID] ,[PaymentID] ,[AddressID],Date,TotalPrice,Shippingfee,ApprovalStatusID,PaymentStatus "
                        + "from [Order] ";
                stm = con.prepareStatement(sql);

                //execute query  
                rs = stm.executeQuery();
                //5 process

                while (rs.next()) {
                    int OrderID = rs.getInt("OrderID");
                    int UserID = rs.getInt("UserID");
                    int PaymentID = rs.getInt("PaymentID");
                    int AddressID = rs.getInt("AddressID");
                    Date date = rs.getDate("Date");
                    double totalPrice = rs.getDouble("TotalPrice");
                    double ShippingFee = rs.getDouble("Shippingfee");
                    int ApprovalStatusID = rs.getInt("ApprovalStatusID");
                    boolean PaymentStatus = rs.getBoolean("PaymentStatus");

                    //create dto
                    OrderDTO dto = new OrderDTO(OrderID, UserID, PaymentID, AddressID, date, totalPrice, ShippingFee, ApprovalStatusID, PaymentStatus);
                    System.out.println("---------------ListOrder------------" + dto);
                    if (this.orderList == null) {
                        this.orderList = new ArrayList<>();
                    }//end the list no exsited
                    this.orderList.add(dto);
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
    public void showListOrderRated()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.orderList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select * "
                        + "from [Order] where point is not null";
                stm = con.prepareStatement(sql);

                //execute query  
                rs = stm.executeQuery();
                //5 process

                while (rs.next()) {
                    int OrderID = rs.getInt("OrderID");
                    int UserID = rs.getInt("UserID");
                    int PaymentID = rs.getInt("PaymentID");
                    int AddressID = rs.getInt("AddressID");
                    Date date = rs.getDate("Date");
                    double totalPrice = rs.getDouble("TotalPrice");
                    double ShippingFee = rs.getDouble("Shippingfee");
                    int ApprovalStatusID = rs.getInt("ApprovalStatusID");
                    boolean PaymentStatus = rs.getBoolean("PaymentStatus");
                    int point = rs.getInt("point");
                    //create dto
                    OrderDTO dto = new OrderDTO(OrderID, UserID, PaymentID, AddressID, date, totalPrice, ShippingFee, ApprovalStatusID, PaymentStatus,point);
                    System.out.println("---------------ListOrderRated------------" + dto);
                    if (this.orderList == null) {
                        this.orderList = new ArrayList<>();
                    }//end the list no exsited
                    this.orderList.add(dto);
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

    public boolean setApprovalAndPaymentStatusOrder(int orderID, int status, boolean payment) throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "Update [Order] "
                        + "Set [ApprovalStatusID] = ? , [PaymentStatus] =? "
                        + "Where [OrderID] = ? ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setInt(3, orderID);
                stm.setBoolean(2, payment);

                //execute query  
                int rows = stm.executeUpdate();
                //5 process
                if (rows > 0) {
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
    
     public void showListOrderFromBotToTop()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [OrderID] ,[UserID] ,[PaymentID] ,[AddressID],Date,TotalPrice,Shippingfee,ApprovalStatusID,PaymentStatus "
                        + "from [Order] "
                        + "ORDER BY OrderID DESC";
                stm = con.prepareStatement(sql);

                //execute query  
                rs = stm.executeQuery();
                //5 process

                while (rs.next()) {
                    int OrderID = rs.getInt("OrderID");
                    int UserID = rs.getInt("UserID");
                    int PaymentID = rs.getInt("PaymentID");
                    int AddressID = rs.getInt("AddressID");
                    Date date = rs.getDate("Date");
                    double totalPrice = rs.getDouble("TotalPrice");
                    double ShippingFee = rs.getDouble("Shippingfee");
                    int ApprovalStatusID = rs.getInt("ApprovalStatusID");
                    boolean PaymentStatus = rs.getBoolean("PaymentStatus");

                    //create dto
                    OrderDTO dto = new OrderDTO(OrderID, UserID, PaymentID, AddressID, date, totalPrice, ShippingFee, ApprovalStatusID, PaymentStatus);
                    System.out.println("---------------ListOrder------------" + dto);
                    if (this.orderList == null) {
                        this.orderList = new ArrayList<>();
                    }//end the list no exsited
                    this.orderList.add(dto);
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

    public OrderDTO getOrderByOrderID(int orderID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        OrderDTO result = new OrderDTO();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [OrderID] ,[UserID] ,[PaymentID] ,[AddressID],Date,TotalPrice,Shippingfee,ApprovalStatusID,PaymentStatus "
                        + "from [Order] "
                        + "where OrderID =? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                //execute query  
                rs = stm.executeQuery();
                //5 process

                while (rs.next()) {
                    int OrderID = rs.getInt("OrderID");
                    int UserID = rs.getInt("UserID");
                    int PaymentID = rs.getInt("PaymentID");
                    int AddressID = rs.getInt("AddressID");
                    Date date = rs.getDate("Date");
                    double totalPrice = rs.getDouble("TotalPrice");
                    double ShippingFee = rs.getDouble("Shippingfee");
                    int ApprovalStatusID = rs.getInt("ApprovalStatusID");
                    boolean PaymentStatus = rs.getBoolean("PaymentStatus");

                    //create dto
                    OrderDTO dto = new OrderDTO(OrderID, UserID, PaymentID, AddressID, date, totalPrice, ShippingFee, ApprovalStatusID, PaymentStatus);
                   
                    result = dto;
                    if (this.orderList == null) {
                        this.orderList = new ArrayList<>();
                    }//end the list no exsited
                    this.orderList.add(dto);
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

    private List<OrderDTO> listPriceMonths;

    public List<OrderDTO> getListPriceMonths() {
        return listPriceMonths;
    }

    public void getTotalPriceWithMonth() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect to the database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Define the SQL query to retrieve the top 10 products based on their quantity
                String sql = "    \n"
                        + "    SELECT MONTH(Date) AS month, SUM(TotalPrice) AS total_price,YEAR(Date) AS year\n"
                        + "FROM [Order]\n"
                        + "Where YEAR(Date) = YEAR(GETDATE()) AND ApprovalStatusID = 3\n"
                        + "GROUP BY MONTH(Date) ,YEAR(Date) ";
                //3. Create the PreparedStatement
                stm = con.prepareStatement(sql);
                //4. Execute the query and retrieve the results
                rs = stm.executeQuery();
                while (rs.next()) {
                    //5. Retrieve the data from the result set and create a ProductDTO object
                    OrderDTO product = new OrderDTO();
                    String montn = rs.getString("month");
                    Double total = rs.getDouble("total_price");
                    //6. Add the product to the list
                    product = new OrderDTO(total, montn);

                    if (this.listPriceMonths == null) {
                        this.listPriceMonths = new ArrayList<>();
                    }
                    this.listPriceMonths.add(product);
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

    public void getTotalPriceWithMonthByYear(String year) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect to the database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Define the SQL query to retrieve the top 10 products based on their quantity
                String sql = "    \n"
                        + "    SELECT MONTH(Date) AS month, SUM(TotalPrice) AS total_price,YEAR(Date) AS year\n"
                        + "FROM [Order]\n"
                        + "Where YEAR(Date) = ? AND ApprovalStatusID = 3\n"
                        + "GROUP BY MONTH(Date) ,YEAR(Date) ";
                //3. Create the PreparedStatement
                stm = con.prepareStatement(sql);
                stm.setString(1, year);
                //4. Execute the query and retrieve the results
                rs = stm.executeQuery();
                while (rs.next()) {
                    //5. Retrieve the data from the result set and create a ProductDTO object
                    OrderDTO product = new OrderDTO();
                    String month = rs.getString("month");
                    Double total = rs.getDouble("total_price");
                    //6. Add the product to the list
                    product = new OrderDTO(total, month);

                    if (this.listPriceMonths == null) {
                        this.listPriceMonths = new ArrayList<>();
                    }
                    this.listPriceMonths.add(product);
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

    public OrderDTO getTotalPriceAtYear() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderDTO product = null;
        try {
            //1. Connect to the database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Define the SQL query to retrieve the top 10 products based on their quantity
                String sql = "SELECT  SUM(TotalPrice) AS total_price,YEAR(Date) AS year "
                        + "FROM [Order]\n"
                        + "Where YEAR(Date) = YEAR(GETDATE()) AND ApprovalStatusID = 3 \n"
                        + "GROUP BY  YEAR(Date) ";
                //3. Create the PreparedStatement
                stm = con.prepareStatement(sql);

                //4. Execute the query and retrieve the results
                rs = stm.executeQuery();
                while (rs.next()) {
                    //5. Retrieve the data from the result set and create a ProductDTO object             
                    String year = rs.getString("year");
                    Double total = rs.getDouble("total_price");
                    //6. Add the product to the list
                    product = new OrderDTO(total, year);

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
        return product;
    }

    
    public OrderDTO getTotalPriceAtYear(String year) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderDTO product = null;
        try {
            //1. Connect to the database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Define the SQL query to retrieve the top 10 products based on their quantity
                String sql = "SELECT  SUM(TotalPrice) AS total_price, YEAR(Date) AS year "
                        + "FROM [Order]\n"
                        + "Where YEAR(Date) = ? AND ApprovalStatusID = 3\n"
                        + "GROUP BY  YEAR(Date) ";
                //3. Create the PreparedStatement
                stm = con.prepareStatement(sql);
                stm.setString(1, year);
                //4. Execute the query and retrieve the results
                rs = stm.executeQuery();
                while (rs.next()) {
                    //5. Retrieve the data from the result set and create a ProductDTO object                               
                    Double total = rs.getDouble("total_price");
                    //6. Add the product to the list
                    product = new OrderDTO(total, year);
                    

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
        return product;
    }
   
}
