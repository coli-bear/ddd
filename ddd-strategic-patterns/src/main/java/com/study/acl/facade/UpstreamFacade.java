package com.study.acl.facade;

import com.study.acl.downstream.InvoiceSummary;

import java.util.UUID;

public class UpstreamFacade {
    private final BillingPort billingPort;
    private final ShippingPort shippingPort;


    public UpstreamFacade(BillingPort billingPort, ShippingPort shippingPort) {
        this.billingPort = billingPort;
        this.shippingPort = shippingPort;
    }

    public OrderSettlementResult settleAndShip(UUID orderId, String paymentMethod, String address) {
        InvoiceSummary invoice = billingPort.findInvoiceByOrderId(orderId);
        billingPort.settleInvoice(invoice.invoiceId(), paymentMethod);
        UUID shipmentId = shippingPort.createShipment(orderId, address);
        return new OrderSettlementResult(invoice, true, shipmentId);
    }
}

