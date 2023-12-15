package com.model3d.user.service.domain.event;

import com.model3d.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class UserUpdatedEvent extends UserAccountEvent {
    public UserUpdatedEvent(User user, ZonedDateTime createdAt) {
        super(user, createdAt);
    }
}
