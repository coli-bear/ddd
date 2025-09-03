package com.study.domain_service;

import com.study.entity.OrderItem;
import com.study.policy.DiscountPolicy;
import com.study.shared.Money;

import java.util.List;

public class PricingService {
    public Money calculateOrderTotal(List<OrderItem> items, DiscountPolicy discountPolicy) {
        Money total = items.stream()
            .map(OrderItem::inlineTotal)
            .reduce(Money.zero("KWR"), Money::add);
        return discountPolicy.apply(total);
    }
}
