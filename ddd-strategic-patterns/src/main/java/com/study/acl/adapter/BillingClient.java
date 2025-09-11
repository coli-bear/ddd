package com.study.acl.adapter;

import com.study.acl.upstream.ExternalInvoiceDto;

public interface BillingClient {
    ExternalInvoiceDto getInvoiceByOrderId(String string);

    void payInvoice(String string, String method);
}
