package com.tomazbr9.bfinance.identity.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class RefreshToken {

    private UUID id;
    private UUID userId;
    private String tokenHash;
    private LocalDateTime expiresAt;
    private boolean revoked;

    public static RefreshToken create(
            UUID userId,
            String tokenHash,
            LocalDateTime expiresAt
    ){
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.id = UUID.randomUUID();
        refreshToken.userId = userId;
        refreshToken.tokenHash = tokenHash;
        refreshToken.expiresAt = expiresAt;
        refreshToken.revoked = false;

        return refreshToken;
    }

    public static RefreshToken restore(
            UUID id,
            UUID userId,
            String tokenHash,
            LocalDateTime expiresAt,
            boolean revoked
    ) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.id = id;
        refreshToken.userId = userId;
        refreshToken.tokenHash = tokenHash;
        refreshToken.expiresAt = expiresAt;
        refreshToken.revoked = revoked;

        return refreshToken;
    }

    public void revoke() {
        this.revoked = true;
    }

    public boolean isExpired(LocalDateTime now) {
        return expiresAt.isBefore(now);
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public boolean isRevoked() {
        return revoked;
    }
}