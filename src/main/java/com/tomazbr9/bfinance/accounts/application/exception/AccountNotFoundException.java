package com.tomazbr9.bfinance.accounts.application.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super("Conta não encontrada.");
    }
}
