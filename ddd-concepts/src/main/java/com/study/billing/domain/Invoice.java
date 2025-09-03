package com.study.billing.domain;

import com.study.shared.Money;

import java.util.UUID;

/**
 * 청구서(Invoice) 도메인 컨텍스트
 * - 청구서 생성, 청구서 발송, 결제 상태 추적 등을 담당합니다.
 * - 예: 청구서 발행, 미납 청구서 관리 등
 */
public class Invoice {
    public enum Status {ISSUED, SETTLED}

    private final UUID invoiceId;
    private final UUID orderId;
    private final UUID customerId;
    private final Money amount;
    private Status status = Status.ISSUED;

    public Invoice(Money amount, UUID customerId, UUID orderId) {
        this.amount = amount;
        this.customerId = customerId;
        this.orderId = orderId;
        this.invoiceId = UUID.randomUUID();
    }

    public void settle(Payment payment) {
        if (!payment.amount().equals(this.amount)) {
            throw new IllegalStateException("Payment amount does not match");
        }
        this.status = Status.SETTLED;
    }

    public UUID id() {
        return invoiceId;
    }
    public Status status() {
        return status;
    }
    public UUID orderId() {
        return orderId;
    }
    public Money amount() {
        return amount;
    }
}
