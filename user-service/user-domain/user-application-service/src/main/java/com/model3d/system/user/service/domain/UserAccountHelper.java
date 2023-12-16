package com.model3d.system.user.service.domain;

import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserCommand;
import com.model3d.system.user.service.domain.dto.createandupdate.UpdateUserCommand;
import com.model3d.system.user.service.domain.mapper.UserDataMapper;
import com.model3d.system.user.service.domain.ports.output.repository.UserRepository;
import com.model3d.system.user.service.domain.ports.output.repository.UserRoleRepository;
import com.model3d.user.service.domain.UserDomainService;
import com.model3d.user.service.domain.entity.User;
import com.model3d.user.service.domain.entity.UserRole;
import com.model3d.user.service.domain.event.UserCreatedEvent;
import com.model3d.user.service.domain.event.UserUpdatedEvent;
import com.model3d.user.service.domain.exception.UserDomainException;
import com.model3d.user.service.domain.exception.UserRoleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class UserAccountHelper {

    private final UserDomainService userDomainService;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserDataMapper userDataMapper;

    public UserAccountHelper(UserDomainService userDomainService,
                             UserRepository userRepository,
                             UserRoleRepository userRoleRepository,
                             UserDataMapper userDataMapper) {
        this.userDomainService = userDomainService;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userDataMapper = userDataMapper;
    }

    @Transactional
    public UserCreatedEvent persistUser(CreateUserCommand createUserCommand) {
        checkEmailExist(createUserCommand.getEmail());
        checkUsernameExist(createUserCommand.getUsername());
        List<UserRole> userRoles = checkUserRole(createUserCommand.getRoles());
        User user = userDataMapper.createUserCommandToUser(createUserCommand);
        UserCreatedEvent userCreatedEvent = userDomainService.createUser(user,userRoles);
        saveUser(user);
        log.info("User is created whit id: {}", userCreatedEvent.getUser().getId().getValue());
        return userCreatedEvent;
    }

    private List<UserRole> checkUserRole(List<UserRole> roles) {
        List<UserRole> userRolesResult = new ArrayList<>();
        for (UserRole role : roles) {
            role = this.userRoleRepository.findUserRole(role).get();
            userRolesResult.add(role);
        }
        if (userRolesResult.isEmpty()) {
            log.warn("No valid roles for this user");
            throw new UserRoleException("No valid roles for this user");
        }
        return userRolesResult;
    }

    @Transactional
    public UserUpdatedEvent updateUser(UpdateUserCommand updateUserCommand) {
        checkUserExist(updateUserCommand.getUserId());
        checkEmailExist(updateUserCommand.getEmail());
        checkUsernameExist(updateUserCommand.getUsername());
        User user = userDataMapper.updateUserCommandToUser(updateUserCommand);
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
