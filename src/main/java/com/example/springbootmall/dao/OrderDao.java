package com.example.springbootmall.dao;

import com.example.springbootmall.dto.OrderQueryParam;
import com.example.springbootmall.model.Order;
import com.example.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer countOrder(OrderQueryParam orderQueryParam);

    List<Order> getOrders(OrderQueryParam orderQueryParam);
}
