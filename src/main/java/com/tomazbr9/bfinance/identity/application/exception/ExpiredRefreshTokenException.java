package com.tomazbr9.bfinance.identity.application.exception;

public class ExpiredRefreshTokenException extends RuntimeException {
    public ExpiredRefreshTokenException() {
        super("Refresh Token expirado");
    }
}