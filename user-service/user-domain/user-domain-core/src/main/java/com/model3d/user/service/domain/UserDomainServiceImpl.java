package com.model3d.user.service.domain;

import com.model3d.user.service.domain.entity.Model;
import com.model3d.user.service.domain.entity.User;
import com.model3d.user.service.domain.event.*;
import com.model3d.user.service.domain.exception.UserDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class UserDomainServiceImpl implements UserDomainService {

    public static final String UTC = "UTC";

    @Override
    public UserCreatedEvent createUser(User user) {
        User updatedUser = user.initializeUser(user);
        log.info("User whit id: {} is initiated", user.getId().getValue());
        return new UserCreatedEvent(updatedUser, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public UserUpdatedEvent updateUser(User user) {
         user.updateUser(user);
        log.info("User whit id: {} is updated", user.getId().getValue());
        return new UserUpdatedEvent(user, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public UserDeletedEvent deleteUser(User user) {
        return null;
    }


    @Override
    public UserDownloadedModelEvent userDownloadModel(User user, Model model) {
        validateModel(model);
        user.downloadModel(model.getId());
        log.info("User whit id: {} downloaded model whit id {}", user.getId().getValue()
                , model.getId().getValue());
        return new UserDownloadedModelEvent(user, model, ZonedDateTime.now(ZoneId.of(UTC)));
    }


    @Override
    public UserLikedModelEvent userLikedModel(User user, Model model) {
        validateModel(model);
        user.likeModel(model.getId());
        log.info("User whit id: {} liked model whit id {}", user.getId().getValue()
                , model.getId().getValue());
        return new UserLikedModelEvent(user, model, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public UserUploadModelEvent userUploadModel(User user, Model model) {
        validateModel(model);
        user.uploadModel(model.getId());
        log.info("User whit id: {} uploaded model whit id {}", user.getId().getValue()
                , model.getId().getValue());
        return new UserUploadModelEvent(user, model, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    private void validateModel(Model model) {
        if (!model.isActive()) {
            throw new UserDomainException("Model whit id: " + model.getId().getValue() +
                    "does not exist!!!");
        }
    }
}
