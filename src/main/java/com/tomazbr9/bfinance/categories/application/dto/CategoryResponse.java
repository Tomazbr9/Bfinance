package com.tomazbr9.bfinance.categories.application.dto;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name
) {
}
