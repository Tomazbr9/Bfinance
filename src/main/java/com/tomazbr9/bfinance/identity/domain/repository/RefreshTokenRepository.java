package com.tomazbr9.bfinance.identity.domain.repository;

import com.tomazbr9.bfinance.identity.domain.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {

    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByTokenHash(String tokenHash);
}