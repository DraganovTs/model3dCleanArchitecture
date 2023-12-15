package com.model3d.user.service.domain.event;

import com.model3d.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class UserDeletedEvent extends UserAccountEvent {
    public UserDeletedEvent(User user, ZonedDateTime createdAt) {
        super(user, createdAt);
    }
}
