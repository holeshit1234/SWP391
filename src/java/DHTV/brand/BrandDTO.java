/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.brand;

/**
 *
 * @author vinht
 */
public class BrandDTO {
    private int BrandId;
    private String BrandName;
    private boolean Status;
    private String Description;

    public BrandDTO() {
    }

    public BrandDTO(int BrandId, String BrandName, boolean Status, String Description) {
        this.BrandId = BrandId;
        this.BrandName = BrandName;
        this.Status = Status;
        this.Description = Description;
    }

    public int getBrandId() {
        return BrandId;
    }

    public void setBrandId(int BrandId) {
        this.BrandId = BrandId;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "BrandDTO{" + "BrandId=" + BrandId + ", BrandName=" + BrandName + ", Status=" + Status + ", Description=" + Description + '}';
    }
    
}
