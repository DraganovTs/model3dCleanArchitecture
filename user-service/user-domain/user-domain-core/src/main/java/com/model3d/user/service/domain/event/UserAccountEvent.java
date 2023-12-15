package com.model3d.user.service.domain.event;

import com.model3d.system.domain.event.DomainEvent;
import com.model3d.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public abstract class UserAccountEvent implements DomainEvent<User> {
    private final User user;
    private final ZonedDateTime createdAt;

    public UserAccountEvent(User user, ZonedDateTime createdAt) {
        this.user = user;
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
