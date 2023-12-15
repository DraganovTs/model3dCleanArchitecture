package com.model3d.user.service.domain.entity;

import com.model3d.system.domain.entity.BaseEntity;
import com.model3d.user.service.domain.valueobject.UserRoleId;
import com.model3d.user.service.domain.valueobject.UserRoleEnum;


public class UserRole extends BaseEntity<UserRoleId> {

    private final UserRoleEnum roleEnum;

    protected UserRole(Builder builder) {
        super.setId(builder.userRoleId);
        roleEnum = builder.roleEnum;
    }


    public UserRoleEnum getRoleEnum() {
        return roleEnum;
    }

    public static final class Builder {
        private UserRoleId userRoleId;
        private UserRoleEnum roleEnum;

        Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder userRoleId(UserRoleId val) {
            userRoleId = val;
            return this;
        }

        public Builder roleEnum(UserRoleEnum val) {
            roleEnum = val;
            return this;
        }

        public UserRole build() {
            return new UserRole(this);
        }
    }
}
