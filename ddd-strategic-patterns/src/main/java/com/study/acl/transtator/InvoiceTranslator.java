package com.study.acl.transtator;

import com.study.acl.downstream.InvoiceSummary;
import com.study.acl.upstream.ExternalInvoiceDto;

import java.util.UUID;

public class InvoiceTranslator {
    public InvoiceSummary toDomain(ExternalInvoiceDto externalInvoice) {
        return new InvoiceSummary(
            UUID.fromString(externalInvoice.invoiceId()),
            UUID.fromString(externalInvoice.orderId()),
            UUID.fromString(externalInvoice.customerId()),
            mapStatus(externalInvoice.status())
        );
    }

    private InvoiceSummary.Status mapStatus(String s) {
        return switch (s) {
            case "SETTLED" ->  InvoiceSummary.Status .SETTLED;
            case "CANCELLED" -> InvoiceSummary.Status.CANCELLED;
            default -> InvoiceSummary.Status.ISSUED;
        };
    }
}
