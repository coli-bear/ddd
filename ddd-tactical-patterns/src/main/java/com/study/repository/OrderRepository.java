package com.study.repository;

import com.study.entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    void save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findByCustomerId(UUID customerId);
}
