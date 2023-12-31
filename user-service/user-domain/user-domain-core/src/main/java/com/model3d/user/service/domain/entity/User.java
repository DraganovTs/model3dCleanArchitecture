package com.model3d.user.service.domain.entity;

import com.model3d.system.domain.entity.AggregateRoot;
import com.model3d.system.domain.valueobject.ModelId;
import com.model3d.system.domain.valueobject.Money;
import com.model3d.system.domain.valueobject.UserId;
import com.model3d.user.service.domain.exception.UserDomainException;
import com.model3d.user.service.domain.valueobject.Email;
import com.model3d.user.service.domain.valueobject.UserRoleEnum;
import com.model3d.user.service.domain.valueobject.UserStatus;
import com.model3d.user.service.domain.valueobject.Username;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class User extends AggregateRoot<UserId> {
    private final Username username;
    private final Email email;
    private UserStatus userStatus;
    private Money userMoney;
    private List<UserRole> roles;
    private List<ModelId> ownedModels;
    private List<ModelId> downloadedModels;
    private List<ModelId> likedModels;

    private User(Builder builder) {
        super.setId(builder.userId);
        username = builder.username;
        email = builder.email;
        userStatus = builder.userStatus;
        userMoney = builder.userMoney;
        roles = builder.roles;
        ownedModels = builder.ownedModels;
        downloadedModels = builder.downloadedModels;
        likedModels = builder.likedModels;
    }


    public void initializeUser() {
        setId(new UserId(UUID.randomUUID()));
        userStatus = UserStatus.ACTIVE;
        userMoney = Money.ZERO;
        roles = new ArrayList<>();
        ownedModels = new ArrayList<>();
        downloadedModels = new ArrayList<>();
        likedModels = new ArrayList<>();
    }

    public void validateUser() {
        if (getId() != null || userStatus != null || userMoney != null
                || roles != null  || ownedModels!= null || downloadedModels!= null ||
                likedModels!= null) {
            throw new UserDomainException("User is not in correct state for initialization!");
        }
    }


    public void uploadModel(ModelId modelId) {
        if (this.ownedModels.contains(modelId) || modelId == null) {
            throw new UserDomainException("Model is already in your account!!!");
        }
        this.ownedModels.add(modelId);
    }

    public void downloadModel(ModelId modelId) {
        this.downloadedModels.add(modelId);
    }

    public void likeModel(ModelId modelId) {
        if (this.likedModels.contains(modelId)) {
            throw new UserDomainException("Model is already is liked by you!!!");
        }
        this.likedModels.add(modelId);
    }

    public Username getUsername() {
        return username;
    }

    public Email getEmail() {
        return email;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public Money getUserMoney() {
        return userMoney;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public List<ModelId> getOwnedModels() {
        return ownedModels;
    }

    public List<ModelId> getDownloadedModels() {
        return downloadedModels;
    }

    public List<ModelId> getLikedModels() {
        return likedModels;
    }


    public static final class Builder {
        private UserId userId;
        private Username username;
        private Email email;
        private UserStatus userStatus;
        private Money userMoney;
        private List<UserRole> roles;
        private List<ModelId> ownedModels;
        private List<ModelId> downloadedModels;
        private List<ModelId> likedModels;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder userId(UserId val) {
            userId = val;
            return this;
        }

        public Builder username(Username val) {
            username = val;
            return this;
        }

        public Builder email(Email val) {
            email = val;
            return this;
        }

        public Builder userStatus(UserStatus val) {
            userStatus = val;
            return this;
        }

        public Builder userMoney(Money val) {
            userMoney = val;
            return this;
        }

        public Builder roles(List<UserRole> val) {
            roles = val;
            return this;
        }

        public Builder ownedModels(List<ModelId> val) {
            ownedModels = val;
            return this;
        }

        public Builder downloadedModels(List<ModelId> val) {
            downloadedModels = val;
            return this;
        }

        public Builder likedModels(List<ModelId> val) {
            likedModels = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
