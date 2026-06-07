package com.paymentgateway.service;

import com.paymentgateway.model.MerchantInfo;
import com.paymentgateway.store.MerchantStore;

public class RegisterService {
    private final MerchantStore merchantStore;

    public RegisterService(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

    public MerchantInfo registerMerchant(String merchantId, String merchantName, String webhookUrl) {
        MerchantInfo merchantInfo = new MerchantInfo(merchantId, merchantName, webhookUrl);
        return merchantStore.save(merchantInfo);
    }
}
