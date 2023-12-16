package com.model3d.system.user.service.domain;

import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserCommand;
import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserResponse;
import com.model3d.system.user.service.domain.dto.createandupdate.UpdateUserCommand;
import com.model3d.system.user.service.domain.dto.createandupdate.UpdateUserResponse;
import com.model3d.system.user.service.domain.mapper.UserDataMapper;
import com.model3d.system.user.service.domain.ports.output.message.publisher.user.UserCreatedMessagePublisher;
import com.model3d.user.service.domain.event.UserCreatedEvent;
import com.model3d.user.service.domain.event.UserUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Validated
@Component
public class UserAccountCommandHandler {

    private final UserDataMapper userDataMapper;
    private final UserAccountHelper userAccountHelper;
    private final UserCreatedMessagePublisher userCreatedMessagePublisher;


    public UserAccountCommandHandler(UserDataMapper userDataMapper,
                                     UserAccountHelper userAccountHelper,
                                     UserCreatedMessagePublisher userCreatedMessagePublisher) {
        this.userDataMapper = userDataMapper;
        this.userAccountHelper = userAccountHelper;
        this.userCreatedMessagePublisher = userCreatedMessagePublisher;
    }



    public CreateUserResponse createUser(CreateUserCommand createUserCommand) {
        UserCreatedEvent userCreatedEvent = userAccountHelper.persistUser(createUserCommand);
        log.info("User is created whit id: {}", userCreatedEvent.getUser().getId().getValue());
        userCreatedMessagePublisher.publish(userCreatedEvent);
        return userDataMapper.userToCreateUserResponse(userCreatedEvent.getUser(),"User created Successfully");
    }



    public UpdateUserResponse updateUser(UpdateUserCommand updateUserCommand) {
        UserUpdatedEvent userUpdatedEvent = userAccountHelper.updateUser(updateUserCommand);

        return null;
    }




}

