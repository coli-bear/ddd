package com.study.shared;

import java.time.Instant;

public interface DomainEvent {
    Instant occurredAt();
}
