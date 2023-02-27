/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.cart;

import DHTV.product.ProductDTO;
import java.io.Serializable;

/**
 *
 * @author vinht
 */
public class CartDTO implements Serializable{
    private ProductDTO Product;
    private int cartID;
    private int productID;
    private String productName;
    private int quantity;
    private double price;
    
    private String img;
}
