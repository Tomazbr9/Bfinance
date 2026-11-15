package com.tomazbr9.bfinance.identity.application.usecase;

import com.tomazbr9.bfinance.identity.application.command.AuthenticateUserCommand;
import com.tomazbr9.bfinance.identity.application.dto.AuthenticatedUser;
import com.tomazbr9.bfinance.identity.application.dto.TokenResult;
import com.tomazbr9.bfinance.identity.application.port.out.*;
import com.tomazbr9.bfinance.identity.domain.entity.RefreshToken;
import com.tomazbr9.bfinance.identity.domain.repository.RefreshTokenRepository;
import com.tomazbr9.bfinance.identity.domain.valueobject.Email;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticateUserUseCase {

    private final UserAuthentication userAuthentication;
    private final TokenProvider tokenProvider;

    private final RefreshTokenGenerator refreshTokenGenerator;
    private final TokenHasher tokenHasher;
    private final RefreshTokenExpirationProvider refreshTokenExpirationProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthenticateUserUseCase(
            UserAuthentication userAuthentication,
            TokenProvider tokenprovider,
            RefreshTokenGenerator refreshTokenGenerator,
            TokenHasher tokenHasher,
            RefreshTokenExpirationProvider refreshTokenExpirationProvider,
            RefreshTokenRepository refreshTokenRepository
    ) {
        this.userAuthentication = userAuthentication;
        this.tokenProvider = tokenprovider;
        this.refreshTokenGenerator = refreshTokenGenerator;
        this.tokenHasher = tokenHasher;
        this.refreshTokenExpirationProvider = refreshTokenExpirationProvider;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public TokenResult execute(AuthenticateUserCommand command) {

        Email email = Email.of(command.email());

        AuthenticatedUser user =
                userAuthentication.authenticate(email.value(), command.password());

        String accessToken =
                tokenProvider.generateAccessToken(
                        user.email().value(),
                        user.role()
                );

        String refreshToken = refreshTokenGenerator.generate();
        String refreshTokenHash = tokenHasher.hash(refreshToken);
        LocalDateTime expiresAt = refreshTokenExpirationProvider.expiresAt();

        RefreshToken refreshTokenEntity = RefreshToken.create(
                user.id(),
                refreshTokenHash,
                expiresAt
        );

        refreshTokenRepository.save(refreshTokenEntity);

        return new TokenResult(
                accessToken,
                refreshToken
        );
    }
}