/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.product;

/**
 *
 * @author vinht
 */
public class ProductDetailDTO {
    private int ProductID;
    private int StoreID;
    private int SizeID;
    private int Quantity;

    public ProductDetailDTO() {
    }

    public ProductDetailDTO(int ProductID, int StoreID, int SizeID, int Quantity) {
        this.ProductID = ProductID;
        this.StoreID = StoreID;
        this.SizeID = SizeID;
        this.Quantity = Quantity;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int StoreID) {
        this.StoreID = StoreID;
    }

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int SizeID) {
        this.SizeID = SizeID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    @Override
    public String toString() {
        return "ProductDetailDTO{" + "ProductID=" + ProductID + ", StoreID=" + StoreID + ", SizeID=" + SizeID + ", Quantity=" + Quantity + '}';
    }
    
}
