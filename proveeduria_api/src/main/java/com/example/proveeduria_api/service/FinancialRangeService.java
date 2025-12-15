/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.service;

import com.example.proveeduria_api.models.FinancialRangeModel;
import com.example.proveeduria_api.repository.FinancialRangeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FinancialRangeService {

    private final FinancialRangeRepository repository;

    public FinancialRangeService(FinancialRangeRepository repository) {
        this.repository = repository;
    }

    public List<FinancialRangeModel> getFinancialRanges() {
        return repository.findAll();
    }
}
