package com.study.ordering.app;

import com.study.ordering.domain.Order;
import com.study.ordering.domain.PaymentInfo;
import com.study.ordering.infra.OrderRepository;
import com.study.shared.EventPublisher;
import com.study.shared.Money;

import java.util.UUID;

public class OrderingService {
    private final OrderRepository orderRepository;
    private final EventPublisher eventPublisher;

    public OrderingService(OrderRepository orderRepository, EventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    public UUID createOrder(UUID customerId) {
        Order order = new Order(customerId);
        orderRepository.save(order);
        return order.id();
    }

    public void addItemToOrder(UUID orderId, UUID productId, Money priceEach, int quantity) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
        order.addItem(productId, quantity, priceEach);
        orderRepository.save(order);
    }

    public UUID placeOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
        order.place();
        orderRepository.save(order);
        order.pullEvents().forEach(eventPublisher::publish);
        return order.id();
    }
}
