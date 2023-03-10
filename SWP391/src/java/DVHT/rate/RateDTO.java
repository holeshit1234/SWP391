/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.rate;

import java.io.Serializable;

/**
 *
 * @author vinht
 */
public class RateDTO implements Serializable{
    private int RateID;
    private int ProductID;
    private int Point;

    public RateDTO() {
    }

    public RateDTO(int RateID, int ProductID, int Point) {
        this.RateID = RateID;
        this.ProductID = ProductID;
        this.Point = Point;
    }

    public int getRateID() {
        return RateID;
    }

    public void setRateID(int RateID) {
        this.RateID = RateID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getPoint() {
        return Point;
    }

    public void setPoint(int Point) {
        this.Point = Point;
    }

    @Override
    public String toString() {
        return "RateDTO{" + "RateID=" + RateID + ", ProductID=" + ProductID + ", Point=" + Point + '}';
    }
    
}
