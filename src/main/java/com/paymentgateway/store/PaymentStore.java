package com.paymentgateway.store;

import com.paymentgateway.model.PaymentInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentStore {
    private final Map<String, PaymentInfo> payments = new ConcurrentHashMap<>();

    public PaymentInfo save(PaymentInfo paymentInfo) {
        payments.put(paymentInfo.getIdempotencyKey(), paymentInfo);
        return paymentInfo;
    }

    public PaymentInfo getByIdempotencyKey(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("idempotencyKey cannot be null or blank");
        }

        return payments.get(idempotencyKey);
    }

    public PaymentInfo delete(String idempotencyKey) {
        return payments.remove(idempotencyKey);
    }
}
