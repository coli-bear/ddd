package com.study.delivering.domain;

import com.study.shared.DomainEvent;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Delivery {
    public enum Status {READY, DELIVERING, DELIVERED;

        public boolean isReady() {
            return this == READY;
        }
    }
    private final UUID deliveryId;
    private final UUID orderId;
    private final DeliveryAddress address;
    private Status status;

    private final List<DomainEvent> events = new ArrayList<>();

    public Delivery(UUID orderId, DeliveryAddress address) {
        this.deliveryId = UUID.randomUUID();
        this.orderId = orderId;
        this.address = address;
        this.status = Status.READY;
        this.events.add(new DeliveryReadyEvent(deliveryId, orderId, Instant.now()));
    }

    public void deliveryStart() {
        if (!this.status.isReady()) {
            throw new IllegalStateException("Delivery is not ready to start");
        }
        this.status = Status.DELIVERING;
        this.events.add(new DeliveryStartEvent(deliveryId, orderId, Instant.now()));
    }
    public void deliveryComplete() {
        if (this.status != Status.DELIVERING) {
            throw new IllegalStateException("Delivery is not in progress");
        }
        this.status = Status.DELIVERED;
        this.events.add(new DeliveryCompleteEvent(deliveryId, orderId, Instant.now()));
    }
    public UUID id() {
        return deliveryId;
    }
    public UUID orderId() {
        return orderId;
    }
    public Status status() {
        return status;
    }
    public DeliveryAddress address() {
        return address;
    }

    public List<DomainEvent> pullEvents() {
        var copy = List.copyOf(events);
        events.clear();
        return copy;
    }

    public record DeliveryReadyEvent(UUID deliveryId, UUID orderId, Instant readyAt) implements DomainEvent {
        @Override
        public Instant occurredAt() {
            return readyAt;
        }
    }

    public record DeliveryStartEvent(UUID deliveryId, UUID orderId, Instant startAt) implements DomainEvent {
        @Override
        public Instant occurredAt() {
            return startAt;
        }
    }

    public record DeliveryCompleteEvent(UUID deliveryId, UUID orderId, Instant completedAt) implements DomainEvent {
        @Override
        public Instant occurredAt() {
            return completedAt;
        }
    }
}
