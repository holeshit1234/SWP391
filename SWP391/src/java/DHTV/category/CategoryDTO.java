/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.category;

/**
 *
 * @author vinht
 */
public class CategoryDTO {
    private  int CategoryID;
    private  String CategoryName;
    private  String Gender;
    private String Description;
    private boolean status;

    public CategoryDTO() {
    }

    public CategoryDTO(int CategoryID, String CategoryName, String Gender, String Description, boolean status) {
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
        this.Gender = Gender;
        this.Description = Description;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" + "CategoryID=" + CategoryID + ", CategoryName=" + CategoryName + ", Gender=" + Gender + ", Description=" + Description + '}';
    }
    
}
