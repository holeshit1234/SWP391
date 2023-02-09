/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class CartObj implements Serializable{
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addToCart(String sku, int quantity){
        //1. check the id of product
        if(sku == null){
            return;
        }
        if(sku.trim().isEmpty()){
            return;
        }
        if(quantity ==0){
            return;
        }
        System.out.println("sku"+sku);
        //2. check exsited item
        if(this.items == null){
            this.items = new HashMap<>();
        }
        //3. check exsited item name in cart if have we plus
        if(this.items.containsKey(sku)){
            quantity += this.items.get(sku);
        }
        //4. update items
        items.put(sku, quantity);
    }
    public void removeFromCart(String sku ){
        //1. check the exsited
        if(sku == null){
            return;
        }
        //2. check the exsited item
        if(this.items.containsKey(sku)){
            this.items.remove(sku);
            if(this.items.isEmpty()){
                this.items = null;
            }
        }
    }
}
