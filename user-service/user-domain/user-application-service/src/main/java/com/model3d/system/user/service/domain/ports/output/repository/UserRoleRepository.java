package com.model3d.system.user.service.domain.ports.output.repository;

import com.model3d.user.service.domain.entity.UserRole;
import com.model3d.user.service.domain.valueobject.UserRoleEnum;

import java.util.Optional;

public interface UserRoleRepository {

    Optional<UserRole> findUserRole(UserRole userRole);
}
