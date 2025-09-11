package com.study.acl.downstream;

import java.util.UUID;

public record InvoiceSummary(
    UUID invoiceId,
    UUID orderId,
    UUID customerId,
    Status status
) {

    public enum Status {
        ISSUED, SETTLED, CANCELLED
    }
}