/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminDAO;

/**
 *
 * @author BachDuc
 */
public class Report {

    String ReportID;
    String UserID;
    String CommentID;
    String Date;
    String Description;

    public String getReportID() {
        return ReportID;
    }

    public void setReportID(String ReportID) {
        this.ReportID = ReportID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getCommentID() {
        return CommentID;
    }

    public void setCommentID(String CommentID) {
        this.CommentID = CommentID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Report(String ReportID, String UserID, String CommentID, String Date, String Description) {
        this.ReportID = ReportID;
        this.UserID = UserID;
        this.CommentID = CommentID;
        this.Date = Date;
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Report{" + "ReportID=" + ReportID + ", UserID=" + UserID + ", CommentID=" + CommentID + ", Date=" + Date + ", Description=" + Description + '}';
    }
    
    
}
