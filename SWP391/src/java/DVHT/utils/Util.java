/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVHT.utils;

import DHTV.order.OrderDAO;
import DHTV.order.OrderDTO;
import DHTV.order.OrderDetailDAO;
import DHTV.order.OrderDetailDTO;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author vinht
 */
public class Util {
    public static float roundingFunction (float  n){
        return (float) Math.round(n * 10) / 10;
    }
    public float getAvgRate(int productID) throws NamingException, SQLException{
        float avgPoint =0;
        int total = 0;
        //get a list of orders that have been rated
        OrderDAO daoOrder = new OrderDAO();
        daoOrder.showListOrderRated();
        List<OrderDTO> listOrder = daoOrder.getOrderList();
        //-----------------------        
        for(OrderDTO order : listOrder){
            //get list order detail of that order
            OrderDetailDAO daoOrderDetail = new OrderDetailDAO();
            daoOrderDetail.showListOrderDetail(order.getOrderID());
            List<OrderDetailDTO> listOrderDetail = daoOrderDetail.getOrderDetailList();
            //calculate points for product
            for(OrderDetailDTO dto : listOrderDetail){
                if(dto.getProductID()==productID){
                    avgPoint += order.getPoint();
                    total++;
                }
            }            
        }        
        
        return roundingFunction((float)(avgPoint/total));
    }
            
}
