package com.study.acl.facade;

import com.study.acl.downstream.InvoiceSummary;

import java.util.UUID;

public record OrderSettlementResult(
    InvoiceSummary invoice,
    boolean paymentSettled,
    UUID shipmentId
) {
}
