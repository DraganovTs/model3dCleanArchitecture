package com.model3d.system.domain.event.publisher;

import com.model3d.system.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
