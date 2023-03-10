/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.order;

import java.io.Serializable;

/**
 *
 * @author vinht
 */
public class OrderDetailDTO implements Serializable {

    private int orderDetailID;
    private int orderID;
    private int productID;
    private int sizeID;
    private int quantity;
    private double price;
    private String productName;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int productID, int quantity, String productName) {
        this.productID = productID;
        this.quantity = quantity;
        this.productName = productName;
    }

    public OrderDetailDTO(int productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
    }

    public OrderDetailDTO(int orderDetailID, int orderID, int productID, int sizeID, int quantity, double price) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.sizeID = sizeID;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" + "orderDetailID=" + orderDetailID + ", orderID=" + orderID + ", productID=" + productID + ", sizeID=" + sizeID + ", quantity=" + quantity + ", price=" + price + '}';
    }

}
