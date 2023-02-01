/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.registration;

/**
 *
 * @author dunghm
 */
public class RegistrationDTO {
     private String username; // map username database
    private String password;//map password database
    private String fullName;// map lastname database
    private boolean role;// map isAdmin database

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String password, 
            String fullName, boolean role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }
    
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the role
     */
    public boolean isRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(boolean role) {
        this.role = role;
    }
}
