/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.product;

/**
 *
 * @author mthin
 */
public class ProductDTO {
    private int productID;
    private String productName;
    private int brandID;
    private int categoryID;
    private float price;
    private boolean status;
    private String description;

    public ProductDTO() {
    }

    public ProductDTO(int productID, String productName, int brandID, int categoryID, float price, boolean status, String description) {
        this.productID = productID;
        this.productName = productName;
        this.brandID = brandID;
        this.categoryID = categoryID;
        this.price = price;
        this.status = status;
        this.description = description;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "productID=" + productID + ", productName=" + productName + ", brandID=" + brandID + ", categoryID=" + categoryID + ", price=" + price + ", status=" + status + ", description=" + description + '}';
    }
    
   
}
