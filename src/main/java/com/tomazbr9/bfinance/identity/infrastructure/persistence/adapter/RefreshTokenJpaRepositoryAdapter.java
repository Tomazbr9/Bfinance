package com.tomazbr9.bfinance.identity.infrastructure.persistence.adapter;

import com.tomazbr9.bfinance.identity.domain.entity.RefreshToken;
import com.tomazbr9.bfinance.identity.domain.repository.RefreshTokenRepository;
import com.tomazbr9.bfinance.identity.infrastructure.entity.RefreshTokenJpaEntity;
import com.tomazbr9.bfinance.identity.infrastructure.entity.UserJpaEntity;
import com.tomazbr9.bfinance.identity.infrastructure.mapper.RefreshTokenMapper;
import com.tomazbr9.bfinance.identity.infrastructure.persistence.repository.RefreshTokenJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenJpaRepositoryAdapter implements RefreshTokenRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public RefreshToken save(RefreshToken refreshToken) {

        RefreshTokenJpaEntity existing = entityManager.find(
                RefreshTokenJpaEntity.class,
                refreshToken.getId()
        );

        if(existing == null){
            return create(refreshToken);
        }

        return update(existing, refreshToken);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<RefreshToken> findByTokenHash(String tokenHash) {
        return refreshTokenJpaRepository.findByTokenHash(tokenHash)
                .map(RefreshTokenMapper::toEntity);
    }

    private RefreshToken create(RefreshToken refreshToken) {

        UserJpaEntity user = entityManager.getReference(
                UserJpaEntity.class,
                refreshToken.getUserId()
        );

        RefreshTokenJpaEntity refreshTokenJpa = RefreshTokenMapper.toJpaEntity(refreshToken, user);

        entityManager.persist(refreshTokenJpa);

        return RefreshTokenMapper.toEntity(refreshTokenJpa);
    }

    private RefreshToken update(RefreshTokenJpaEntity existing, RefreshToken refreshToken){

        existing.setTokenHash(refreshToken.getTokenHash());
        existing.setExpiresAt(refreshToken.getExpiresAt());
        existing.setRevoked(refreshToken.isRevoked());

        return RefreshTokenMapper.toEntity(existing);
    }

}