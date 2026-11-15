package com.tomazbr9.bfinance.identity.presentation.response;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}