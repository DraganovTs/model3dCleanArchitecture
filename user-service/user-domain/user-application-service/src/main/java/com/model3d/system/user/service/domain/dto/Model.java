package com.model3d.system.user.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Model {
    @NotNull
    private final UUID modelId;
    @NotNull
    private final String name;
    @NotNull
    private final boolean isActive;

}
