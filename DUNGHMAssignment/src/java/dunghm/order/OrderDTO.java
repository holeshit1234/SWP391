/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunghm.order;


import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author dunghm
 */
public class OrderDTO implements Serializable{
    private int id;
    private Date datebuy;
    private String username;
    private float total;

    public OrderDTO() {
    }

    public OrderDTO(int id, Date datebuy, String username, float total) {
        this.id = id;
        this.datebuy = datebuy;
        this.username = username;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatebuy() {
        return datebuy;
    }

    public void setDatebuy(Date datebuy) {
        this.datebuy = datebuy;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
}
