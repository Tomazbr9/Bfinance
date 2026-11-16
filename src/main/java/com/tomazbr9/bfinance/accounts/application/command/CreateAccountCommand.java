package com.tomazbr9.bfinance.accounts.application.command;

import java.math.BigDecimal;

public record CreateAccountCommand(
        String name,
        BigDecimal initialBalance
) {

}
