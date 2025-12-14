/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.models;

import java.util.List;

public class CreateOrderRequestModel {

    private Integer userId;
    private List<CreateOrderProductsRequestModel> products;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<CreateOrderProductsRequestModel> getProducts() {
        return products;
    }

    public void setProducts(List<CreateOrderProductsRequestModel> products) {
        this.products = products;
    }

}
