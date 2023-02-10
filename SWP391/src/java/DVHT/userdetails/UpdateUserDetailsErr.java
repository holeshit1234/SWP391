/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.userdetails;

/**
 *
 * @author User
 */
public class UpdateUserDetailsErr {
    private String notEnoughWordFullName;
    private String notEnoughWordPassWord;
    private String emptyEmail;
    private String emptyPhone;
    private String emptyStreet;
    private String emptyPronvince;
    private String emptyDistrict;
    private String emptyWard;
    private String emptyDOB;

    public UpdateUserDetailsErr() {
    }

    public UpdateUserDetailsErr(String notEnoughWordFullName, String notEnoughWordPassWord, String emptyEmail, String emptyPhone, String emptyStreet, String emptyPronvince, String emptyDistrict, String emptyWard, String emptyDOB) {
        this.notEnoughWordFullName = notEnoughWordFullName;
        this.notEnoughWordPassWord = notEnoughWordPassWord;
        this.emptyEmail = emptyEmail;
        this.emptyPhone = emptyPhone;
        this.emptyStreet = emptyStreet;
        this.emptyPronvince = emptyPronvince;
        this.emptyDistrict = emptyDistrict;
        this.emptyWard = emptyWard;
        this.emptyDOB = emptyDOB;
    }

    public String getNotEnoughWordFullName() {
        return notEnoughWordFullName;
    }

    public void setNotEnoughWordFullName(String notEnoughWordFullName) {
        this.notEnoughWordFullName = notEnoughWordFullName;
    }

    public String getNotEnoughWordPassWord() {
        return notEnoughWordPassWord;
    }

    public void setNotEnoughWordPassWord(String notEnoughWordPassWord) {
        this.notEnoughWordPassWord = notEnoughWordPassWord;
    }

    public String getEmptyEmail() {
        return emptyEmail;
    }

    public void setEmptyEmail(String emptyEmail) {
        this.emptyEmail = emptyEmail;
    }

    public String getEmptyPhone() {
        return emptyPhone;
    }

    public void setEmptyPhone(String emptyPhone) {
        this.emptyPhone = emptyPhone;
    }

    public String getEmptyStreet() {
        return emptyStreet;
    }

    public void setEmptyStreet(String emptyStreet) {
        this.emptyStreet = emptyStreet;
    }

    public String getEmptyPronvince() {
        return emptyPronvince;
    }

    public void setEmptyPronvince(String emptyPronvince) {
        this.emptyPronvince = emptyPronvince;
    }

    public String getEmptyDistrict() {
        return emptyDistrict;
    }

    public void setEmptyDistrict(String emptyDistrict) {
        this.emptyDistrict = emptyDistrict;
    }

    public String getEmptyWard() {
        return emptyWard;
    }

    public void setEmptyWard(String emptyWard) {
        this.emptyWard = emptyWard;
    }

    public String getEmptyDOB() {
        return emptyDOB;
    }

    public void setEmptyDOB(String emptyDOB) {
        this.emptyDOB = emptyDOB;
    }
    
    
   
    
}
