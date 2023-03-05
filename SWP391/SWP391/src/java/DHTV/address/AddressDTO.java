/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.address;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class AddressDTO implements Serializable {

   private int addressID; // match AddressID
    private int userID; //match UserID
    private String Provice; //match Provice
    private String Ward; // match Ward
    private String Street; //match Street
    private String District;
    private boolean status;

    public AddressDTO() {
    }

    public AddressDTO(int addressID, int userID, String Provice, String Ward, String Street, String District, boolean status) {
        this.addressID = addressID;
        this.userID = userID;
        this.Provice = Provice;
        this.Ward = Ward;
        this.Street = Street;
        this.District = District;
        this.status = status;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getProvice() {
        return Provice;
    }

    public void setProvice(String Provice) {
        this.Provice = Provice;
    }

    public String getWard() {
        return Ward;
    }

    public void setWard(String Ward) {
        this.Ward = Ward;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String District) {
        this.District = District;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AddressDTO{" + "addressID=" + addressID + ", userID=" + userID + ", Provice=" + Provice + ", Ward=" + Ward + ", Street=" + Street + ", District=" + District + ", status=" + status + '}';
    }
    
}
