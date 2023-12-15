package com.model3d.user.service.domain.event;

import com.model3d.system.domain.event.DomainEvent;
import com.model3d.user.service.domain.entity.Model;
import com.model3d.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public abstract class UserModelEvent implements DomainEvent<User> {

    private final User user;
    private final Model model;
    private final ZonedDateTime createdAt;

    protected UserModelEvent(User user, Model model, ZonedDateTime createdAt) {
        this.user = user;
        this.model = model;
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public Model getModel() {
        return model;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
