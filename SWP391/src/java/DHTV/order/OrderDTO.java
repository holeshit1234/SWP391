/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.order;

import java.sql.Date;

/**
 *
 * @author vinht
 */
public class OrderDTO {
    private int orderID;
    private int userID;
    private int paymentID;
    private int addressID;
    private Date date;
    private double totalPrice;
    private double shippingFee;
    private int approvalStatus;
    private boolean paymentStatus;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, int userID, int paymentID, int addressID, Date date, double totalPrice, double shippingFee, int approvalStatus, boolean paymentStatus) {
        this.orderID = orderID;
        this.userID = userID;
        this.paymentID = paymentID;
        this.addressID = addressID;
        this.date = date;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.approvalStatus = approvalStatus;
        this.paymentStatus = paymentStatus;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "orderID=" + orderID + ", userID=" + userID + ", paymentID=" + paymentID + ", addressID=" + addressID + ", date=" + date + ", totalPrice=" + totalPrice + ", shippingFee=" + shippingFee + ", approvalStatus=" + approvalStatus + ", paymentStatus=" + paymentStatus + '}';
    }

   
    
            
}
