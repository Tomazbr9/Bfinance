package com.tomazbr9.bfinance.identity.application.port.out;

public interface TokenProvider {
    String generateAccessToken(String email, String role);
}