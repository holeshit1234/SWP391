/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.userdetails;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author User
 */
public class UserDetailsDTO implements Serializable{
    
    private int UserID;
    private int RoleID;
    private String userName;
    private String passWord;
    private String email;
    private String fullName;
    private String phone;
    private Date DOB;
    private String gender;
    private String picture;
    
            
    public UserDetailsDTO() {
    }

    public UserDetailsDTO(int UserID, int RoleID, String userName, String passWord, String email, String fullName, String phone, Date DOB, String gender, String picture) {
        this.UserID = UserID;
        this.RoleID = RoleID;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.DOB = DOB;
        this.gender = gender;
        this.picture = picture;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


  

  
}
