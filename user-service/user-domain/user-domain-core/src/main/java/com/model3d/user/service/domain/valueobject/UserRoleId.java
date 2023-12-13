package com.model3d.user.service.domain.valueobject;

import com.model3d.system.domain.valueobject.BaseId;

import java.util.UUID;

public class UserRoleId extends BaseId<UUID> {
    protected UserRoleId(UUID value) {
        super(value);
    }
}
