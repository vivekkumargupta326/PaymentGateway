package com.paymentgateway.service;

import com.paymentgateway.model.NotificationItem;
import com.paymentgateway.model.OrderInfo;
import com.paymentgateway.model.PaymentInfo;
import com.paymentgateway.model.PaymentMode;
import com.paymentgateway.model.PaymentStatus;
import com.paymentgateway.processor.PaymentProcessorFactory;
import com.paymentgateway.processor.PaymentProcessorInterface;
import com.paymentgateway.queue.NotificationQueue;
import com.paymentgateway.store.OrderStore;
import com.paymentgateway.store.PaymentStore;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentService {
    private final OrderStore orderStore;
    private final PaymentStore paymentStore;
    private final PaymentProcessorFactory paymentProcessorFactory;
    private final NotificationQueue notificationQueue;

    public PaymentService(OrderStore orderStore, PaymentStore paymentStore, PaymentProcessorFactory paymentProcessorFactory, NotificationQueue notificationQueue) {
        this.orderStore = orderStore;
        this.paymentStore = paymentStore;
        this.paymentProcessorFactory = paymentProcessorFactory;
        this.notificationQueue = notificationQueue;
    }

    public PaymentStatus processPayment(String orderId, PaymentMode paymentMode, String idempotencyKey) {
        PaymentInfo existingPaymentInfo = paymentStore.getByIdempotencyKey(idempotencyKey);
        if (existingPaymentInfo != null) {
            return existingPaymentInfo.getPaymentStatus();
        }

        OrderInfo orderInfo = orderStore.getById(orderId);
        PaymentInfo paymentInfo = new PaymentInfo(
                UUID.randomUUID().toString(),
                orderInfo.getOrderId(),
                orderInfo.getMerchantId(),
                idempotencyKey,
                orderInfo.getAmount(),
                LocalDateTime.now(),
                PaymentStatus.INITIATED
        );
        paymentStore.save(paymentInfo);

        PaymentProcessorInterface paymentProcessor = paymentProcessorFactory.getPaymentProcessor(paymentMode);
        PaymentStatus paymentStatus = paymentProcessor.processPayment(orderId);

        paymentInfo.setPaymentStatus(paymentStatus);
        paymentStore.save(paymentInfo);

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
