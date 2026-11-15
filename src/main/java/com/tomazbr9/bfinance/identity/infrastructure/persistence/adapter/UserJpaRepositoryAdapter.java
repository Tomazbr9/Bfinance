package com.tomazbr9.bfinance.identity.infrastructure.persistence.adapter;

import com.tomazbr9.bfinance.identity.domain.entity.User;
import com.tomazbr9.bfinance.identity.domain.repository.UserRepository;
import com.tomazbr9.bfinance.identity.domain.valueobject.Email;
import com.tomazbr9.bfinance.identity.infrastructure.entity.UserJpaEntity;
import com.tomazbr9.bfinance.identity.infrastructure.mapper.UserMapper;
import com.tomazbr9.bfinance.identity.infrastructure.persistence.repository.UserJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserJpaRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByEmail(Email email) {
        return userJpaRepository.findByEmail(email.value()).map(UserMapper::toEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id).map(UserMapper::toEntity);
    }

    @Transactional
    @Override
    public User save(User user) {

        UserJpaEntity userJpa =
                UserMapper.toJpaEntity(user);

        entityManager.persist(userJpa);

        return UserMapper.toEntity(userJpa);
    }
}