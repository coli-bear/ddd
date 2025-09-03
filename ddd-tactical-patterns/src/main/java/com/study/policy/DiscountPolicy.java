package com.study.policy;

import com.study.shared.Money;

public interface DiscountPolicy {
    Money apply(Money total);
}
