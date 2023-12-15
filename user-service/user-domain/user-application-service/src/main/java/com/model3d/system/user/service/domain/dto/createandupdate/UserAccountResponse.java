package com.model3d.system.user.service.domain.dto.createandupdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public abstract class UserAccountResponse {
    @NotNull
    private String message;
}
