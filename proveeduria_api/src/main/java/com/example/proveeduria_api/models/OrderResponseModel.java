/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.models;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponseModel {

    private Integer orderId;
    private BigDecimal totalAmmount;
    private String comments;
    private StatusModel status;
    private String buyerName;
    private List<OrderProductsResponseModel> products;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalAmmount() {
        return totalAmmount;
    }

    public void setTotalAmmount(BigDecimal totalAmmount) {
        this.totalAmmount = totalAmmount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public List<OrderProductsResponseModel> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductsResponseModel> products) {
        this.products = products;
    }

}
