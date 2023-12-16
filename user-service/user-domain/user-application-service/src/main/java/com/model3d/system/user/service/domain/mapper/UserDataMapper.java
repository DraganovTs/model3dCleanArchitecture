package com.model3d.system.user.service.domain.mapper;

import com.model3d.system.domain.valueobject.Money;
import com.model3d.system.domain.valueobject.UserId;
import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserCommand;
import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserResponse;
import com.model3d.system.user.service.domain.dto.createandupdate.UpdateUserCommand;
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

    public CreateUserResponse userToCreateUserResponse(User user ,String message) {
        return CreateUserResponse.builder()
                .email(user.getEmail().getUserEmail())
                .username(user.getUsername().getNickName())
                .message(message)
                .build();
    }

    public User updateUserCommandToUser(UpdateUserCommand updateUserCommand) {
        return User.Builder.builder()
                .userId(new UserId(updateUserCommand.getUserId()))
                .email(new Email(updateUserCommand.getEmail()))
                .username(new Username(updateUserCommand.getUsername()))
                .userMoney(new Money(updateUserCommand.ge))
                .build();
    }
}
