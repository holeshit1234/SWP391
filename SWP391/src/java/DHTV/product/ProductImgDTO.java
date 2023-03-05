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
public class ProductImgDTO {
    private int ImageID;
    private int ProductID;
    private String Image;

    public ProductImgDTO() {
    }

    public ProductImgDTO(int ImageID, int ProductID, String Image) {
        this.ImageID = ImageID;
        this.ProductID = ProductID;
        this.Image = Image;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int ImageID) {
        this.ImageID = ImageID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    @Override
    public String toString() {
        return "ProductImgDTO{" + "ImageID=" + ImageID + ", ProductID=" + ProductID + ", Image=" + Image + '}';
    }
    
}
