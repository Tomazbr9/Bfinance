package com.tomazbr9.bfinance.accounts.domain.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void shouldCreateAccountWithZeroBalanceWhenNotProvided() {
        UUID userId = UUID.randomUUID();

        Account account = Account.create(userId, "Nubank", null);

        assertNotNull(account.getId());
        assertEquals(userId, account.getUserId());
        assertEquals("Nubank", account.getName());
        assertEquals(0, BigDecimal.ZERO.compareTo(account.getInitialBalance()));
    }

    @Test
    void shouldAllowNegativeInitialBalance() {
        Account account = Account.create(
                UUID.randomUUID(), "Conta corrente", new BigDecimal("-150.50")
        );

        assertEquals(0, new BigDecimal("-150.50").compareTo(account.getInitialBalance()));
    }

    @Test
    void shouldRestoreAccountPreservingItsIdentityAndData() {
        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        BigDecimal balance = new BigDecimal("250.00");

        Account account = Account.restore(id, userId, "Inter", balance);

        assertEquals(id, account.getId());
        assertEquals(userId, account.getUserId());
        assertEquals("Inter", account.getName());
        assertEquals(0, balance.compareTo(account.getInitialBalance()));
    }
}
