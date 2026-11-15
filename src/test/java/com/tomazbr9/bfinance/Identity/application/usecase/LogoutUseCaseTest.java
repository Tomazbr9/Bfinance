package com.tomazbr9.bfinance.Identity.application.usecase;

import com.tomazbr9.bfinance.identity.application.command.LogoutCommand;
import com.tomazbr9.bfinance.identity.application.exception.InvalidRefreshTokenException;
import com.tomazbr9.bfinance.identity.application.port.out.TokenHasher;
import com.tomazbr9.bfinance.identity.application.usecase.LogoutUseCase;
import com.tomazbr9.bfinance.identity.domain.entity.RefreshToken;
import com.tomazbr9.bfinance.identity.domain.repository.RefreshTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LogoutUseCaseTest {

    @Mock
    private TokenHasher tokenHasher;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    private LogoutUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new LogoutUseCase(
                tokenHasher,
                refreshTokenRepository
        );
    }

    @Test
    void shouldThrowWhenRefreshTokenDoesNotExist() {

        String rawToken = "refresh-token";
        String hash = "token-hash";

        LogoutCommand command =
                new LogoutCommand(rawToken);

        when(tokenHasher.hash(rawToken))
                .thenReturn(hash);

        when(refreshTokenRepository.findByTokenHash(hash))
                .thenReturn(Optional.empty());

        assertThrows(
                InvalidRefreshTokenException.class,
                () -> useCase.execute(command)
        );

        verify(tokenHasher).hash(rawToken);

        verify(refreshTokenRepository)
                .findByTokenHash(hash);

        verify(refreshTokenRepository, never())
                .save(any());
    }

    @Test
    void shouldRevokeRefreshToken() {

        String rawToken = "refresh-token";
        String hash = "token-hash";

        RefreshToken refreshToken =
                RefreshToken.restore(
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        hash,
                        LocalDateTime.now().plusDays(10),
                        false
                );

        LogoutCommand command =
                new LogoutCommand(rawToken);

        when(tokenHasher.hash(rawToken))
                .thenReturn(hash);

        when(refreshTokenRepository.findByTokenHash(hash))
                .thenReturn(Optional.of(refreshToken));

        useCase.execute(command);

        assertTrue(refreshToken.isRevoked());

        verify(refreshTokenRepository)
                .save(refreshToken);
    }
}