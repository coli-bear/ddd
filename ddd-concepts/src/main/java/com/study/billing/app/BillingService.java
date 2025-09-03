package com.study.billing.app;

import com.study.billing.domain.Invoice;
import com.study.billing.domain.Payment;
import com.study.billing.infra.InvoiceRepository;
import com.study.shared.Money;

import java.util.UUID;

public class BillingService {
    private final InvoiceRepository invoiceRepository;

    public BillingService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public UUID issueInvoice(UUID orderId, UUID customerId, Money amount) {
        Invoice invoice = new Invoice(amount, customerId, orderId);
        invoiceRepository.save(invoice);
        return invoice.id();
    }

    public void settleInvoice(UUID invoiceId, Money amount, String method) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
            .orElseThrow(() -> new IllegalArgumentException("Invoice not found"));
        invoice.settle(new Payment(invoiceId, amount, method));
        invoiceRepository.save(invoice);
    }
}
