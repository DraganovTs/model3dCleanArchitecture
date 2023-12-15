package com.model3d.system.user.service.domain.ports.output.repository;

import com.model3d.user.service.domain.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(UUID userId);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
