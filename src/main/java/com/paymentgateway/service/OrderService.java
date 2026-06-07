package com.paymentgateway.service;

import com.paymentgateway.model.OrderInfo;
import com.paymentgateway.model.PaymentStatus;
import com.paymentgateway.store.OrderStore;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderService {
    private final OrderStore orderStore;

    public OrderService(OrderStore orderStore) {
        this.orderStore = orderStore;
    }

    public OrderInfo createOrder(String orderId, String merchantId, BigDecimal amount, String currency, LocalDateTime createdAt) {
        OrderInfo orderInfo = new OrderInfo(orderId, merchantId, amount, currency, PaymentStatus.PENDING, createdAt);
        return orderStore.save(orderInfo);
    }
}
