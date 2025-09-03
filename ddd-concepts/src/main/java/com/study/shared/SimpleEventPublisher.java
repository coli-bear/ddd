package com.study.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleEventPublisher implements EventPublisher {
    private final Map<Class<? extends DomainEvent>, List<EventHandler<? extends DomainEvent>>> handlers = new HashMap<>();

    @Override
    public void publish(DomainEvent event) {
        handlers.getOrDefault(event.getClass(), List.of()).stream()
            .map(h -> (EventHandler<DomainEvent>) h) // unchecked cast
            .forEach(h -> h.handle(event));
    }

    @Override
    public void register(Class<? extends DomainEvent> type, EventHandler<?> handler) {
        handlers.computeIfAbsent(type, k -> new ArrayList<>()).add(handler);
    }
}
