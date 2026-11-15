package com.tomazbr9.bfinance.identity.application.command;

public record CreateUserCommand(
        String name,
        String email,
        String password
) {
}