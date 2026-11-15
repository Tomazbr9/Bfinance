package com.tomazbr9.bfinance.identity.application.port.out;

public interface TokenHasher {
    String hash(String token);
}