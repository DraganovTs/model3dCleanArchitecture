package com.model3d.system.user.service.domain.mapper;

import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserCommand;
import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserResponse;
import com.model3d.user.service.domain.entity.User;
import com.model3d.user.service.domain.valueobject.Email;
import com.model3d.user.service.domain.valueobject.Username;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {


    public User createUserCommandToUser(CreateUserCommand createUserCommand) {
        return User.Builder.builder()
                .email(new Email(createUserCommand.getEmail()))
                .username(new Username(createUserCommand.getUsername()))
                .build();
    }

    public CreateUserResponse userToUserResponse(User user) {
        return new CreateUserResponse(user.getUsername().getNickName()
                , user.getEmail().getUserEmail());
    }
}
