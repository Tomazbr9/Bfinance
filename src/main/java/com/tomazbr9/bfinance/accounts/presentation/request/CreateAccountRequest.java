package com.tomazbr9.bfinance.accounts.presentation.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record CreateAccountRequest(

        @NotBlank(message = "Nome da conta é obrigatorio")
        String name,

        BigDecimal initialBalance
) {
}
