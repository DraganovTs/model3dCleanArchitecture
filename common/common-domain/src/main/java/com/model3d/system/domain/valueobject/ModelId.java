package com.model3d.system.domain.valueobject;

import java.util.UUID;

public class ModelId extends BaseId<UUID> {
    protected ModelId(UUID value) {
        super(value);
    }
}
