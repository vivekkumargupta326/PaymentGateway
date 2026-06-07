package com.paymentgateway.service;

import com.paymentgateway.model.NotificationItem;
import com.paymentgateway.model.OrderInfo;
import com.paymentgateway.model.PaymentMode;
import com.paymentgateway.model.PaymentStatus;
import com.paymentgateway.processor.PaymentProcessorFactory;
import com.paymentgateway.processor.PaymentProcessorInterface;
import com.paymentgateway.queue.NotificationQueue;
import com.paymentgateway.store.OrderStore;

import java.time.LocalDateTime;

public class PaymentService {
    private final OrderStore orderStore;
    private final PaymentProcessorFactory paymentProcessorFactory;
    private final NotificationQueue notificationQueue;

    public PaymentService(OrderStore orderStore, PaymentProcessorFactory paymentProcessorFactory, NotificationQueue notificationQueue) {
        this.orderStore = orderStore;
        this.paymentProcessorFactory = paymentProcessorFactory;
        this.notificationQueue = notificationQueue;
    }

    public PaymentStatus processPayment(String orderId, PaymentMode paymentMode) {
        OrderInfo orderInfo = orderStore.getById(orderId);
        PaymentProcessorInterface paymentProcessor = paymentProcessorFactory.getPaymentProcessor(paymentMode);
        PaymentStatus paymentStatus = paymentProcessor.processPayment(orderId);

        orderInfo.setPaymentStatus(paymentStatus);
        orderStore.save(orderInfo);

        NotificationItem notificationItem = new NotificationItem(
                orderInfo.getOrderId(),
                orderInfo.getMerchantId(),
                paymentStatus,
                orderInfo.getAmount(),
                orderInfo.getCurrency(),
                LocalDateTime.now()
        );
        notificationQueue.push(notificationItem);

        return paymentStatus;
    }
}
