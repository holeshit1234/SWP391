/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminDAO;

/**
 *
 * @author BachDuc
 */
public class OrderDeteil {

    String OrderID;
    String ProductID;
    String SizeID, Quantity;
    String Price;
    String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getSizeID() {
        return SizeID;
    }

    public void setSizeID(String SizeID) {
        this.SizeID = SizeID;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return "OrderDeteil{" + "OrderID=" + OrderID + ", ProductID=" + ProductID + ", SizeID=" + SizeID + ", Quantity=" + Quantity + ", Price=" + Price + '}';
    }

    public OrderDeteil(String OrderID, String ProductID, String SizeID, String Quantity, String Price) {
        this.OrderID = OrderID;
        this.ProductID = ProductID;
        this.SizeID = SizeID;
        this.Quantity = Quantity;
        this.Price = Price;
    }
    
}
