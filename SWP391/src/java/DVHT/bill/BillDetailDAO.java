/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.bill;

import DHTV.order.OrderDTO;
import DHTV.order.OrderDetailDTO;
import DVHT.utils.DBHelpers;
import java.sql.Connection;
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
public class BillDetailDAO {

    private List<BillDetailDTO> orderList;

    public List<BillDetailDTO> getOrderList() {
        return orderList;
    }

    public static int addBillDetail(int key, OrderDetailDTO dto)
            throws NamingException, SQLException, ParseException {

        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            //1.Conect Database
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Sql command
                String sql = "Insert into [BillDetail] ([BillID] ,[ProductID] ,[sizeID] ,[Quantity] ,[Price]) "
                        + "values (?,?,?,?,?) ";
                //3. Create Statement
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setInt(1, key);
                stm.setInt(2, dto.getProductID());
                stm.setInt(3, dto.getSizeID());
                stm.setInt(4, dto.getQuantity());
                stm.setDouble(5, dto.getPrice());

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

    private List<BillDetailDTO> billDetailList;

    public List<BillDetailDTO> getBillDetailList() {
        return billDetailList;
    }

    public BillDetailDTO showListBillDetail(int BillID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        BillDetailDTO result = new BillDetailDTO();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [BillDetailID] ,[BillID] ,[ProductID] ,[SizeID],Quantity,Price "
                        + "from BillDetail "
                        + "where BillID= ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, BillID);
                //execute query  
                rs = stm.executeQuery();

                //5 process
                while (rs.next()) {
//                    int BillID = rs.getInt("BillID");
                    int BillDetailID = rs.getInt("BillDetailID");
                    int ProductID = rs.getInt("ProductID");
                    int SizeID = rs.getInt("SizeID");
                    int Quantity = rs.getInt("Quantity");
                    Double Price = rs.getDouble("Price");

                    //create dto
                    BillDetailDTO dto = new BillDetailDTO(BillDetailID, BillID, ProductID, SizeID, Quantity, SizeID);

                    result = dto;
                    if (this.billDetailList == null) {
                        this.billDetailList = new ArrayList<>();
                    }//end the list no exsited
                    this.billDetailList.add(dto);
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

    private List<BillDetailDTO> listdto;

    public List<BillDetailDTO> getListdto() {
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
                String sql = "SELECT TOP 10 bd.ProductID, SUM(Quantity) AS Quantity, p.ProductName "
                        + "FROM [BillDetail] bd "
                        + "INNER JOIN [Bill] b ON bd.BillID = b.BillID  "
                        + "INNER JOIN Product p ON bd.ProductID = p.ProductID  "
                        + "Where MONTH(b.[Date]) = MONTH(GETDATE()) AND YEAR(b.[Date]) = YEAR(GETDATE()) "
                        + "GROUP BY bd.ProductID, p.ProductName "
                        + "ORDER BY Quantity DESC";
                //3. Create the PreparedStatement
                stm = con.prepareStatement(sql);
                //4. Execute the query and retrieve the results
                rs = stm.executeQuery();
                while (rs.next()) {
                    //5. Retrieve the data from the result set and create a ProductDTO object
                    BillDetailDTO product = new BillDetailDTO();
                    int pro = rs.getInt("ProductID");
                    int quan = rs.getInt("Quantity");
                    String name = rs.getString("ProductName");
                    //6. Add the product to the list
                    product = new BillDetailDTO(pro, quan, name);

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
                String sql = "SELECT TOP 10 p.ProductName ,p.ProductID, SUM(Quantity) AS Quantity, bd.BillID "
                        + "FROM [BillDetail] bd "
                        + "INNER JOIN [Bill] b ON bd.BillID = b.BillID  "
                        + "INNER JOIN Product p ON bd.ProductID = p.ProductID  "
                        + "WHERE  MONTH(b.[Date]) = ? AND YEAR(b.[Date]) = ? "
                        + "GROUP BY p.ProductName ,p.ProductID, bd.BillID "
                        + "ORDER BY Quantity DESC ";
                // 3. Create prepared statement

                stm = con.prepareStatement(sql);
                stm.setString(1, month);
                stm.setString(2, year);
                // 4. Execute query
                rs = stm.executeQuery();
                // 5. Process results
                while (rs.next()) {
                    BillDetailDTO product = new BillDetailDTO();
                    int pro = rs.getInt("ProductID");
                    int quan = rs.getInt("Quantity");
                    String name = rs.getString("ProductName");
                    //6. Add the product to the list
                    product = new BillDetailDTO(pro, quan, name);

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
}
