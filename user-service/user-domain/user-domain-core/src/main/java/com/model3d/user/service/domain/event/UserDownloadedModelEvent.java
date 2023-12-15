package com.model3d.user.service.domain.event;

import com.model3d.user.service.domain.entity.Model;
import com.model3d.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class UserDownloadedModelEvent extends UserModelEvent {
    public UserDownloadedModelEvent(User user, Model model, ZonedDateTime createdAt) {
        super(user, model, createdAt);
    }
}
