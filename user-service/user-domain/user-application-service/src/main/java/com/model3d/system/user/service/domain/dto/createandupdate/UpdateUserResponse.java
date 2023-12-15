package com.model3d.system.user.service.domain.dto.createandupdate;

import javax.validation.constraints.NotNull;

public class UpdateUserResponse extends UserAccountResponse {
    public UpdateUserResponse(@NotNull String message) {
        super(message);
    }
}
