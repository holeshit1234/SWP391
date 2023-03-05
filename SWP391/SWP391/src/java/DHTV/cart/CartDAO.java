/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.cart;

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
public class CartDAO {

    public boolean saveCart(CartDTO cart) throws NamingException, SQLException {
        int key = 0;
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                //create sql
                String sql = "INSERT INTO Cart (ProductID,SizeID,StoreID,UserID,Quantity,Status,price ) "
                        + "values (?,?,?,?,?,?,? ) ";
                //create stm
                stm = con.prepareStatement(sql);
                stm.setInt(1, cart.getProductID());
                stm.setInt(2, cart.getSizeID());
                stm.setInt(3, cart.getStoreID());
                stm.setInt(4, cart.getUserID());
                stm.setInt(5, cart.getQuantity());
                stm.setBoolean(6, true);
                stm.setDouble(7, cart.getPrice());
                //execute
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
    public boolean cartExisted(int productID, int storeID , int sizeID, int userID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                //create sql
                String sql = "SELECT * from Cart "
                        + "where ProductID =? and StoreID =? and SizeID =? and UserID =?";
                //create stm
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                stm.setInt(2, storeID);
                stm.setInt(3, sizeID);
                stm.setInt(4, userID);
                //execute
                rs = stm.executeQuery();
                if (rs.next()) {
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
    public boolean cartUserExisted(int userID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                //create sql
                String sql = "SELECT * from Cart "
                        + "where UserID =?";
                //create stm
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                //execute
                rs = stm.executeQuery();
                if (rs.next()) {
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
    public int getQuantityCartInCart(int productID, int storeID , int sizeID, int userID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int result = 0;
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                //create sql
                String sql = "SELECT * from Cart "
                        + "where ProductID =? and StoreID =? and SizeID =? and UserID =?";
                //create stm
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                stm.setInt(2, storeID);
                stm.setInt(3, sizeID);
                stm.setInt(4, userID);
                //execute
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("Quantity");
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
    
public int getQuantityCartInStore(CartDTO cart) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int result = 0;
        System.out.println(cart);
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                //create sql
                String sql = "select Quantity " +
                    "from [ProductDetails] " +
                    "where ProductID = ? and StoreID = ? and SizeID =?";
                //create stm
                stm = con.prepareStatement(sql);
                stm.setInt(1, cart.getProductID());                
                stm.setInt(2, cart.getStoreID());
                stm.setInt(3, cart.getSizeID());      
                //execute
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("Quantity");
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

    private List<CartDTO> cartList;

    public List<CartDTO> getCartList() {
        return cartList;
    }

    public void getCartByUserID(int userID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        this.cartList = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select [CartID],[ProductID],[SizeID],[StoreID],[UserID],[Quantity],[Status], [price] "
                        + "from  Cart "
                        + "where Status =1 and UserID = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int cartID = rs.getInt("CartID");
                    int productID = rs.getInt("ProductID");
                    int sizeID = rs.getInt("SizeID");
                    int storeID = rs.getInt("StoreID");
                    int quantity = rs.getInt("Quantity");
                    boolean status = rs.getBoolean("Status");
                    double price = rs.getDouble("price");

                    //create dto
                    CartDTO dto = new CartDTO(cartID, productID, sizeID, storeID, userID, quantity, status, price);
                    System.out.println(dto);
                    //add item to dto
                    if (this.cartList == null) {
                        this.cartList = new ArrayList<>();
                    }//end the list no exsited
                    this.cartList.add(dto);
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

    public boolean deleteCart(int cartID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "DELETE Cart "
                        + "where CartID = ?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, cartID);
                //execute query  
                int rows = stm.executeUpdate();
                //5 process
                if (rows > 0) {
                    result = true;
                }
            }

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

    public boolean updatecart(int cartId, int productId, int quantity, double price)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "Update Cart "
                        + "set Quantity = ?, price = ? "
                        + "where CartID = ? and ProductID = ? ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setDouble(2, price);
                stm.setInt(3, cartId);
                stm.setInt(4, productId);
                //execute query  
                int rows = stm.executeUpdate();
                //5 process
                if (rows > 0) {
                    result = true;
                }
            }

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
    public boolean updatecartNoKey(int sizeID, int storeID, int productId,int userID, int quantity, double price)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "Update Cart "
                        + "set Quantity = ?, Price = ? "
                        + "where SizeID = ? and ProductID = ? and StoreID =? and UserID=?";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setDouble(2, price);
                stm.setInt(3, sizeID);
                stm.setInt(4, productId);
                stm.setInt(5, storeID);
                stm.setInt(6, userID);
                //execute query  
                int rows = stm.executeUpdate();
                //5 process
                if (rows > 0) {
                    result = true;
                }
            }

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

    public CartDTO getCartByProductId(int cartId, int productId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CartDTO cart = null;

        try {
            con = DBHelpers.getConnection();
            String sql = "SELECT * FROM Cart WHERE CartId = ? AND ProductId = ? ";
            stm = con.prepareStatement(sql);
            stm.setInt(1, cartId);
            stm.setInt(2, productId);
            rs = stm.executeQuery();
            if (rs.next()) {
                int sizeId = rs.getInt("sizeId");
                int storeID = rs.getInt("StoreID");
                int userid = rs.getInt("UserID");
                boolean status = rs.getBoolean("Status");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                cart = new CartDTO(cartId, productId, sizeId, storeID, userid, quantity, status, price);
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

        return cart;
    }

    public double getTotalCartPrice(int userid) throws NamingException, SQLException {
        double sum = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "select sum(price) "
                        + "from Cart "
                        + "Where UserID = ? ";

                stm = con.prepareStatement(sql);

                stm.setInt(1, userid);

                rs = stm.executeQuery();
                if (rs.next()) {
                    sum = rs.getDouble(1);
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
        return sum;
    }
    
    public int getQuantityProductCart(int userid) throws NamingException, SQLException {
        int quantity = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "select sum(Quantity) "
                        + "from Cart "
                        + "Where UserID = ? ";

                stm = con.prepareStatement(sql);

                stm.setInt(1, userid);

                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt(1);
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
        return quantity;
    }
    
    public boolean deleteCartByUserID(int userID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                //create sql
                String sql = "DELETE from Cart "
                        + "where UserID=?";
                //create stm
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                
                //execute
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
}
