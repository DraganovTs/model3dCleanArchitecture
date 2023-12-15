package com.model3d.system.user.service.domain.ports.output.message.publisher.user;

import com.model3d.system.domain.event.publisher.DomainEventPublisher;
import com.model3d.user.service.domain.event.UserCreatedEvent;

public interface UserCreatedMessagePublisher extends DomainEventPublisher<UserCreatedEvent> {
}
