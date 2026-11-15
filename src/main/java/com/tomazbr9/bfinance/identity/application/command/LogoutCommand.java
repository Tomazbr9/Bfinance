package com.tomazbr9.bfinance.identity.application.command;

public record LogoutCommand(
        String refreshToken
) {
}