/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.product;

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
 * @author mthin
 */
public class ProductDAO {

    private List<ProductDTO> itemsList;

    public List<ProductDTO> getItemsList() {
        return itemsList;
    }

    public void showProduct()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "SELECT P.ProductID,IP.Image, Cat.CategoryName,Bra.BrandName,Cat.Gender,P.Price,PD.Quantity "
                        + "Product P Inner join ProductIMG IP on P.ProductID=IP.ProductID "
                        + "Inner join Brand Bra  on p.BrandID = Bra.BrandID"
                        + "Inner join Category Cat on P.CategoryID=Cat.CategoryID"
                        + "Inner join ProductDetails PD on p.ProductID=PD.ProductID";
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while(rs.next()){
                    int productID= rs.getInt("P.ProductID");
                    String image= rs.getString("IP.Image");
                    String category= rs.getString("Cat.CategoryName");
                    String brand= rs.getString("Bra.BrandName");
                    Boolean gender= rs.getBoolean("Cat.Gender");
                    Double price= rs.getDouble("P.Price");
                    int quantity =rs.getInt("PD.Quantity");
                    //create dto
                    ProductDTO dto = new ProductDTO(productID, image,
                            category, brand, gender, price,quantity);
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
}
