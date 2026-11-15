package com.tomazbr9.bfinance.identity.application.exception;

public class RevokedRefreshTokenException extends RuntimeException {
    public RevokedRefreshTokenException() {
        super("Refresh Token revogado");
    }
}