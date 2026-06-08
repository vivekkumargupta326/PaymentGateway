package com.paymentgateway.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentInfo {
    private String paymentId;
    private String orderId;
    private String merchantId;
    private String idempotencyKey;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private PaymentStatus paymentStatus;

    public PaymentInfo(String paymentId, String orderId, String merchantId, String idempotencyKey, BigDecimal amount, LocalDateTime createdAt, PaymentStatus paymentStatus) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.merchantId = merchantId;
        this.idempotencyKey = idempotencyKey;
        this.amount = amount;
        this.createdAt = createdAt;
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
