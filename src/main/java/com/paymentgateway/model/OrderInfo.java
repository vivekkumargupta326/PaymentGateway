package com.paymentgateway.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderInfo {
    private String orderId;
    private String merchantId;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime createdAt;

    public OrderInfo(String orderId, String merchantId, BigDecimal amount, String currency, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.merchantId = merchantId;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = createdAt;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
