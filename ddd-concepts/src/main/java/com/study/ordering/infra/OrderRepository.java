package com.study.ordering.infra;

import com.study.ordering.domain.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class OrderRepository {
    private final Map<UUID, Order> store = new HashMap<>();

    public void save(Order order) {
        store.put(order.id(), order);
    }

    public Optional<Order> findById(UUID orderId) {
        return Optional.ofNullable(store.get(orderId));
    }
}
