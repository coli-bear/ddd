package com.study.acl.upstream;

public record ExternalInvoiceDto(
    String invoiceId,
    String orderId,
    String customerId,
    String status
) {
}
