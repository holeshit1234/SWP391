 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.report;

import java.util.Date;

/**
 *
 * @author vinht
 */
public class ReportDTO {
    private int ReportID;
    private int UserID;
    private  int CommentID;
    private Date Date;
    private String Description;
    private String fullName;
    private int count;
    private String comment;

    public ReportDTO(int ReportID, int UserID, int CommentID, Date Date, String Description, String fullName, int count, String comment) {
        this.ReportID = ReportID;
        this.UserID = UserID;
        this.CommentID = CommentID;
        this.Date = Date;
        this.Description = Description;
        this.fullName = fullName;
        this.count = count;
        this.comment = comment;
    }

    public ReportDTO(int CommentID, int count) {
        this.CommentID = CommentID;
        this.count = count;
    }
    public ReportDTO() {
    }

    public ReportDTO(int ReportID, int UserID, int CommentID, Date Date, String Description) {
        this.ReportID = ReportID;
        this.UserID = UserID;
        this.CommentID = CommentID;
        this.Date = Date;
        this.Description = Description;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

   

    public int getReportID() {
        return ReportID;
    }

    public void setReportID(int ReportID) {
        this.ReportID = ReportID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int CommentID) {
        this.CommentID = CommentID;
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

    @Override
    public String toString() {
        return "ReportDTO{" + "ReportID=" + ReportID + ", UserID=" + UserID + ", CommentID=" + CommentID + ", Date=" + Date + ", Description=" + Description + '}';
    }
    
}
