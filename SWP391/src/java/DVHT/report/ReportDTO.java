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

    public ReportDTO() {
    }

    public ReportDTO(int ReportID, int UserID, int CommentID, Date Date, String Description) {
        this.ReportID = ReportID;
        this.UserID = UserID;
        this.CommentID = CommentID;
        this.Date = Date;
        this.Description = Description;
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
