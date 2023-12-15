package com.model3d.user.service.domain.valueobject;

import com.model3d.user.service.domain.exception.UserDomainException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    private final String userEmail;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public Email(String userEmail) {
        this.userEmail = userEmail;
    }



    public boolean isValid(){
        return this.userEmail != null &&  isCorrect(this.userEmail);
    }

    private boolean isCorrect(String userEmail) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(userEmail);
        return matcher.matches();
    }

    public Email changeEmail(String newEmail) {
        if (isCorrect(newEmail)) {
           return new Email(newEmail);
        } else {
            throw new UserDomainException("Invalid email format");
        }
    }

    public String getUserEmail() {
        return userEmail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return userEmail.equals(email.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail);
    }


}
