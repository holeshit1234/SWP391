/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.comment;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author vinht
 */
public class CommentDTO implements Serializable{

    private int CommentID;
    private int UserID;
    private int ProductID;
    private Date Date;
    private String Description;
    private int Point;
    private boolean status;
    private String fullName;

    public CommentDTO() {
    }

    public CommentDTO(int CommentID, int UserID, int ProductID, Date Date, String Description, int Point, boolean status) {
        this.CommentID = CommentID;
        this.UserID = UserID;
        this.ProductID = ProductID;
        this.Date = Date;
        this.Description = Description;
        this.Point = Point;
        this.status = status;
    }

    public CommentDTO(int CommentID, int UserID, String Description, String fullName) {
        this.CommentID = CommentID;
        this.UserID = UserID;
        this.Description = Description;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int CommentID) {
        this.CommentID = CommentID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getPoint() {
        return Point;
    }

    public void setPoint(int Point) {
        this.Point = Point;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CommentDTO{" + "CommentID=" + CommentID + ", UserID=" + UserID + ", ProductID=" + ProductID + ", Date=" + Date + ", Description=" + Description + ", Point=" + Point + ", status=" + status + '}';
    }

}
