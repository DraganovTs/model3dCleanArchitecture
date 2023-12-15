package com.model3d.user.service.domain.entity;

import com.model3d.system.domain.entity.AggregateRoot;
import com.model3d.system.domain.valueobject.ModelId;
import com.model3d.system.domain.valueobject.Money;
import com.model3d.system.domain.valueobject.UserId;
import com.model3d.user.service.domain.exception.UserDomainException;
import com.model3d.user.service.domain.valueobject.Email;
import com.model3d.user.service.domain.valueobject.UserRoleEnum;
import com.model3d.user.service.domain.valueobject.Username;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class User extends AggregateRoot<UserId> {
    private UserId userId;
    private final Username username;
    private final Email email;
    private Money userMoney;
    private final List<UserRole> roles;
    private List<ModelId> ownedModels;
    private List<ModelId> downloadedModels;
    private List<ModelId> likedModels;

    private User(Builder builder) {
        super.setId(builder.userId);
        userId = builder.userId;
        username = builder.username;
        email = builder.email;
        userMoney = builder.userMoney;
        roles = builder.roles;
        ownedModels = builder.ownedModels;
        downloadedModels = builder.downloadedModels;
        likedModels = builder.likedModels;
    }


    public void initializeUser(String email, String username) {
        setId(new UserId(UUID.randomUUID()));
        initializeEmail(email);
        initializeUserName(username);
        setUserRole(UserRoleEnum.USER);
        userMoney = Money.ZERO;
        ownedModels = new ArrayList<>();
        downloadedModels = new ArrayList<>();
        likedModels = new ArrayList<>();
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
            throw new UserDomainException("Model is already in liked by you!!!");
        }
        this.likedModels.add(modelId);
    }

    private Username initializeUserName(String username) {
        Username currentUsername = new Username(username);
        if (currentUsername.validateUsername()) {
            return currentUsername;
        }
        throw new UserDomainException("Username is not valid");
    }

    private Email initializeEmail(String email) {
        Email currentEmail = new Email(email);
        if (currentEmail.isValid()) {
            return currentEmail;
        }
        throw new UserDomainException("Email is not valid");

    }

    private void setUserRole(UserRoleEnum roleEnum) {
        UserRole userRole = new UserRole.Builder()
                .roleEnum(roleEnum)
                .build();
        this.roles.add(userRole);
    }


    public UserId getUserId() {
        return userId;
    }

    public Username getUsername() {
        return username;
    }

    public Email getEmail() {
        return email;
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
