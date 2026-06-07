package com.paymentgateway.model;

public class MerchantInfo {
    private String merchantId;
    private String merchantName;
    private String webhookUrl;

    public MerchantInfo(String merchantId, String merchantName, String webhookUrl) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.webhookUrl = webhookUrl;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }
}
