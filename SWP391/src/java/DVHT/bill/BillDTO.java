/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.bill;

import java.io.Serializable;
import java.sql.Date;





/**
 *
 * @author mthin
 */
public class BillDTO implements Serializable{
    private int billID;
    private int userID;
    private int paymentID;
    private int addressID;
    private Date date;
    private float totalPrice;
    private float shippingPee;
    private String month;

    public BillDTO() {
    }

    public BillDTO(int billID, int userID, int paymentID, int addressID, Date date, float totalPrice, float shippingPee) {
        this.billID = billID;
        this.userID = userID;
        this.paymentID = paymentID;
        this.addressID = addressID;
        this.date = date;
        this.totalPrice = totalPrice;
        this.shippingPee = shippingPee;
    }

    public BillDTO(float totalPrice, String month) {
        this.totalPrice = totalPrice;
        this.month = month;
    }
    
    

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    
    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getShippingPee() {
        return shippingPee;
    }

    public void setShippingPee(float shippingPee) {
        this.shippingPee = shippingPee;
    }

    @Override
    public String toString() {
        return "BillDTO{" + "billID=" + billID + ", userID=" + userID + ", paymentID=" + paymentID + ", addressID=" + addressID + ", date=" + date + ", totalPrice=" + totalPrice + ", shippingPee=" + shippingPee + '}';
    }
    
   
}
