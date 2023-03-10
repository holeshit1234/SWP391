/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.size;

import java.io.Serializable;

/**
 *
 * @author vinht
 */
public class SizeDTO implements Serializable{
    private int SizeID;
    private  String SizeName;

    public SizeDTO() {
    }

    public SizeDTO(int SizeID, String SizeName) {
        this.SizeID = SizeID;
        this.SizeName = SizeName;
    }

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int SizeID) {
        this.SizeID = SizeID;
    }

    public String getSizeName() {
        return SizeName;
    }

    public void setSizeName(String SizeName) {
        this.SizeName = SizeName;
    }

    @Override
    public String toString() {
        return "SizeDTO{" + "SizeID=" + SizeID + ", SizeName=" + SizeName + '}';
    }
    
}
