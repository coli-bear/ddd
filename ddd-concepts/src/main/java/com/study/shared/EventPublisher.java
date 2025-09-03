package com.study.shared;

public interface EventPublisher {
    void publish(DomainEvent event);
    void register(Class<? extends DomainEvent> eventType, EventHandler<?> event);

    interface EventHandler<T extends DomainEvent> {
        void handle(T event);
    }
}
