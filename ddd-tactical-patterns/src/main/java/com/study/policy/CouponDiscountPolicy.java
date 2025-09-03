package com.study.policy;

import com.study.shared.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CouponDiscountPolicy implements DiscountPolicy {
    private final BigDecimal discountRate;

    public CouponDiscountPolicy(int discountRate) {
        if (discountRate < 0 || discountRate > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        this.discountRate = BigDecimal.valueOf(discountRate)
            .divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP);
    }

    @Override
    public Money apply(Money money) {
        BigDecimal discountAmount = money.amount()
            .multiply(BigDecimal.ONE.subtract(discountRate));
        return Money.of(discountAmount, money.currency());
    }
}
