package com.study.application_service;

import com.study.entity.Order;
import com.study.entity.OrderItem;
import com.study.factory.OrderFactory;
import com.study.repository.OrderRepository;
import com.study.shared.EventPublisher;

import java.util.List;
import java.util.UUID;

public class PriceOrderService {
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final EventPublisher eventPublisher;
    public PriceOrderService(OrderRepository orderRepository, OrderFactory orderFactory, EventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
        this.eventPublisher = eventPublisher;
    }

    // Transactional 어노테이션을 이용해서 JPA 영속성 컨텍스트를 유지한다. (여기서는 의존성 주입을 하지 않아서 주석 처리)
    // @Transactional
    public UUID price(UUID customerId, List<OrderItem> orderItems) {
        Order newOrder = orderFactory.createNewOrder(customerId, orderItems);
        newOrder.place();
        orderRepository.save(newOrder);
        newOrder.pullDomainEvents().forEach(eventPublisher::publish);
        return newOrder.getId();
    }
}
