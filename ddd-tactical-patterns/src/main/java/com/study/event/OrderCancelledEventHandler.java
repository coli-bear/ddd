package com.study.event;

import com.study.shared.EventPublisher.EventHandler;

import java.util.ArrayList;
import java.util.List;

public class OrderCancelledEventHandler implements EventHandler<OrderCancelledEvent> {

    private final List<OrderCancelledEvent> events = new ArrayList<>();

    @Override
    public void handle(OrderCancelledEvent event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }

        events.add(event);
    }
}
