/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.product;

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
public class ProductImgDAO {
    
    
    public String getOneImgByProductID (int productID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        String img = "";
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                 String sql = "SELECT [Image] " +
                            "FROM [ProductIMG] " +
                            "where ProductID = ? ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                if(rs.next()){
                    img= rs.getString("Image");
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
            return img;
        }
    }
    
    
    
    private List<ProductImgDTO> imgList;

    public List<ProductImgDTO> getImgList() {
        return imgList;
    }

    public ProductImgDTO getImgByProductID(int productID)
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        ProductImgDTO result = null;
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                 String sql = "SELECT [Image], ImageID " +
                            "FROM [ProductIMG] " +
                            "where ProductID = ? ";
                // 3 stm create
                stm = con.prepareStatement(sql);
                //execute query  
                rs = stm.executeQuery();
                //5 process
                while(rs.next()){
                   String img= rs.getString("Image");
                   int  imgid = rs.getInt("ImageID");
                   ProductImgDTO dto = new ProductImgDTO(imgid, productID, img);
                    //add item to dto
                    if (this.imgList == null) {
                        this.imgList = new ArrayList<>();
                    }//end the list no exsited
                    this.imgList.add(dto);
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
