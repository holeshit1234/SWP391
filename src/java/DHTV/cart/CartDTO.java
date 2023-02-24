/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.cart;

import DHTV.product.ProductDTO;

/**
 *
 * @author vinht
 */
public class CartDTO {
    private ProductDTO Product;
    private int cartID;
    private int productID;
    private String productName;
    private int quantity;
    private double price;
    
    private String img;
}
