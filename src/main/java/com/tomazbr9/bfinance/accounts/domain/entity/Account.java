package com.tomazbr9.bfinance.accounts.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {

    private UUID id;

    private UUID userId;

    private String name;

    private BigDecimal initialBalance;


    public static Account create(UUID userId, String name, BigDecimal initialBalance){
        Account account = new Account();

        account.id = UUID.randomUUID();
        account.userId = userId;
        account.name = name;
        account.initialBalance = initialBalance;

        return account;
    }

    public static Account restore(UUID id, UUID userId, String name, BigDecimal initialBalance){
        Account account = new Account();

        account.id = id;
        account.userId = userId;
        account.name = name;
        account.initialBalance = initialBalance;

        return account;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }
}
