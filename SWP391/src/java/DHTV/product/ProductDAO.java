/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.product;

import DVHT.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author mthin
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> itemsList;

    public List<ProductDTO> getItemsList() {
        return itemsList;
    }

    public void showProduct()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.itemsList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description], Image "
                        + "from  Product "
                        + "where Status =1";
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    //create dto
                    ProductDTO dto = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, image);
                    System.out.println(dto);
                    //add item to dto
                    if (this.itemsList == null) {
                        this.itemsList = new ArrayList<>();
                    }//end the list no exsited
                    this.itemsList.add(dto);
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

    public int getTotalProduct()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description],[Image] "
                        + "from  Product "
                        + "where Status =1";
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    //create dto
                    ProductDTO dto = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, image);
                    //System.out.println(dto);
                    //add item to dto
                    if (this.itemsList == null) {
                        this.itemsList = new ArrayList<>();
                    }//end the list no exsited
                    this.itemsList.add(dto);
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
        return this.itemsList.size();
    }

    public List<ProductDTO> pagingProduct(int index, int recordsPerPage)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();

        try {
            con = DBHelpers.getConnection();
            String sql = "SELECT [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description], [Image] "
                    + "FROM Product\n"
                    + "where Status =1 "
                    + "ORDER BY ProductID\n"
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            stm = con.prepareStatement(sql);

            stm.setInt(1, (index - 1) * recordsPerPage);
            stm.setInt(2, recordsPerPage);
            rs = stm.executeQuery();

            while (rs.next()) {
                int productid = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                int brandid = rs.getInt("BrandID");
                int cate = rs.getInt("CategoryID");
                float price = rs.getFloat("Price");
                boolean status = rs.getBoolean("Status");
                String des = rs.getString("Description");
                String image = rs.getString("Image");

                ProductDTO dto = new ProductDTO(productid, name, brandid, cate, price, status, des, image);
                list.add(dto);
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
        return list;
    }

    public ProductDTO getInfoProductByProductID(int productID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        ProductDTO result = new ProductDTO();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description], Image  "
                        + "from  Product "
                        + "where ProductID = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                if (rs.next()) {

                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    //create dto
                    result = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, image);
                    System.out.println(result);

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

    public int searchProduct(String searchValue)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description] ,[Image]"
                        + "from  Product "
                        + "where [ProductName] Like  ? and Status= 1 ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String img = rs.getString("Image");
                    //create dto
                    ProductDTO dto = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, img);

                    //add item to dto
                    if (this.itemsList == null) {
                        this.itemsList = new ArrayList<>();
                    }//end the list no exsited
                    this.itemsList.add(dto);
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
        return this.itemsList.size();
    }

    public List<ProductDTO> pagingProductSearch(String searchValue, int index, int recordsPerPage)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();

        try {
            con = DBHelpers.getConnection();
            String sql = "SELECT [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description], [Image] "
                    + "FROM Product\n"
                    + "where Status =1 and [ProductName] Like  ? "
                    + "ORDER BY ProductID\n"
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + searchValue + "%");
            stm.setInt(2, (index - 1) * recordsPerPage);
            stm.setInt(3, recordsPerPage);
            rs = stm.executeQuery();

            while (rs.next()) {
                int productid = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                int brandid = rs.getInt("BrandID");
                int cate = rs.getInt("CategoryID");
                float price = rs.getFloat("Price");
                boolean status = rs.getBoolean("Status");
                String des = rs.getString("Description");
                String image = rs.getString("Image");

                ProductDTO dto = new ProductDTO(productid, name, brandid, cate, price, status, des, image);
                list.add(dto);
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
        return list;
    }

    public void showProductAdmin()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands

                //   stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");

                    //create dto
                    ProductDTO dto = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, image);

                    //add item to dto
                    if (this.itemsList == null) {
                        this.itemsList = new ArrayList<>();
                    }//end the list no exsited
                    this.itemsList.add(dto);
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

    public int addProductAdmin(ProductDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int key = 0;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "Insert into Product  (ProductName,BrandID,CategoryID,Price,Description, Status,Image) "
                        + "values (?,?,?,?,?,?,? ) ";
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1, dto.getProductName());
                stm.setInt(2, dto.getBrandID());
                stm.setInt(3, dto.getCategoryID());
                stm.setFloat(4, dto.getPrice());
                stm.setString(5, dto.getDescription());
                stm.setBoolean(6, true);
                stm.setString(7, dto.getImage());

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
        return key;
    }

    public ProductDTO getProductIdByInfo(ProductDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        ProductDTO result = new ProductDTO();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description], Image  "
                        + "from  Product "
                        + "where ProductName = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getProductName());
                //execute query  
                rs = stm.executeQuery();
                //5 process
                if (rs.next()) {

                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int productID = rs.getInt("ProductID");
                    int categoryID = rs.getInt("CategoryID");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    //create dto
                    result = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, image);

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

    public int ChangeStatusProduct(int productID, boolean status)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int result = 0;
        try {
            //1 get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "UPDATE Product SET status = ? "
                        + "WHERE productID  = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setInt(2, productID);
                // execute update
                result = stm.executeUpdate();
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

    public ProductDTO getProductByProductID(int productID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        ProductDTO result = new ProductDTO();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [ProductID],[ProductName],[BrandID],[CategoryID],[Price],[Status],[Description], Image  "
                        + "from  Product "
                        + "where ProductID = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                if (rs.next()) {

                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    //create dto
                    result = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, image);
                    if (this.itemsList == null) {
                        this.itemsList = new ArrayList<>();
                    }//end the list no exsited
                    this.itemsList.add(result);

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

    public int getTotalProductNam()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select P.[ProductID],P.[ProductName],P.[BrandID],P.[CategoryID],P.[Price],P.[Status],P.[Description],P.[Image] "
                        + "from  Product P "
                        + "Inner join Category C on P.CategoryID = C.CategoryID "
                        + "where P.Status =1 AND C.Gender = 'Nam' ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    //create dto
                    ProductDTO dto = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, image);
                    //System.out.println(dto);
                    //add item to dto
                    if (this.itemsList == null) {
                        this.itemsList = new ArrayList<>();
                    }//end the list no exsited
                    this.itemsList.add(dto);
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
        return this.itemsList.size();
    }

    public List<ProductDTO> pagingProductNam(int index, int recordsPerPage)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();

        try {
            con = DBHelpers.getConnection();
            String sql = "SELECT P.[ProductID], P.[ProductName], P.[BrandID], P.[CategoryID], P.[Price], P.[Status], P.[Description], P.[Image], C.[Gender]   "
                    + "FROM [Product] P  "
                    + "INNER JOIN [Category] C ON P.[CategoryID] = C.[CategoryID] "
                    + "WHERE P.[Status] = 1 AND C.[Gender] = 'Nam' "
                    + "ORDER BY P.[ProductID] "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
            stm = con.prepareStatement(sql);

            stm.setInt(1, (index - 1) * recordsPerPage);
            stm.setInt(2, recordsPerPage);
            rs = stm.executeQuery();

            while (rs.next()) {
                int productid = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                int brandid = rs.getInt("BrandID");
                int cate = rs.getInt("CategoryID");
                float price = rs.getFloat("Price");
                boolean status = rs.getBoolean("Status");
                String des = rs.getString("Description");
                String image = rs.getString("Image");
                String gender = rs.getString("Gender");
                ProductDTO dto = new ProductDTO(productid, name, brandid, cate, price, status, des, image, gender);
                list.add(dto);
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
        return list;
    }

    public int getTotalProductNu()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select P.[ProductID],P.[ProductName],P.[BrandID],P.[CategoryID],P.[Price],P.[Status],P.[Description],P.[Image] "
                        + "from  Product P "
                        + "Inner join Category C on P.CategoryID = C.CategoryID "
                        + "where P.Status =1 AND C.Gender = N'Nữ' ";
                // 3 stm create
                stm = con.prepareStatement(sql);

                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");

                    //create dto
                    ProductDTO dto = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, image);
                    //System.out.println(dto);
                    //add item to dto
                    if (this.itemsList == null) {
                        this.itemsList = new ArrayList<>();
                    }//end the list no exsited
                    this.itemsList.add(dto);
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
        return this.itemsList.size();
    }

    public List<ProductDTO> pagingProductNu(int index, int recordsPerPage)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();

        try {
            con = DBHelpers.getConnection();
            String sql = "SELECT P.[ProductID], P.[ProductName], P.[BrandID], P.[CategoryID], P.[Price], P.[Status], P.[Description], P.[Image], C.[Gender]  "
                    + "FROM [Product] P  "
                    + "INNER JOIN [Category] C ON P.[CategoryID] = C.[CategoryID] "
                    + "WHERE P.[Status] = 1 AND C.[Gender] = N'Nữ' "
                    + "ORDER BY P.[ProductID] "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
            stm = con.prepareStatement(sql);
            stm.setInt(1, (index - 1) * recordsPerPage);
            stm.setInt(2, recordsPerPage);

            rs = stm.executeQuery();

            while (rs.next()) {
                int productid = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                int brandid = rs.getInt("BrandID");
                int cate = rs.getInt("CategoryID");
                float price = rs.getFloat("Price");
                boolean status = rs.getBoolean("Status");
                String des = rs.getString("Description");
                String image = rs.getString("Image");
                String gender = rs.getString("Gender");
                ProductDTO dto = new ProductDTO(productid, name, brandid, cate, price, status, des, image, gender);
                list.add(dto);
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
        return list;
    }

    public int getTotalProductUnisex()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select P.[ProductID],P.[ProductName],P.[BrandID],P.[CategoryID],P.[Price],P.[Status],P.[Description],P.[Image] "
                        + "from  Product P "
                        + "Inner join Category C on P.CategoryID = C.CategoryID "
                        + "where P.Status =1 AND C.Gender = 'Unisex' ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    //create dto
                    ProductDTO dto = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, image);
                    //System.out.println(dto);
                    //add item to dto
                    if (this.itemsList == null) {
                        this.itemsList = new ArrayList<>();
                    }//end the list no exsited
                    this.itemsList.add(dto);
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
        return this.itemsList.size();
    }

    public List<ProductDTO> pagingProductUnisex(int index, int recordsPerPage)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();

        try {
            con = DBHelpers.getConnection();
            String sql = "SELECT P.[ProductID], P.[ProductName], P.[BrandID], P.[CategoryID], P.[Price], P.[Status], P.[Description], P.[Image],C.[Gender]  "
                    + "FROM [Product] P  "
                    + "INNER JOIN [Category] C ON P.[CategoryID] = C.[CategoryID] "
                    + "WHERE P.[Status] = 1 AND C.[Gender] = 'Unisex' "
                    + "ORDER BY P.[ProductID] "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
            stm = con.prepareStatement(sql);

            stm.setInt(1, (index - 1) * recordsPerPage);
            stm.setInt(2, recordsPerPage);
            rs = stm.executeQuery();

            while (rs.next()) {
                int productid = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                int brandid = rs.getInt("BrandID");
                int cate = rs.getInt("CategoryID");
                float price = rs.getFloat("Price");
                boolean status = rs.getBoolean("Status");
                String des = rs.getString("Description");
                String image = rs.getString("Image");
                String gender = rs.getString("Gender");
                ProductDTO dto = new ProductDTO(productid, name, brandid, cate, price, status, des, image, gender);
                list.add(dto);
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
        return list;
    }

    public int getTotalProductByBrand(int BrandID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select P.[ProductID],P.[ProductName],P.[BrandID],P.[CategoryID],P.[Price],P.[Status],P.[Description],P.[Image] "
                        + "from  Product P "
                        + " where P.BrandID = ? ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, BrandID);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    //create dto
                    ProductDTO dto = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, image);
                    //System.out.println(dto);
                    //add item to dto
                    if (this.itemsList == null) {
                        this.itemsList = new ArrayList<>();
                    }//end the list no exsited
                    this.itemsList.add(dto);
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
        return this.itemsList.size();
    }

    public List<ProductDTO> pagingProductByBrand(int BrandID, int index, int recordsPerPage)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();

        try {
            con = DBHelpers.getConnection();
            String sql = "SELECT P.[ProductID], P.[ProductName], P.[BrandID], P.[CategoryID], P.[Price], P.[Status], P.[Description], P.[Image]   "
                    + "FROM [Product] P  "
                    + "WHERE P.[BrandID] = ? "
                    + "ORDER BY P.[ProductID] "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
            stm = con.prepareStatement(sql);
            stm.setInt(1, BrandID);
            stm.setInt(2, (index - 1) * recordsPerPage);
            stm.setInt(3, recordsPerPage);
            rs = stm.executeQuery();

            while (rs.next()) {
                int productid = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                int brandid = rs.getInt("BrandID");
                int cate = rs.getInt("CategoryID");
                float price = rs.getFloat("Price");
                boolean status = rs.getBoolean("Status");
                String des = rs.getString("Description");
                String image = rs.getString("Image");

                ProductDTO dto = new ProductDTO(productid, name, brandid, cate, price, status, des, image);
                list.add(dto);
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
        return list;
    }

    public void showTop4NewProduct()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.itemsList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "SELECT TOP 4 [ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image] "
                        + "FROM  Product "
                        + "WHERE Status = 1 "
                        + "ORDER BY [ProductID] DESC ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    //create dto
                    ProductDTO dto = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, image);
                    System.out.println(dto);
                    //add item to dto
                    if (this.itemsList == null) {
                        this.itemsList = new ArrayList<>();
                    }//end the list no exsited
                    this.itemsList.add(dto);
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
    
    
    private List<ProductDTO> getTop4Sell;

    public List<ProductDTO> getGetTop4Sell() {
        return getTop4Sell;
    }
    
    
     public void showTop4BestSalesProduct()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.getTop4Sell = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "SELECT TOP 4 [ProductID], [ProductName], [BrandID], [CategoryID], [Price], [Status], [Description], [Image] "
                        + "FROM  Product "
                        + "WHERE Status = 1 "
                        + "ORDER BY [Price] ASC ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    int categoryID = rs.getInt("CategoryID");
                    float price = rs.getFloat("Price");
                    boolean status = rs.getBoolean("Status");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    //create dto
                    ProductDTO dto = new ProductDTO(productID, productName, brandID, categoryID, price, status, description, image);
                    System.out.println(dto);
                    //add item to dto
                    if (this.getTop4Sell == null) {
                        this.getTop4Sell = new ArrayList<>();
                    }//end the list no exsited
                    this.getTop4Sell.add(dto);
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
