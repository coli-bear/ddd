package com.study.acl.adapter;

import com.study.acl.downstream.InvoiceSummary;

import java.util.UUID;

public interface BillingPort {
    InvoiceSummary findInvoiceByOrderId(UUID orderId);

    void settleInvoice(UUID invoiceId, String method);
}
