package com.tomazbr9.bfinance.identity.application.usecase;

import com.tomazbr9.bfinance.identity.application.command.LogoutCommand;
import com.tomazbr9.bfinance.identity.application.exception.InvalidRefreshTokenException;
import com.tomazbr9.bfinance.identity.application.port.out.TokenHasher;
import com.tomazbr9.bfinance.identity.domain.entity.RefreshToken;
import com.tomazbr9.bfinance.identity.domain.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class LogoutUseCase {

    private final TokenHasher tokenHasher;
    private final RefreshTokenRepository refreshTokenRepository;

    public LogoutUseCase(
            TokenHasher tokenHasher,
            RefreshTokenRepository refreshTokenRepository
    ){
        this.tokenHasher = tokenHasher;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public void execute(LogoutCommand command) {

        String refreshToken = tokenHasher.hash(command.refreshToken());

        RefreshToken refreshTokenEntity = refreshTokenRepository.findByTokenHash(refreshToken)
                .orElseThrow(InvalidRefreshTokenException::new);

        refreshTokenEntity.revoke();

        refreshTokenRepository.save(refreshTokenEntity);

    }
}