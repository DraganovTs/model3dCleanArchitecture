package com.model3d.system.user.service.domain;

import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserCommand;
import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserResponse;
import com.model3d.system.user.service.domain.dto.createandupdate.UpdateUserCommand;
import com.model3d.system.user.service.domain.dto.createandupdate.UpdateUserResponse;
import com.model3d.system.user.service.domain.ports.input.service.UserApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class UserApplicationServiceImpl implements UserApplicationService {

    private final UserAccountCommandHandler userAccountCommandHandler;

    UserApplicationServiceImpl(UserAccountCommandHandler userAccountCommandHandler) {
        this.userAccountCommandHandler = userAccountCommandHandler;
    }

    @Override
    public CreateUserResponse createUser(CreateUserCommand createUserCommand) {
        return userAccountCommandHandler.createUser(createUserCommand);
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserCommand updateUserCommand) {
        return userAccountCommandHandler.updateUser(updateUserCommand);
    }

    //TODO model operations from modelCommandHandler
}
