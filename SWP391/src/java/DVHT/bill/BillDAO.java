/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.bill;

import DHTV.order.OrderDTO;
import DVHT.utils.DBHelpers;
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
 * @author mthin
 */
public class BillDAO {

    private List<BillDTO> orderList;

    public List<BillDTO> getBillList() {
        return orderList;
    }

    public static int addBill(OrderDTO dto)
            throws NamingException, SQLException, ParseException {
        //set current date

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
                String sql = "Insert into [Bill] ([UserID] ,[PaymentID] ,[AddressID] ,[Date] ,[TotalPrice] "
                        + " ,[Shippingfee]  ) "
                        + "values (?,?,?,?,?,?) ";
                //3. Create Statement
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setInt(1, dto.getUserID());
                stm.setInt(2, dto.getPaymentID());
                stm.setInt(3, dto.getAddressID());
                stm.setDate(4, dto.getDate());
                stm.setDouble(5, dto.getTotalPrice());
                stm.setDouble(6, dto.getShippingFee());

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

    public void showListBillOldToNew()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [BillID] ,[UserID] ,[PaymentID] ,[AddressID],Date,TotalPrice,Shippingfee "
                        + "from [Bill] "
                        + "ORDER BY BillID DESC";
                stm = con.prepareStatement(sql);

                //execute query  
                rs = stm.executeQuery();
                //5 process

                while (rs.next()) {
                    int BillID = rs.getInt("BillID");
                    int UserID = rs.getInt("UserID");
                    int PaymentID = rs.getInt("PaymentID");
                    int AddressID = rs.getInt("AddressID");
                    Date date = rs.getDate("Date");
                    float totalPrice = rs.getFloat("TotalPrice");
                    float ShippingFee = rs.getFloat("Shippingfee");

                    //create dto
                    BillDTO dto = new BillDTO(BillID, UserID, PaymentID, AddressID, date, totalPrice, ShippingFee);
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

    private List<BillDTO> allBillList;

    public List<BillDTO> getAllBillList() {
        return allBillList;
    }

    public void showBillByUserID(int userID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.allBillList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [BillID] ,[UserID] ,[PaymentID] ,[AddressID],Date,TotalPrice,Shippingfee "
                        + "from [Bill] "
                        + "Where UserID = ? ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);

                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int billid = rs.getInt("BillID");
                    int paymentID = rs.getInt("PaymentID");
                    int addressID = rs.getInt("AddressID");
                    Date date = rs.getDate("Date");
                    float totalPrice = rs.getFloat("TotalPrice");
                    float shippingfee = rs.getFloat("Shippingfee");

                    //create dto
                    BillDTO dto = new BillDTO(billid, userID, paymentID, addressID, date, totalPrice, shippingfee);
                    System.out.println(dto);

                    //add item to dto
                    if (this.allBillList == null) {
                        this.allBillList = new ArrayList<>();
                    }//end the list no exsited
                    this.allBillList.add(dto);
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

    private List<BillDTO> listPriceMonths;

    public List<BillDTO> getListPriceMonths() {
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
                        + "FROM [Bill]\n"
                        + "Where YEAR(Date) = YEAR(GETDATE()) \n"
                        + "GROUP BY MONTH(Date) ,YEAR(Date) ";
                //3. Create the PreparedStatement
                stm = con.prepareStatement(sql);
                //4. Execute the query and retrieve the results
                rs = stm.executeQuery();
                while (rs.next()) {
                    //5. Retrieve the data from the result set and create a ProductDTO object
                    BillDTO product = new BillDTO();
                    String month = rs.getString("month");
                    float total = rs.getFloat("total_price");
                    //6. Add the product to the list
                    product = new BillDTO(total, month);

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
                        + "FROM [Bill]\n"
                        + "Where YEAR(Date) = ?\n"
                        + "GROUP BY MONTH(Date) ,YEAR(Date) ";
                //3. Create the PreparedStatement
                stm = con.prepareStatement(sql);
                stm.setString(1, year);
                //4. Execute the query and retrieve the results
                rs = stm.executeQuery();
                while (rs.next()) {
                    //5. Retrieve the data from the result set and create a ProductDTO object
                    BillDTO product = new BillDTO();
                    String month = rs.getString("month");
                    float total = rs.getFloat("total_price");
                    //6. Add the product to the list
                    product = new BillDTO(total, month);

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


    public BillDTO getTotalPriceAtYear() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        BillDTO product = null;
        try {
            //1. Connect to the database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Define the SQL query to retrieve the top 10 products based on their quantity
                String sql = "SELECT  SUM(TotalPrice) AS total_price,YEAR(Date) AS year "
                        + "FROM [Bill]\n"
                        + "Where YEAR(Date) = YEAR(GETDATE()) \n"
                        + "GROUP BY  YEAR(Date) ";
                //3. Create the PreparedStatement
                stm = con.prepareStatement(sql);

                //4. Execute the query and retrieve the results
                rs = stm.executeQuery();
                while (rs.next()) {
                    //5. Retrieve the data from the result set and create a ProductDTO object             
                    String year = rs.getString("year");
                    float total = rs.getFloat("total_price");
                    //6. Add the product to the list
                    product = new BillDTO(total, year);

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

    
    public BillDTO getTotalPriceAtYear(String year) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        BillDTO product = null;
        try {
            //1. Connect to the database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Define the SQL query to retrieve the top 10 products based on their quantity
                String sql = "SELECT  SUM(TotalPrice) AS total_price, YEAR(Date) AS year "
                        + "FROM [Bill]\n"
                        + "Where YEAR(Date) = ?\n"
                        + "GROUP BY  YEAR(Date) ";
                //3. Create the PreparedStatement
                stm = con.prepareStatement(sql);
                stm.setString(1, year);
                //4. Execute the query and retrieve the results
                rs = stm.executeQuery();
                while (rs.next()) {
                    //5. Retrieve the data from the result set and create a ProductDTO object                               
                    float total = rs.getFloat("total_price");
                    //6. Add the product to the list
                    product = new BillDTO(total, year);
                    

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
