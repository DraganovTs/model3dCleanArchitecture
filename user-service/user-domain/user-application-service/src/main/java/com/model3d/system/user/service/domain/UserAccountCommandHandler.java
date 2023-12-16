package com.model3d.system.user.service.domain;

import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserCommand;
import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserResponse;
import com.model3d.system.user.service.domain.dto.createandupdate.UpdateUserCommand;
import com.model3d.system.user.service.domain.dto.createandupdate.UpdateUserResponse;
import com.model3d.system.user.service.domain.mapper.UserDataMapper;
import com.model3d.system.user.service.domain.ports.output.message.publisher.user.UserCreatedMessagePublisher;
import com.model3d.user.service.domain.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class UserAccountCommandHandler {

    private final UserDataMapper userDataMapper;
    private final UserCreateHelper userCreateHelper;
    private final UserCreatedMessagePublisher userCreatedMessagePublisher;


    public UserAccountCommandHandler(UserDataMapper userDataMapper,
                                     UserCreateHelper userCreateHelper,
                                     UserCreatedMessagePublisher userCreatedMessagePublisher) {
        this.userDataMapper = userDataMapper;
        this.userCreateHelper = userCreateHelper;
        this.userCreatedMessagePublisher = userCreatedMessagePublisher;
    }



    public CreateUserResponse createUser(CreateUserCommand createUserCommand) {
        UserCreatedEvent userCreatedEvent = userCreateHelper.persistUser(createUserCommand);
        log.info("User is created whit id: {}", userCreatedEvent.getUser().getUserId().getValue());
        userCreatedMessagePublisher.publish(userCreatedEvent);
        return userDataMapper.userToUserResponse(userCreatedEvent.getUser());
    }



    public UpdateUserResponse updateUser(UpdateUserCommand updateUserCommand) {
        checkUserExist(updateUserCommand.getUserId());
        return null;
    }

    private void checkUserExist(UUID userId) {
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isEmpty()) {
//            log.warn("Could not find user with user id: {}", userId);
//            throw new UserDomainException("Could not find user with user id: " + userId);
//        } //TODO create helper class
    }


}

