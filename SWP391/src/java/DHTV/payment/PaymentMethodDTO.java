/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.payment;

import java.io.Serializable;

/**
 *
 * @author vinht
 */
public class PaymentMethodDTO implements Serializable{
    private int paymentID;
    private String paymentMethod;
    private boolean status;

    public PaymentMethodDTO() {
    }

    public PaymentMethodDTO(int paymentID, String paymentMethod, boolean status) {
        this.paymentID = paymentID;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentMethodDTO{" + "paymentID=" + paymentID + ", paymentMethod=" + paymentMethod + ", status=" + status + '}';
    }
    
}
