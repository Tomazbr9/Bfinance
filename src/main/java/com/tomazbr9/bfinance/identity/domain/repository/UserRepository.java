package com.tomazbr9.bfinance.identity.domain.repository;

import com.tomazbr9.bfinance.identity.domain.entity.User;
import com.tomazbr9.bfinance.identity.domain.valueobject.Email;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findByEmail(Email email);

    Optional<User> findById(UUID id);

    User save(User user);
}
