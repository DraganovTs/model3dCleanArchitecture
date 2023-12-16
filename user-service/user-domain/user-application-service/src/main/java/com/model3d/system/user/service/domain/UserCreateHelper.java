package com.model3d.system.user.service.domain;

import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserCommand;
import com.model3d.system.user.service.domain.mapper.UserDataMapper;
import com.model3d.system.user.service.domain.ports.output.repository.UserRepository;
import com.model3d.user.service.domain.UserDomainService;
import com.model3d.user.service.domain.entity.User;
import com.model3d.user.service.domain.event.UserCreatedEvent;
import com.model3d.user.service.domain.exception.UserDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class UserCreateHelper {

    private final UserDomainService userDomainService;
    private final UserRepository userRepository;
    private final UserDataMapper userDataMapper;

    public UserCreateHelper(UserDomainService userDomainService,
                            UserRepository userRepository,
                            UserDataMapper userDataMapper) {
        this.userDomainService = userDomainService;
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
    }

    @Transactional
    public UserCreatedEvent persistUser(CreateUserCommand createUserCommand) {
        checkEmailExist(createUserCommand.getEmail());
        checkUsernameExist(createUserCommand.getUsername());
        User user = userDataMapper.createUserCommandToUser(createUserCommand);
        UserCreatedEvent userCreatedEvent = userDomainService.createUser(user);
        saveUser(user);
        log.info("User is created whit id: {}", userCreatedEvent.getUser().getId().getValue());
        return userCreatedEvent;
    }

    private void checkUsernameExist(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            log.warn("User with username: {} already exist", username);
            throw new UserDomainException("User with username: " + username + " already exist");
        }
    }

    private void checkEmailExist(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
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
        log.info("User is saved with id: {}", userResult.getId().getValue());
        return userResult;
    }
}
