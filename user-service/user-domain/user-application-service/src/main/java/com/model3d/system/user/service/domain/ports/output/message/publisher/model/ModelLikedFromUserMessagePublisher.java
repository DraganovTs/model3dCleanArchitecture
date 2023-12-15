package com.model3d.system.user.service.domain.ports.output.message.publisher.model;

import com.model3d.system.domain.event.publisher.DomainEventPublisher;
import com.model3d.user.service.domain.event.UserLikedModelEvent;

public interface ModelLikedFromUserMessagePublisher extends DomainEventPublisher<UserLikedModelEvent> {
}
