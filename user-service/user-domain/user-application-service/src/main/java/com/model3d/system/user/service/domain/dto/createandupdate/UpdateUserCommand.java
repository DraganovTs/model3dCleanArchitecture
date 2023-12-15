package com.model3d.system.user.service.domain.dto.createandupdate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class UpdateUserCommand extends UserAccountCommand{
    public UpdateUserCommand(@NotNull UUID userId, @NotNull @Max(value = 30) @Email String email, @NotNull @Max(value = 15) @Min(value = 4) String username) {
        super(userId, email, username);
    }
}
