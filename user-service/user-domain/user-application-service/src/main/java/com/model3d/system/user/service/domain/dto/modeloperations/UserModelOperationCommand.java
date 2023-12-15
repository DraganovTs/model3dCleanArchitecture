package com.model3d.system.user.service.domain.dto.modeloperations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Getter
@Builder
@AllArgsConstructor
public abstract class UserModelOperationCommand {
    private final UUID userId;
}
