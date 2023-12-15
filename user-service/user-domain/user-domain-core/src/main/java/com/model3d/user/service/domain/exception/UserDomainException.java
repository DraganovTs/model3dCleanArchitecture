package com.model3d.user.service.domain.exception;


import com.model3d.system.domain.exception.DomainException;

public class UserDomainException extends DomainException {

    public UserDomainException(String message) {
        super(message);
    }

    public UserDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
