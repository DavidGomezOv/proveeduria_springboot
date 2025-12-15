/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.controller;

import com.example.proveeduria_api.models.CreateOrderRequestModel;
import com.example.proveeduria_api.models.OrderResponseModel;
import com.example.proveeduria_api.service.OrdersService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrdersController {

    private final OrdersService service;

    public OrdersController(OrdersService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequestModel requestModel) {
        try {
            service.createOrder(requestModel);
            return ResponseEntity.status(201).body("Orden creada");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error al crear orden: " + ex.getMessage());
        }
    }

    @GetMapping("/{idUsuario}")
    public List<OrderResponseModel> getOrdersByUserId(@PathVariable Integer idUsuario) {
        return service.getOrdersByUserId(idUsuario);
    }
    
    @PutMapping("/{idOrder}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Integer idOrder, @RequestParam("status") Integer statusId, @RequestParam("comments") String comments) {
        try {
            service.updateOrderStatus(idOrder, statusId, comments);
            return ResponseEntity.status(201).body("Orden actualizada");
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error al actualizar el estado de la orden: " + ex.getMessage());
        }
    }
}
