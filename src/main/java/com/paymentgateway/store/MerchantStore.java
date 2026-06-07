package com.paymentgateway.store;

import com.paymentgateway.model.MerchantInfo;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

public class MerchantStore {
    private final Map<String, MerchantInfo> merchants = new ConcurrentHashMap<>();

    public MerchantInfo save(MerchantInfo merchantInfo) {
        merchants.put(merchantInfo.getMerchantId(), merchantInfo);
        return merchantInfo;
    }

    public MerchantInfo getById(String merchantId) {
        if (merchantId == null || merchantId.isBlank()) {
            throw new IllegalArgumentException("merchantId cannot be null or blank");
        }

        MerchantInfo merchantInfo = merchants.get(merchantId);
        if (merchantInfo == null) {
            throw new NoSuchElementException("Merchant not found for id: " + merchantId);
        }

        return merchantInfo;
    }

    public MerchantInfo delete(String merchantId) {
        return merchants.remove(merchantId);
    }
}
