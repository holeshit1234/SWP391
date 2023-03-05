/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminDAO;

import java.util.List;

/**
 *
 * @author BachDuc
 */
public class Order {

    String Order_ID;
    String User_ID;
    String ApproveStatus;
    List<OrderDeteil> Order_Detail;
     List<Report> Report;

    public String getOrder_ID() {
        return Order_ID;
    }

    public void setOrder_ID(String Order_ID) {
        this.Order_ID = Order_ID;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String User_ID) {
        this.User_ID = User_ID;
    }

    public String getApproveStatus() {
        return ApproveStatus;
    }

    public void setApproveStatus(String ApproveStatus) {
        this.ApproveStatus = ApproveStatus;
    }

  

   

    public List<OrderDeteil> getOrder_Detail() {
        return Order_Detail;
    }

    public void setOrder_Detail(List<OrderDeteil> Order_Detail) {
        this.Order_Detail = Order_Detail;
    }

    public List<Report> getReport() {
        return Report;
    }

    public void setReport(List<Report> Report) {
        this.Report = Report;
    }

    public Order(String Order_ID, String User_ID, String ApproveStatus, List<OrderDeteil> Order_Detail, List<Report> Report) {
        this.Order_ID = Order_ID;
        this.User_ID = User_ID;
        this.ApproveStatus = ApproveStatus;
        this.Order_Detail = Order_Detail;
        this.Report = Report;
    }

   

    @Override
    public String toString() {
        return "Order{" + "Order_ID=" + Order_ID + ", User_ID=" + User_ID + ", ApproveStatus=" + ApproveStatus + ", Order_Detail=" + Order_Detail + ", Report=" + Report + '}';
    }

   
   
    
    
}
