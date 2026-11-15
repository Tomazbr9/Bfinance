package com.tomazbr9.bfinance.identity.application.usecase;

import com.tomazbr9.bfinance.identity.application.command.RefreshTokenCommand;
import com.tomazbr9.bfinance.identity.application.dto.TokenResult;
import com.tomazbr9.bfinance.identity.application.exception.ExpiredRefreshTokenException;
import com.tomazbr9.bfinance.identity.application.exception.InvalidRefreshTokenException;
import com.tomazbr9.bfinance.identity.application.exception.RevokedRefreshTokenException;
import com.tomazbr9.bfinance.identity.application.exception.UserNotFoundException;
import com.tomazbr9.bfinance.identity.application.port.out.RefreshTokenExpirationProvider;
import com.tomazbr9.bfinance.identity.application.port.out.RefreshTokenGenerator;
import com.tomazbr9.bfinance.identity.application.port.out.TokenHasher;
import com.tomazbr9.bfinance.identity.application.port.out.TokenProvider;
import com.tomazbr9.bfinance.identity.domain.entity.RefreshToken;
import com.tomazbr9.bfinance.identity.domain.entity.User;
import com.tomazbr9.bfinance.identity.domain.repository.RefreshTokenRepository;
import com.tomazbr9.bfinance.identity.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RefreshTokenUseCase {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenGenerator refreshTokenGenerator;
    private final RefreshTokenExpirationProvider refreshTokenExpirationProvider;
    private final UserRepository userRepository;
    private final TokenHasher tokenHasher;
    private final TokenProvider tokenProvider;

    public RefreshTokenUseCase(
            RefreshTokenRepository refreshTokenRepository,
            RefreshTokenGenerator refreshTokenGenerator,
            RefreshTokenExpirationProvider refreshTokenExpirationProvider,
            UserRepository userRepository,
            TokenHasher tokenHasher,
            TokenProvider tokenProvider
    ){
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenGenerator = refreshTokenGenerator;
        this.refreshTokenExpirationProvider = refreshTokenExpirationProvider;
        this.userRepository = userRepository;
        this.tokenHasher = tokenHasher;
        this.tokenProvider = tokenProvider;
    }

    public TokenResult execute(RefreshTokenCommand command) {

        String tokenHash = tokenHasher.hash(command.refreshToken());

        RefreshToken refreshTokenEntity = refreshTokenRepository.findByTokenHash(tokenHash)
                .orElseThrow(InvalidRefreshTokenException::new);

        if(refreshTokenEntity.isRevoked()){
            throw new RevokedRefreshTokenException();
        }

        if(refreshTokenEntity.isExpired(LocalDateTime.now())){
            throw new ExpiredRefreshTokenException();
        }

        User user = userRepository.findById(refreshTokenEntity.getUserId())
                .orElseThrow(UserNotFoundException::new);

        String accessToken = tokenProvider.generateAccessToken(
                user.getEmail().value(),
                user.getRole().name()
        );

        refreshTokenEntity.revoke();

        refreshTokenRepository.save(refreshTokenEntity);

        String newRefreshToken = refreshTokenGenerator.generate();

        String newRefreshTokenHash = tokenHasher.hash(newRefreshToken);
        LocalDateTime newExpiresAt = refreshTokenExpirationProvider.expiresAt();

        RefreshToken newRefreshTokenEntity = RefreshToken.create(
                user.getId(),
                newRefreshTokenHash,
                newExpiresAt
        );

        refreshTokenRepository.save(newRefreshTokenEntity);

        return new TokenResult(accessToken, newRefreshToken);
    }

}