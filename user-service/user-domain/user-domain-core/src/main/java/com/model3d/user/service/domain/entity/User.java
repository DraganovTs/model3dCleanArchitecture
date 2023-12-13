package com.model3d.user.service.domain.entity;

import com.model3d.system.domain.entity.AggregateRoot;
import com.model3d.system.domain.valueobject.ModelId;
import com.model3d.system.domain.valueobject.Money;
import com.model3d.system.domain.valueobject.UserId;

import java.util.List;
import java.util.UUID;


public class User extends AggregateRoot<UserId> {
    private UserId userId;
    private String email;
    private String password;
    private Money userMoney = Money.ZERO;
    private List<UserRole> roles;
    private List<ModelId> ownedModels;
    private List<ModelId> downloadedModels;
    private List<ModelId> likedModels;


    public void initializeUser(String email,String password){
        setId(new UserId(UUID.randomUUID()));
        this.email = email;
        this.password = password;

    }

    public UserId getUserId() {
        return userId;
    }

    public List<ModelId> getOwnedModels() {
        return ownedModels;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Money getUserMoney() {
        return userMoney;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public List<ModelId> getDownloadedModels() {
        return downloadedModels;
    }

    public List<ModelId> getLikedModels() {
        return likedModels;
    }
}
