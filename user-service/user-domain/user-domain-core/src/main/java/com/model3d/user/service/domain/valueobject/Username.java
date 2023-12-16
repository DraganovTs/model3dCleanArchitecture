package com.model3d.user.service.domain.valueobject;

import java.util.Objects;

public class Username {

    private final String nickName;
    private static final String[] BANNED_WORDS = {"offensive1", "offensive2", "offensive3"};

    public Username(String nickName) {
        this.nickName = nickName;
    }

    public boolean validateUsername() {
        return this.nickName != null && isUsernameOffencive(this.nickName) && this.nickName.length() > 5;
    }

    private boolean isUsernameOffencive(String nickName) {
        String lowercaseNickname = nickName.toLowerCase();

        for (String word : BANNED_WORDS) {
            if (lowercaseNickname.contains(word.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username = (Username) o;
        return nickName.equals(username.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickName);
    }
}
