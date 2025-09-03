package com.study.ordering.domain;

import com.study.shared.Money;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {
    private final UUID productId;
    private final int quantity;
    private final Money price;

    public OrderItem(UUID productId, int quantity, Money price) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than zero");
        if (price.amount().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Price must be greater than zero");
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public Money lineTotal() {
        return Money.of(price.amount().multiply(BigDecimal.valueOf(quantity)), price.currency());
    }

    public UUID productId() {
        return productId;
    }

    public int quantity() {
        return quantity;
    }

    public Money price() {
        return price;
    }
}
