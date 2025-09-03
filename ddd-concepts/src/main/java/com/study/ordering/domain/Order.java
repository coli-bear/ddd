package com.study.ordering.domain;

import com.study.shared.DomainEvent;
import com.study.shared.Money;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    public enum Status {CREATED, PLACED}

    private final UUID orderId;
    private final UUID customerId;
    private final List<OrderItem> items = new ArrayList<>();

    private Status status;
    private PaymentInfo paymentInfo;

    private final List<DomainEvent> events = new ArrayList<>();

    public Order(UUID customerId) {
        this.orderId = UUID.randomUUID();
        this.customerId = customerId;
        this.status = Status.CREATED;
    }

    public void addItem(UUID productId, int quantity, Money price) {
        if (status != Status.CREATED) throw new IllegalStateException("Cannot add item to order in status " + status);
        items.add(new OrderItem(productId, quantity, price));
    }

    public Money calculateTotal() {
        return items.stream().map(OrderItem::lineTotal)
            .reduce(Money.of(BigDecimal.ZERO, "KWR"), Money::add);
    }

    public void attachPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public void place() {
        if (items.isEmpty()) throw new IllegalStateException("Cannot place order in status " + status);
        this.status = Status.PLACED;
        events.add(new OrderPlacedEvent(orderId, customerId, calculateTotal(), Instant.now()));
    }

    public List<DomainEvent> pullEvents() {
        var copy = List.copyOf(events);
        events.clear();
        return copy;
    }

    public UUID id() {
        return orderId;
    }

    public UUID customerId() {
        return customerId;
    }

    public Status status() {
        return status;
    }

    public List<OrderItem> items() {
        return List.copyOf(items);
    }

    public record OrderPlacedEvent(UUID orderId, UUID customerId, Money total, Instant instant) implements DomainEvent {
        @Override
        public Instant occurredAt() {
            return instant;
        }
    }
}
