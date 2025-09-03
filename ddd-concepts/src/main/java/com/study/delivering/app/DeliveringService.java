package com.study.delivering.app;

import com.study.delivering.domain.Delivery;
import com.study.delivering.domain.DeliveryAddress;
import com.study.delivering.infra.DeliveryRepository;
import com.study.shared.DomainEvent;
import com.study.shared.EventPublisher;

import java.util.List;
import java.util.UUID;

public class DeliveringService {
    private final DeliveryRepository deliveryRepository;
    private final EventPublisher eventPublisher;

    public DeliveringService(DeliveryRepository deliveryRepository, EventPublisher eventPublisher) {
        this.deliveryRepository = deliveryRepository;
        this.eventPublisher = eventPublisher;
    }

    public UUID createDelivery(UUID orderId, DeliveryAddress address) {
        var delivery = new Delivery(orderId, address);
        deliveryRepository.save(delivery);
        publishEvent(delivery.pullEvents());
        return delivery.id();
    }

    public void startDelivery(UUID orderId) {
        var delivery = deliveryRepository.findByOrderId(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Delivery not found for order: " + orderId));
        delivery.deliveryStart();
        publishEvent(delivery.pullEvents());
        deliveryRepository.save(delivery);
    }

    public void completeDelivery(UUID orderId) {
        var delivery = deliveryRepository.findByOrderId(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Delivery not found for order: " + orderId));
        delivery.deliveryComplete();
        publishEvent(delivery.pullEvents());
        deliveryRepository.save(delivery);
    }

    private void publishEvent(List<DomainEvent> events) {
        events.forEach(eventPublisher::publish);
    }
}
