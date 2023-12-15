package com.model3d.system.user.service.domain.dto.message;

import com.model3d.system.domain.valueobject.ModelStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class ModelResponse {
    private String id;
    private String sagaId;
    private String userId;
    private Instant createdAt;
    private ModelStatus modelStatus;

}
