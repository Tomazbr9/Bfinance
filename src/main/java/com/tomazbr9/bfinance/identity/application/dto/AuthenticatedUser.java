package com.tomazbr9.bfinance.identity.application.dto;

import com.tomazbr9.bfinance.identity.domain.valueobject.Email;

import java.util.UUID;

public record AuthenticatedUser(
        UUID id,
        Email email,
        String role
) {}