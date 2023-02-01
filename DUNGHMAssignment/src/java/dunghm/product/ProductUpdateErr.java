/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.product;

import java.io.Serializable;

/**
 *
 * @author dunghm
 */
public class ProductUpdateErr implements Serializable {
    
    private String negativeErr;
    private String wrongFormatErr;

    public ProductUpdateErr() {
    }

    public ProductUpdateErr(String negativeErr, String wrongFormatErr) {
        this.negativeErr = negativeErr;
        this.wrongFormatErr = wrongFormatErr;
    }

    public String getNegativeErr() {
        return negativeErr;
    }

    public void setNegativeErr(String negativeErr) {
        this.negativeErr = negativeErr;
    }

    public String getWrongFormatErr() {
        return wrongFormatErr;
    }

    public void setWrongFormatErr(String wrongFormatErr) {
        this.wrongFormatErr = wrongFormatErr;
    }

   
    
    
 
}
