package com.study.acl.adapter;

import com.study.acl.downstream.InvoiceSummary;
import com.study.acl.transtator.InvoiceTranslator;
import com.study.acl.upstream.ExternalInvoiceDto;

import java.util.UUID;

public class BillingAdapter implements BillingPort {
    private final BillingClient billingClient;
    private final InvoiceTranslator invoiceTranslator;


    public BillingAdapter(BillingClient billingClient, InvoiceTranslator invoiceTranslator) {
        this.billingClient = billingClient;
        this.invoiceTranslator = invoiceTranslator;
    }

    @Override
    public InvoiceSummary findInvoiceByOrderId(UUID orderId) {
        ExternalInvoiceDto externalInvoice = billingClient.getInvoiceByOrderId(orderId.toString());
        return invoiceTranslator.toDomain(externalInvoice);
    }

    @Override
    public void settleInvoice(UUID invoiceId, String method) {
        billingClient.payInvoice(invoiceId.toString(), method);
    }
}
