package com.tomazbr9.bfinance.accounts.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountResponse(
        UUID id,
        String name,
        BigDecimal initialBalance
) {
}
