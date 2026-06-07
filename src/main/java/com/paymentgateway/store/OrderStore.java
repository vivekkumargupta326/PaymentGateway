package com.paymentgateway.store;

import com.paymentgateway.model.OrderInfo;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

public class OrderStore {
    private final Map<String, OrderInfo> orders = new ConcurrentHashMap<>();

    public OrderInfo save(OrderInfo orderInfo) {
        orders.put(orderInfo.getOrderId(), orderInfo);
        return orderInfo;
    }

    public OrderInfo getById(String orderId) {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("orderId cannot be null or blank");
        }

        OrderInfo orderInfo = orders.get(orderId);
        if (orderInfo == null) {
            throw new NoSuchElementException("Order not found for id: " + orderId);
        }

        return orderInfo;
    }

    public OrderInfo delete(String orderId) {
        return orders.remove(orderId);
    }
}
