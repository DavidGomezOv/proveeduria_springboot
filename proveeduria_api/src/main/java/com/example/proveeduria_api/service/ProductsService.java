/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.service;

import com.example.proveeduria_api.models.ProductModel;
import com.example.proveeduria_api.repository.ProductsRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    private final ProductsRepository repository;

    public ProductsService(ProductsRepository repository) {
        this.repository = repository;
    }

    public List<ProductModel> getAll() {
        return repository.findAll();
    }

}
