package com.study.billing.infra;

import com.study.billing.domain.Invoice;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InvoiceRepository {
    private final Map<UUID, Invoice> store = new HashMap<>();

    public void save(Invoice invoice) {
        store.put(invoice.id(), invoice);
    }

    public Optional<Invoice> findById(UUID invoiceId) {
        return Optional.ofNullable(store.get(invoiceId));
    }

    public Optional<Invoice> findByOrderId(UUID orderId) {
        return store.values().stream()
            .filter(invoice -> invoice.orderId().equals(orderId))
            .findFirst();
    }
}
