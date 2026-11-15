package com.tomazbr9.bfinance.identity.application.port.out;

public interface PasswordHasher {
    String hash(String password);
}