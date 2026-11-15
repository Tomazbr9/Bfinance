package com.tomazbr9.bfinance.identity.application.command;

public record AuthenticateUserCommand(
        String email,
        String password
) {
}