/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proveeduria_api.service;

import com.example.proveeduria_api.models.CreateOrderRequestModel;
import com.example.proveeduria_api.models.FinancialRangeModel;
import com.example.proveeduria_api.models.NotificationModel;
import com.example.proveeduria_api.models.OrderDetailModel;
import com.example.proveeduria_api.models.OrderModel;
import com.example.proveeduria_api.models.ProductModel;
import com.example.proveeduria_api.models.RevisionModel;
import com.example.proveeduria_api.models.StatusModel;
import com.example.proveeduria_api.models.UserModel;
import com.example.proveeduria_api.repository.FinancialRangeRepository;
import com.example.proveeduria_api.repository.NotificationsRepository;
import com.example.proveeduria_api.repository.OrdersDetailRepository;
import com.example.proveeduria_api.repository.OrdersRepository;
import com.example.proveeduria_api.repository.ProductsRepository;
import com.example.proveeduria_api.repository.RevisionRepository;
import com.example.proveeduria_api.repository.StatusRepository;
import com.example.proveeduria_api.repository.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final RevisionRepository revisionRepository;
    private final NotificationsRepository notificationsRepository;
    private final OrdersDetailRepository ordersDetailRepository;

    private final FinancialRangeRepository financialRangeRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;

    public OrdersService(OrdersRepository ordersRepository, RevisionRepository revisionRepository, NotificationsRepository notificationsRepository, FinancialRangeRepository financialRangeRepository, StatusRepository statusRepository, UserRepository userRepository, OrdersDetailRepository ordersDetailRepository, ProductsRepository productsRepository) {
        this.ordersRepository = ordersRepository;
        this.revisionRepository = revisionRepository;
        this.notificationsRepository = notificationsRepository;
        this.financialRangeRepository = financialRangeRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
        this.ordersDetailRepository = ordersDetailRepository;
        this.productsRepository = productsRepository;
    }

    public List<OrderModel> getOrdersByUserId(Integer idUsuario) {
        return ordersRepository.findByUser_Id(idUsuario);
    }

    public Boolean createOrder(CreateOrderRequestModel requestModel) {

        UserModel user = userRepository.findById(Long.valueOf(requestModel.getUserId().toString())).orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        BigDecimal orderTotalAmount = BigDecimal.ZERO;

        for (var product : requestModel.getProducts()) {
            ProductModel productToSave = productsRepository.findById(Long.valueOf(product.getProductId())).orElseThrow(() -> new IllegalArgumentException("Invalid product"));

            BigDecimal subtotal = productToSave.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()));

            orderTotalAmount = orderTotalAmount.add(subtotal);
        }

        System.out.println("TOTAL AMOUNT " + orderTotalAmount);

        FinancialRangeModel range = financialRangeRepository.findByMinLessThanEqualAndMaxGreaterThanEqual(orderTotalAmount, orderTotalAmount).orElseThrow(() -> new IllegalArgumentException("Invalid amount"));
        StatusModel status = statusRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("Status error"));

        OrderModel orderModel = new OrderModel();
        orderModel.setTotalAmount(orderTotalAmount);
        orderModel.setFinancialRangeModel(range);
        orderModel.setUser(user);

        ordersRepository.save(orderModel);

        requestModel.getProducts().forEach((product) -> {
            ProductModel productToSave = productsRepository.findById(Long.valueOf(product.getProductId())).orElseThrow(() -> new IllegalArgumentException("Invalid product"));

            OrderDetailModel orderDetailModel = new OrderDetailModel();
            orderDetailModel.setOrder(orderModel);
            orderDetailModel.setProduct(productToSave);
            orderDetailModel.setQuantity(product.getQuantity());

            ordersDetailRepository.save(orderDetailModel);
        });

        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setUser(user);
        notificationModel.setOrder(orderModel);
        notificationModel.setStatus(status);
        notificationModel.setMensaje("Orden creada exitosamente!");

        notificationsRepository.save(notificationModel);

        RevisionModel revisionModel = new RevisionModel();
        revisionModel.setOrder(orderModel);
        revisionModel.setUser(user);
        revisionModel.setStatus(status);
        revisionModel.setComentario("Orden creada");

        revisionRepository.save(revisionModel);

        return true;
    }
}
