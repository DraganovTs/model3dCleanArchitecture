package com.model3d.user.service.domain.exception;

import com.model3d.system.domain.exception.DomainException;

public class UserRoleException extends DomainException {
    public UserRoleException(String message) {
        super(message);
    }

    public UserRoleException(String message, Throwable cause) {
        super(message, cause);
    }
}
