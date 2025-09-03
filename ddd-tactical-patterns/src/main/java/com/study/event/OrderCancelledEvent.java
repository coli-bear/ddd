package com.study.event;

import com.study.shared.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record OrderCancelledEvent(UUID orderId, UUID customerId, Instant occurredAt) implements DomainEvent {
    public OrderCancelledEvent {
        if (occurredAt == null) {
            occurredAt = Instant.now();
        }
    }
}