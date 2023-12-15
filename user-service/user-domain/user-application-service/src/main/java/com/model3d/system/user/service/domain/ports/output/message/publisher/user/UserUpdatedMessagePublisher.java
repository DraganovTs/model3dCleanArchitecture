package com.model3d.system.user.service.domain.ports.output.message.publisher.user;

import com.model3d.system.domain.event.publisher.DomainEventPublisher;
import com.model3d.user.service.domain.event.UserUpdatedEvent;

public interface UserUpdatedMessagePublisher extends DomainEventPublisher<UserUpdatedEvent> {
}
