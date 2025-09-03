package com.study.entity;

import com.study.event.OrderCancelledEvent;
import com.study.event.OrderPlacedEvent;
import com.study.shared.DomainEvent;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    public enum Status {CREATED, PLACED, CANCELLED}

    private final UUID orderId;
    private final UUID customerId;
    private Status status;
    private List<OrderItem> items;
    private List<DomainEvent> events;

    public Order(UUID customerId) {
        this.orderId = UUID.randomUUID();
        this.customerId = customerId;
        this.status = Status.CREATED;
        this.items = new ArrayList<>();
    }

    /**
     * 주문 항목을 추가합니다.
     * 주문 상태가 CREATED가 아니면 예외를 발생시킵니다.
     *
     * @param item 추가할 주문 항목
     */
    public void addItem(OrderItem item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        if (status != Status.CREATED) throw new IllegalStateException("Cannot add item to order in status " + status);
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        items.add(item);
    }

    public UUID getId() {
        return orderId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    /**
     * 주문을 확정하고 상태를 PLACED로 변경합니다.
     * 주문 항목이 하나도 없으면 예외를 발생시킵니다.
     * 주문이 성공적으로 확정되면 OrderPlacedEvent 도메인 이벤트를 생성
     */
    public void place() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Order must have at least one item");
        }
        this.status = Status.PLACED;
        events.add(new OrderPlacedEvent(this.orderId, this.customerId, Instant.now()));
    }

    /**
     * 주문을 취소하고 상태를 CANCELLED로 변경합니다.
     * 이미 취소된 주문을 다시 취소하려고 하면 예외를 발생시
     * 킵니다.
     * 주문이 성공적으로 취소되면 OrderCancelledEvent 도메인 이벤트를 생성
     */
    public void cancel() {
        if (this.status == Status.CANCELLED) {
            throw new IllegalStateException("Order is already cancelled");
        }
        this.status = Status.CANCELLED;
        // 주문 취소 이벤트 등록
        events.add(new OrderCancelledEvent(this.orderId, this.customerId, Instant.now()));
    }

    /**
     * 도메인 이벤트를 외부로 전달하고 내부 이벤트 리스트를 초기화합니다.
     *
     * @return 복사된 도메인 이벤트 리스트
     */
    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> copyEvents = List.copyOf(events);
        events.clear();
        return copyEvents;
    }
}
