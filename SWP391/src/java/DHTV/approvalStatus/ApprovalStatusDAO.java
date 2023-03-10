/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.approvalStatus;

import DVHT.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author vinht
 */
public class ApprovalStatusDAO implements Serializable{
    private List<ApprovalStatusDTO> list;

    public List<ApprovalStatusDTO> getList() {
        return list;
    }

    public void getData()
            throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
         this.list = new ArrayList<>();
        try {
            //1 get comnnection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2 sql commands
                String sql = "select * "
                        + "from  [ApprovalStatus] ";
                // 3 stm create
                stm = con.prepareStatement(sql);
              

                //execute query  
                rs = stm.executeQuery();
                //5 process
                while (rs.next()) {
                    int approvalStatusID = rs.getInt("ApprovalStatusID");
                    String approvalStatus = rs.getNString("ApprovalStatus");
                    //create dto
                    ApprovalStatusDTO dto = new ApprovalStatusDTO(approvalStatusID, approvalStatus);
                    System.out.println(dto);

                    //add item to dto
                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }//end the list no exsited
                    this.list.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

}
