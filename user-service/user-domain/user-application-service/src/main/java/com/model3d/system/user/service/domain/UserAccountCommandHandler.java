package com.model3d.system.user.service.domain;

import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserCommand;
import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserResponse;
import com.model3d.system.user.service.domain.dto.createandupdate.UpdateUserCommand;
import com.model3d.system.user.service.domain.dto.createandupdate.UpdateUserResponse;
import com.model3d.system.user.service.domain.mapper.UserDataMapper;
import com.model3d.system.user.service.domain.ports.output.repository.UserRepository;
import com.model3d.system.user.service.domain.ports.output.repository.UserRoleRepository;
import com.model3d.user.service.domain.UserDomainService;
import com.model3d.user.service.domain.entity.User;
import com.model3d.user.service.domain.event.UserCreatedEvent;
import com.model3d.user.service.domain.exception.UserDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class UserAccountCommandHandler {

    private final UserDomainService userDomainService;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserDataMapper userDataMapper;

    public UserAccountCommandHandler(UserDomainService userDomainService,
                                     UserRepository userRepository,
                                     UserRoleRepository userRoleRepository,
                                     UserDataMapper userDataMapper) {
        this.userDomainService = userDomainService;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userDataMapper = userDataMapper;
    }

    @Transactional
    public CreateUserResponse createUser(CreateUserCommand createUserCommand) {
        checkEmailExist(createUserCommand.getEmail());
        checkUsernameExist(createUserCommand.getUsername());
        User user = userDataMapper.createUserCommandToUser(createUserCommand);
        UserCreatedEvent userCreatedEvent = userDomainService.createUser(user);
        User userResult = saveUser(user);
        return userDataMapper.userToUserResponse(userResult);
    }


    @Transactional
    public UpdateUserResponse updateUser(UpdateUserCommand updateUserCommand) {
        checkUserExist(updateUserCommand.getUserId());
        return null;
    }

    private void checkUserExist(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            log.warn("Could not find user with user id: {}", userId);
            throw new UserDomainException("Could not find user with user id: " + userId);
        }
    }

    private void checkUsernameExist(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            log.warn("User with username: {} already exist", username);
            throw new UserDomainException("User with username: " + username + " already exist");
        }
    }

    private void checkEmailExist(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            log.warn("User with email: {} already exist", email);
            throw new UserDomainException("User with email: " + email + " already exist");
        }
    }

    private User saveUser(User user) {
        User userResult = userRepository.save(user);
        if (userResult == null) {
            log.error("Could not save user!");
            throw new UserDomainException("Could not save user!");
        }
        log.info("User is saved with id: {}", userResult.getUserId().getValue());
        return userResult;
    }


}
