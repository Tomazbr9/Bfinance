package com.tomazbr9.bfinance.identity.application.dto;

public record TokenResult(
        String accessToken,
        String refreshToken
) {}