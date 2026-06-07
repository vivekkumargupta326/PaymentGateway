package com.paymentgateway.model;

import java.math.BigDecimal;

public class OrderInfo {
    private String orderId;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus paymentStatus;

    public OrderInfo(String orderId, BigDecimal amount, String currency, PaymentStatus paymentStatus) {
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.paymentStatus = paymentStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
