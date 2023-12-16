package com.model3d.system.user.service.domain.dto.createandupdate;


import com.model3d.user.service.domain.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CreateUserCommand {

    @NotNull
    @Max(value = 30)
    @Email
    private final String email;
    @NotNull
    @Max(value = 15)
    @Min(value = 4)
    private final String username;
    @NotNull
    private final List<UserRole> roles;
}
