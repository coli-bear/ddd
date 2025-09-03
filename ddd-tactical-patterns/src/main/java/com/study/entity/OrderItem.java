package com.study.entity;

import com.study.shared.Money;

import java.math.BigDecimal;import java.util.UUID;

public record OrderItem(UUID productId, int quantity, Money price) {
    public Money inlineTotal() {
        return Money.of(price.amount().multiply(BigDecimal.valueOf(quantity)), price.currency());
    }}
