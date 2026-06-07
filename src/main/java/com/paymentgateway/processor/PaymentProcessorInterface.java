package com.paymentgateway.processor;

import com.paymentgateway.model.PaymentStatus;

public interface PaymentProcessorInterface {
    PaymentStatus processPayment(String orderId);
}
