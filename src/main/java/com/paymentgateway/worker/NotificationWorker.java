package com.paymentgateway.worker;

import com.paymentgateway.model.MerchantInfo;
import com.paymentgateway.model.NotificationItem;
import com.paymentgateway.queue.NotificationQueue;
import com.paymentgateway.store.MerchantStore;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationWorker {
    private static final long POLL_INTERVAL_IN_MILLISECONDS = 100;

    private final NotificationQueue notificationQueue;
    private final MerchantStore merchantStore;
    private final HttpClient httpClient;
    private final ScheduledExecutorService executorService;

    public NotificationWorker(NotificationQueue notificationQueue, MerchantStore merchantStore) {
        this.notificationQueue = notificationQueue;
        this.merchantStore = merchantStore;
        this.httpClient = HttpClient.newHttpClient();
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void start() {
        executorService.scheduleAtFixedRate(
                this::processNextNotification,
                0,
                POLL_INTERVAL_IN_MILLISECONDS,
                TimeUnit.MILLISECONDS
        );
    }

    public void stop() {
        executorService.shutdown();
    }

    private void processNextNotification() {
        try {
            pollAndSendNotification();
        } catch (RuntimeException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private void pollAndSendNotification() {
        NotificationItem notificationItem = notificationQueue.poll();
        if (notificationItem == null) {
            return;
        }

        MerchantInfo merchantInfo = merchantStore.getById(notificationItem.getMerchantId());
        sendWebhook(merchantInfo.getWebhookUrl(), notificationItem);
    }

    private void sendWebhook(String webhookUrl, NotificationItem notificationItem) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(webhookUrl))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(buildRequestBody(notificationItem)))
                .build();

        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException exception) {
            throw new RuntimeException("Failed to send webhook for orderId: " + notificationItem.getOrderId(), exception);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Webhook notification was interrupted for orderId: " + notificationItem.getOrderId(), exception);
        }
    }

    private String buildRequestBody(NotificationItem notificationItem) {
        return "{"
                + "\"orderId\":\"" + notificationItem.getOrderId() + "\","
                + "\"merchantId\":\"" + notificationItem.getMerchantId() + "\","
                + "\"paymentStatus\":\"" + notificationItem.getPaymentStatus() + "\","
                + "\"amount\":" + notificationItem.getAmount() + ","
                + "\"currency\":\"" + notificationItem.getCurrency() + "\","
                + "\"createdAt\":\"" + notificationItem.getCreatedAt() + "\""
                + "}";
    }
}
