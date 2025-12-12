/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.service;

import com.example.proveeduria_api.models.OrderModel;
import com.example.proveeduria_api.models.OrderResponseModel;
import com.example.proveeduria_api.models.RevisionModel;
import com.example.proveeduria_api.repository.NotificationsRepository;
import com.example.proveeduria_api.repository.OrdersRepository;
import com.example.proveeduria_api.repository.RevisionRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final RevisionRepository revisionRepository;
    private final NotificationsRepository notificationsRepository;

    public OrdersService(OrdersRepository ordersRepository, RevisionRepository revisionRepository, NotificationsRepository notificationsRepository) {
        this.ordersRepository = ordersRepository;
        this.revisionRepository = revisionRepository;
        this.notificationsRepository = notificationsRepository;
    }

    
    public List<OrderResponseModel> getAllOrderResponses() {

        List<OrderModel> orders = ordersRepository.findAll();

        List<OrderResponseModel> responseList = new ArrayList<>();

        for (OrderModel order : orders) {

            RevisionModel revision = order.getRevision();

            OrderResponseModel dto = new OrderResponseModel(
                    order.getId(),
                    order.getTotalAmount(),
                    revision.getComentario(),
                    revision.getStatus().getStatus()
            );

            responseList.add(dto);
        }

        return responseList;
    }
}
