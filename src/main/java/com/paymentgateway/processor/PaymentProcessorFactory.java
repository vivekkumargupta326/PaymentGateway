package com.paymentgateway.processor;

import com.paymentgateway.model.PaymentMode;

public class PaymentProcessorFactory {
    public PaymentProcessorInterface getPaymentProcessor(PaymentMode paymentMode) {
        if (paymentMode == PaymentMode.UPI) {
            return new UPIPaymentProcessor();
        }

        if (paymentMode == PaymentMode.CARD) {
            return new CARDPaymentProcessor();
        }

        throw new IllegalArgumentException("Unsupported payment mode: " + paymentMode);
    }
}
