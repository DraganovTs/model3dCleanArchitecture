package com.model3d.system.user.service.domain.dto.create;

import com.model3d.user.service.domain.valueobject.Email;
import com.model3d.user.service.domain.valueobject.Username;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateUserCommand {

    @NotNull
    private final UUID userId;
    @NotNull
    @Max(value = 30)
    @javax.validation.constraints.Email
    private final Email email;
    @NotNull
    @Max(value = 30)
    private final Username username;
}
