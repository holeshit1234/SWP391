/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.product;

/**
 *
 * @author dunghm
 */
public class ProductErr {
    private String nameLengthErr;
    private String descriptionLengthErr;
    private String negativePriceErr;
    private String negativeQuantityErr;
    private String skuIsExisted;
    private String skuLengthErr;
    

    public ProductErr() {
    }

    public ProductErr(String nameLengthErr, String descriptionLengthErr, String negativePriceErr, String negativeQuantityErr, String skuIsExisted, String skuLengthErr) {
        this.nameLengthErr = nameLengthErr;
        this.descriptionLengthErr = descriptionLengthErr;
        this.negativePriceErr = negativePriceErr;
        this.negativeQuantityErr = negativeQuantityErr;
        this.skuIsExisted = skuIsExisted;
        this.skuLengthErr = skuLengthErr;
    }

    public String getNameLengthErr() {
        return nameLengthErr;
    }

    public void setNameLengthErr(String nameLengthErr) {
        this.nameLengthErr = nameLengthErr;
    }

    public String getDescriptionLengthErr() {
        return descriptionLengthErr;
    }

    public void setDescriptionLengthErr(String descriptionLengthErr) {
        this.descriptionLengthErr = descriptionLengthErr;
    }

    public String getNegativePriceErr() {
        return negativePriceErr;
    }

    public void setNegativePriceErr(String negativePriceErr) {
        this.negativePriceErr = negativePriceErr;
    }

    public String getNegativeQuantityErr() {
        return negativeQuantityErr;
    }

    public void setNegativeQuantityErr(String negativeQuantityErr) {
        this.negativeQuantityErr = negativeQuantityErr;
    }

    public String getSkuIsExisted() {
        return skuIsExisted;
    }

    public void setSkuIsExisted(String skuIsExisted) {
        this.skuIsExisted = skuIsExisted;
    }

    public String getSkuLengthErr() {
        return skuLengthErr;
    }

    public void setSkuLengthErr(String skuLengthErr) {
        this.skuLengthErr = skuLengthErr;
    }
 
   
    
    
}
