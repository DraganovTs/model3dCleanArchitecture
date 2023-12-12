package com.model3d.user.service.domain.entity;

import com.model3d.system.domain.entity.AggregateRoot;
import com.model3d.system.domain.valueobject.UserId;


public class User extends AggregateRoot<UserId> {
    private String email;
    private String password;

    private User(Builder builder) {
        super.setId(builder.userId);
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static final class Builder {
        private UserId userId;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder userId(UserId val) {
            userId = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
