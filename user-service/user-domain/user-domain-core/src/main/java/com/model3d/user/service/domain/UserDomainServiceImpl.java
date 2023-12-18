package com.model3d.user.service.domain;

import com.model3d.user.service.domain.entity.Model;
import com.model3d.user.service.domain.entity.User;
import com.model3d.user.service.domain.entity.UserRole;
import com.model3d.user.service.domain.event.*;
import com.model3d.user.service.domain.exception.UserDomainException;
import com.model3d.user.service.domain.valueobject.Email;
import com.model3d.user.service.domain.valueobject.UserRoleEnum;
import com.model3d.user.service.domain.valueobject.Username;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserDomainServiceImpl implements UserDomainService {

    public static final String UTC = "UTC";

    @Override
    public UserCreatedEvent createUser(User user) {
        validateUserInfo(user);
        user.validateUser();
        user.initializeUser();
        log.info("User whit id: {} is initiated", user.getId().getValue());
        return new UserCreatedEvent(user, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public UserUpdatedEvent updateUser(User user) {
        validateUserInfo(user);
        log.info("User whit id: {} is initiated", user.getId().getValue());
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
    public UserLikedModelEvent userLikeModel(User user, Model model) {
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

    private void validateUserInfo(User user) {
        validateEmail(user.getEmail());
        validateUsername(user.getUsername());
        validateUserRoles(user.getRoles());
    }

    private void validateUserRoles(List<UserRole> roles) {
        List<UserRoleEnum> invalidRoles = roles.stream()
                .map(UserRole::getRoleEnum)
                .filter(roleEnum -> !isValidUserRoleEnum(roleEnum))
                .collect(Collectors.toList());

        if (!invalidRoles.isEmpty()) {
            throw new UserDomainException("Invalid User role");
        }
    }

    private boolean isValidUserRoleEnum(UserRoleEnum roleEnum) {
        return Arrays.stream(UserRoleEnum.values())
                .anyMatch(validRoleEnum -> validRoleEnum == roleEnum);
    }

    private void validateUsername(Username username) {
        if (!username.validateUsername()) {
            throw new UserDomainException("Username is not valid");
        }

    }

    private void validateEmail(Email email) {
        if (!email.isValid()) {
            throw new UserDomainException("Email is not valid");
        }
    }
}
