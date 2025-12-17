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
import com.example.proveeduria_api.models.OrderProductsResponseModel;
import com.example.proveeduria_api.models.OrderResponseModel;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    public void createOrder(CreateOrderRequestModel requestModel) {

        UserModel user = userRepository.findById(Long.valueOf(requestModel.getUserId().toString())).orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        BigDecimal orderTotalAmount = BigDecimal.ZERO;

        for (var product : requestModel.getProducts()) {
            ProductModel productToSave = productsRepository.findById(Long.valueOf(product.getProductId())).orElseThrow(() -> new IllegalArgumentException("Invalid product"));

            BigDecimal subtotal = productToSave.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()));

            orderTotalAmount = orderTotalAmount.add(subtotal);
        }

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

        List<UserModel> availableChiefApproverUsers = userRepository.findByRol_Id(2);

        Random random = new Random();
        int index = random.nextInt(availableChiefApproverUsers.size());

        UserModel chiefApproverUser = availableChiefApproverUsers.get(index);

        RevisionModel revisionModel = new RevisionModel();
        revisionModel.setOrder(orderModel);
        revisionModel.setUser(chiefApproverUser);
        revisionModel.setStatus(status);
        revisionModel.setComentario("Orden creada");

        revisionRepository.save(revisionModel);
    }
    
    public List<OrderResponseModel> getOrdersByUserId(Integer idUsuario) {
        List<OrderModel> ordersByUserId = ordersRepository.findByUser_Id(idUsuario);
        

        List<OrderResponseModel> orders = new ArrayList<>();

        for (var order : ordersByUserId) {
            
            List<OrderDetailModel> orderDetailsByOrderId = ordersDetailRepository.findByOrder_Id(order.getId());
            
            List<OrderProductsResponseModel> orderProducts = new ArrayList<>();
            for (var orderDetail : orderDetailsByOrderId) {
                OrderProductsResponseModel product = new OrderProductsResponseModel();
                product.setName(orderDetail.getProduct().getName());
                product.setPrice(orderDetail.getProduct().getPrice());
                product.setQuantity(orderDetail.getQuantity());
                
                orderProducts.add(product);
            }
            
            RevisionModel revisionModel = revisionRepository.findByOrder_Id(order.getId());

            OrderResponseModel orderResponseModel = new OrderResponseModel();

            orderResponseModel.setOrderId(order.getId());
            orderResponseModel.setTotalAmmount(order.getTotalAmount());
            orderResponseModel.setBuyerName(order.getUser().getNombre() + " " + order.getUser().getApellidos());
            orderResponseModel.setComments(revisionModel.getComentario());
            orderResponseModel.setStatus(revisionModel.getStatus());
            orderResponseModel.setProducts(orderProducts);

            orders.add(orderResponseModel);
        }

        return orders;
    }
    
    public List<OrderResponseModel> getOrdersByApproverId(Integer idUsuario) {
        
        List<RevisionModel> revisionsByUserId = revisionRepository.findByUser_Id(idUsuario);

        List<OrderResponseModel> orders = new ArrayList<>();

        for (var revision : revisionsByUserId) {
            OrderModel order = revision.getOrder();
            
            RevisionModel revisionModel = revisionRepository.findByOrder_Id(order.getId());

            if (revision.getStatus().getId() == 6) {
                continue;
            }
            
            OrderResponseModel orderResponseModel = new OrderResponseModel();

            orderResponseModel.setOrderId(order.getId());
            orderResponseModel.setTotalAmmount(order.getTotalAmount());
            orderResponseModel.setBuyerName(order.getUser().getNombre() + " " + order.getUser().getApellidos());
            orderResponseModel.setComments(revisionModel.getComentario());
            orderResponseModel.setStatus(revisionModel.getStatus());
            
            List<OrderDetailModel> orderDetailsByOrderId = ordersDetailRepository.findByOrder_Id(order.getId());
            
            List<OrderProductsResponseModel> orderProducts = new ArrayList<>();
            for (var orderDetail : orderDetailsByOrderId) {
                OrderProductsResponseModel product = new OrderProductsResponseModel();
                product.setName(orderDetail.getProduct().getName());
                product.setPrice(orderDetail.getProduct().getPrice());
                product.setQuantity(orderDetail.getQuantity());
                
                orderProducts.add(product);
            }
            
            orderResponseModel.setProducts(orderProducts);

            orders.add(orderResponseModel);
        }

        return orders;
    }
    
    public List<OrderResponseModel> getApprovedOrRejectedOrders() {
        
        List<RevisionModel> revisionsByUserId = revisionRepository.findAll();

        List<OrderResponseModel> orders = new ArrayList<>();

        for (var revision : revisionsByUserId) {
            OrderModel order = revision.getOrder();
            
            RevisionModel revisionModel = revisionRepository.findByOrder_Id(order.getId());

            if (revision.getStatus().getId() == 1) {
                continue;
            }
            
            OrderResponseModel orderResponseModel = new OrderResponseModel();

            orderResponseModel.setOrderId(order.getId());
            orderResponseModel.setTotalAmmount(order.getTotalAmount());
            orderResponseModel.setBuyerName(order.getUser().getNombre() + " " + order.getUser().getApellidos());
            orderResponseModel.setComments(revisionModel.getComentario());
            orderResponseModel.setStatus(revisionModel.getStatus());
            
            List<OrderDetailModel> orderDetailsByOrderId = ordersDetailRepository.findByOrder_Id(order.getId());
            
            List<OrderProductsResponseModel> orderProducts = new ArrayList<>();
            for (var orderDetail : orderDetailsByOrderId) {
                OrderProductsResponseModel product = new OrderProductsResponseModel();
                product.setName(orderDetail.getProduct().getName());
                product.setPrice(orderDetail.getProduct().getPrice());
                product.setQuantity(orderDetail.getQuantity());
                
                orderProducts.add(product);
            }
            
            orderResponseModel.setProducts(orderProducts);

            orders.add(orderResponseModel);
        }

        return orders;
    }

    public void updateOrderStatus(Integer orderId, Integer newStatusId, String comments) {

        RevisionModel revisionModel = revisionRepository.findByOrder_Id(orderId);
        StatusModel statusModel = statusRepository.findById(Long.valueOf(newStatusId)).orElseThrow(() -> new IllegalArgumentException("Invalid status"));

        // Solo cuando la orden es aprobada o rechazada por el aprobador jefe ya que debe pasar a un aprobador financiero
        if (revisionModel.getStatus().getId() == 1 && (newStatusId == 3 || newStatusId == 5)) {

            OrderModel orderModel = ordersRepository.findById(Long.valueOf(orderId)).orElseThrow(() -> new IllegalArgumentException("Invalid order"));

            FinancialRangeModel range = financialRangeRepository.findByMinLessThanEqualAndMaxGreaterThanEqual(orderModel.getTotalAmount(), orderModel.getTotalAmount()).orElseThrow(() -> new IllegalArgumentException("Invalid amount"));

            List<UserModel> availableFinancialApproverUsers = userRepository.findByRango(range);

            Random random = new Random();
            int index = random.nextInt(availableFinancialApproverUsers.size());

            UserModel financialApproverUser = availableFinancialApproverUsers.get(index);

            revisionModel.setUser(financialApproverUser);

        }

        NotificationModel notificationModel = notificationsRepository.findByOrder_Id(orderId);

        notificationModel.setStatus(statusModel);
        notificationModel.setMensaje("El estado de su orden se ha actualizado a: " + statusModel.getStatus());

        revisionModel.setStatus(statusModel);
        revisionModel.setComentario(comments);
        revisionRepository.save(revisionModel);

    }
}
