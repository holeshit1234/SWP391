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
public class UserDetailsErr implements Serializable{
    private String wrongUserNamePassWord;
    private String emptyUserNamePassWord;

    public UserDetailsErr() {
    }

    public UserDetailsErr(String wrongUserNamePassWord, String emptyUserNamePassWord) {
        this.wrongUserNamePassWord = wrongUserNamePassWord;
        this.emptyUserNamePassWord = emptyUserNamePassWord;
    }

    public String getWrongUserNamePassWord() {
        return wrongUserNamePassWord;
    }

    public void setWrongUserNamePassWord(String wrongUserNamePassWord) {
        this.wrongUserNamePassWord = wrongUserNamePassWord;
    }

    public String getEmptyUserNamePassWord() {
        return emptyUserNamePassWord;
    }

    public void setEmptyUserNamePassWord(String emptyUserNamePassWord) {
        this.emptyUserNamePassWord = emptyUserNamePassWord;
    }
    
    
}
