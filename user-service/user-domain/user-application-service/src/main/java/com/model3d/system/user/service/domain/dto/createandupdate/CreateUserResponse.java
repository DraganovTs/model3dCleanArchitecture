package com.model3d.system.user.service.domain.dto.createandupdate;


import javax.validation.constraints.NotNull;

public class CreateUserResponse extends UserAccountResponse {

    public CreateUserResponse(@NotNull String message) {
        super(message);
    }
}
