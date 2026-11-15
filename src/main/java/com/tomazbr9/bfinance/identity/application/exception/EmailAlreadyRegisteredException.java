package com.tomazbr9.bfinance.identity.application.exception;

public class EmailAlreadyRegisteredException extends RuntimeException {
    public EmailAlreadyRegisteredException() {
        super("E-mail já cadastrado");
    }
}