package com.tomazbr9.bfinance.identity.application.port.out;

import java.time.LocalDateTime;

public interface RefreshTokenExpirationProvider {
    LocalDateTime expiresAt();
}