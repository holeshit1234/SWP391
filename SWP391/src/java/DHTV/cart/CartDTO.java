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
    private int CartID;
    private int ProductID;
    private int SizeID;
    private int StoreID;
    private int UserID;
    private int Quantity;   
    boolean status;
    private double price;

    public CartDTO() {
    }

   
    public CartDTO(int CartID, int ProductID, int SizeID, int StoreID, int UserID, int Quantity, boolean status, double price) {
        this.CartID = CartID;
        this.ProductID = ProductID;
        this.SizeID = SizeID;
        this.StoreID = StoreID;
        this.UserID = UserID;
        this.Quantity = Quantity;
        this.status = status;
        this.price = price;
    }

   

   

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int CartID) {
        this.CartID = CartID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int SizeID) {
        this.SizeID = SizeID;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int StoreID) {
        this.StoreID = StoreID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CartDTO{" + "CartID=" + CartID + ", ProductID=" + ProductID + ", SizeID=" + SizeID + ", StoreID=" + StoreID + ", UserID=" + UserID + ", Quantity=" + Quantity + ", status=" + status + '}';
    }

   
    
}
