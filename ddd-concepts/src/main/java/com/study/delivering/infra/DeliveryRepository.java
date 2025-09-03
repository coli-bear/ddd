package com.study.delivering.infra;

import com.study.delivering.domain.Delivery;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class DeliveryRepository {
    private final Map<UUID, Delivery> store = new HashMap<>();
    public void save(Delivery delivery) {
        store.put(delivery.id(), delivery);
    }
    public Optional<Delivery> findByOrderId(UUID orderId) {
        return store.values().stream()
            .filter(delivery -> delivery.orderId().equals(orderId))
            .findFirst();
    }
}
