package com.study.event;

import com.study.shared.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record OrderPlacedEvent(UUID orderId, UUID customerId, Instant occurredAt) implements DomainEvent {
    public OrderPlacedEvent {
        if (occurredAt == null) {
            occurredAt = Instant.now();
        }
    }
}