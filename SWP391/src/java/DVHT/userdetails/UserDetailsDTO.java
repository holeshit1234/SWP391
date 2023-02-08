/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.userdetails;

import java.io.Serializable;

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

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(int UserID, int RoleID, String userName, String passWord, 
            String Email, String fullName, String Phone) {
        this.UserID = UserID;
        this.RoleID = RoleID;
        this.userName = userName;
        this.passWord = passWord;
        this.email = Email;
        this.fullName = fullName;
        this.phone = Phone;
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

    
    
}
