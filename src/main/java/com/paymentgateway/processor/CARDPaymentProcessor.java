package com.paymentgateway.processor;

import com.paymentgateway.model.PaymentStatus;

public class CARDPaymentProcessor implements PaymentProcessorInterface {
    @Override
    public PaymentStatus processPayment(String orderId) {
        System.out.println("Processing CARD payment for orderId: " + orderId);
        return PaymentStatus.SUCCESS;
    }
}
