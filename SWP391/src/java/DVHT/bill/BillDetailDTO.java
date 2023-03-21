/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.bill;

import java.io.Serializable;

/**
 *
 * @author mthin
 */
public class BillDetailDTO implements Serializable{
    private int billDetailID;
    private int billID;
    private int productID;
    private int sizeID;
    private int quantity;
    private float price;
    private String productName;
    private String month;
    private String year;

    public BillDetailDTO() {
    }

    public BillDetailDTO(int billDetailID, int billID, int productID, int sizeID, int quantity, float price) {
        this.billDetailID = billDetailID;
        this.billID = billID;
        this.productID = productID;
        this.sizeID = sizeID;
        this.quantity = quantity;
        this.price = price;
    }

    public BillDetailDTO(int productID, int quantity, String productName) {
        this.productID = productID;
        this.quantity = quantity;
        this.productName = productName;
    }

    public BillDetailDTO(int productID, int quantity, String productName, String month, String year) {
        this.productID = productID;
        this.quantity = quantity;
        this.productName = productName;
        this.month = month;
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    
    public int getBillDetailID() {
        return billDetailID;
    }

    public void setBillDetailID(int billDetailID) {
        this.billDetailID = billDetailID;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BillDetailDTO{" + "billDetailID=" + billDetailID + ", billID=" + billID + ", productID=" + productID + ", sizeID=" + sizeID + ", quantity=" + quantity + ", price=" + price + '}';
    }
    
}
