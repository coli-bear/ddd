package com.study.factory;

import com.study.entity.Order;
import com.study.entity.OrderItem;

import java.util.List;
import java.util.UUID;

public class OrderFactory {
    public Order createNewOrder(UUID customerId, List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }

        Order order = new Order(customerId);
        items.forEach(order::addItem);
        return order;
    }
}
