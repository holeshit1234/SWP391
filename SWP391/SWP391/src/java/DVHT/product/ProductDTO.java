/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.product;

/**
 *
 * @author mthin
 */
public class ProductDTO {
    private int productID;
    private String productName;
    private String image;
    private String categoryName;
    private String brandName;
    private boolean gender;
    private double  price;
    private int quantity;

    public ProductDTO() {
    }

    public ProductDTO(int productID, String productName, String image, String categoryName, String brandName, boolean gender, double price, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.categoryName = categoryName;
        this.brandName = brandName;
        this.gender = gender;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDTO(String sku, double price, int quantity) {
       this.productName =sku;
       this.price=price;
       this.quantity=quantity;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

 

   
}
