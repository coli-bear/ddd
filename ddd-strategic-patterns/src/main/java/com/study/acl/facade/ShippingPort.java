package com.study.acl.facade;

import java.util.UUID;

public interface ShippingPort {
    UUID createShipment(UUID orderId, String address);
}
