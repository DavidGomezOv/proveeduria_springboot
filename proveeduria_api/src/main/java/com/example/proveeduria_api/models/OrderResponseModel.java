/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.models;

import java.math.BigDecimal;

public class OrderResponseModel {

    private Integer orderId;
    private BigDecimal totalAmmount;
    private String comments;
    private String status;

    public OrderResponseModel(Integer orderId, BigDecimal totalAmmount, String comments, String status) {
        this.orderId = orderId;
        this.totalAmmount = totalAmmount;
        this.comments = comments;
        this.status = status;
    }

}
