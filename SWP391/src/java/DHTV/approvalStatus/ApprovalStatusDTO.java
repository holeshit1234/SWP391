/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.approvalStatus;

import java.io.Serializable;

/**
 *
 * @author vinht
 */
public class ApprovalStatusDTO implements Serializable{
    private int approvalStatusID;
    private String approvalStatus;

    public ApprovalStatusDTO() {
    }

    public ApprovalStatusDTO(int approvalStatusID, String approvalStatus) {
        this.approvalStatusID = approvalStatusID;
        this.approvalStatus = approvalStatus;
    }

    public int getApprovalStatusID() {
        return approvalStatusID;
    }

    public void setApprovalStatusID(int approvalStatusID) {
        this.approvalStatusID = approvalStatusID;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    @Override
    public String toString() {
        return "ApprovalStatusDTO{" + "approvalStatusID=" + approvalStatusID + ", approvalStatus=" + approvalStatus + '}';
    }
    
}
