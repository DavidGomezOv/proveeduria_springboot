/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.proveeduria_api.repository;

import com.example.proveeduria_api.models.OrderDetailModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDetailRepository extends JpaRepository<OrderDetailModel, Long> {

    List<OrderDetailModel> findByOrder_Id(Integer idOrder);
    
}
