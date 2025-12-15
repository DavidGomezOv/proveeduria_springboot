/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.controller;

import com.example.proveeduria_api.models.FinancialRangeModel;
import com.example.proveeduria_api.service.FinancialRangeService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ranges")
@CrossOrigin(origins = "*")
public class FinancialRangeController {

    private final FinancialRangeService service;

    public FinancialRangeController(FinancialRangeService service) {
        this.service = service;
    }

    @GetMapping
    public List<FinancialRangeModel> getFinancialRanges() {
        return service.getFinancialRanges();
    }
}
