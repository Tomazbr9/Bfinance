package com.tomazbr9.bfinance.identity.infrastructure.mapper;

import com.tomazbr9.bfinance.identity.domain.entity.RefreshToken;
import com.tomazbr9.bfinance.identity.infrastructure.entity.RefreshTokenJpaEntity;
import com.tomazbr9.bfinance.identity.infrastructure.entity.UserJpaEntity;

public class RefreshTokenMapper {

    private RefreshTokenMapper(){

    }

    public static RefreshTokenJpaEntity toJpaEntity(
            RefreshToken refreshToken,
            UserJpaEntity user
    ){
        return RefreshTokenJpaEntity.builder()
                .id(refreshToken.getId())
                .user(user)
                .tokenHash(refreshToken.getTokenHash())
                .expiresAt(refreshToken.getExpiresAt())
                .revoked(refreshToken.isRevoked())
                .build();
    }

    public static RefreshToken toEntity(RefreshTokenJpaEntity refreshToken){
        return RefreshToken.restore(
                refreshToken.getId(),
                refreshToken.getUser().getId(),
                refreshToken.getTokenHash(),
                refreshToken.getExpiresAt(),
                refreshToken.isRevoked()
        );
    }
}