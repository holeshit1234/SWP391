/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminDAO;

/**
 *
 * @author BachDuc
 */
public class Method_Payment {
   private String id_method;
   private String Payment_method;
   private int status;

    public Method_Payment() {
    }
   

    public String getId_method() {
        return id_method;
    }

    public void setId_method(String id_method) {
        this.id_method = id_method;
    }

    public String getPayment_method() {
        return Payment_method;
    }

    public void setPayment_method(String Payment_method) {
        this.Payment_method = Payment_method;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Method_Payment(String id_method, String Payment_method, int status) {
        this.id_method = id_method;
        this.Payment_method = Payment_method;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Method_Payment{" + "id_method=" + id_method + ", Payment_method=" + Payment_method + ", status=" + status + '}';
    }
    
}
