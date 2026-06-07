package com.paymentgateway.processor;

import com.paymentgateway.model.PaymentStatus;

public class UPIPaymentProcessor implements PaymentProcessorInterface {
    @Override
    public PaymentStatus processPayment(String orderId) {
        System.out.println("Processing UPI payment for orderId: " + orderId);
        return PaymentStatus.SUCCESS;
    }
}
