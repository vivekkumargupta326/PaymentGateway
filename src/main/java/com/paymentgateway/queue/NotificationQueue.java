package com.paymentgateway.queue;

import com.paymentgateway.model.NotificationItem;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NotificationQueue {
    private final Queue<NotificationItem> notificationItems = new ConcurrentLinkedQueue<>();

    public void push(NotificationItem notificationItem) {
        notificationItems.offer(notificationItem);
    }

    public NotificationItem poll() {
        return notificationItems.poll();
    }

    public boolean isEmpty() {
        return notificationItems.isEmpty();
    }
}
