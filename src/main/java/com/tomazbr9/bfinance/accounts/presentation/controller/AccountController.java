package com.tomazbr9.bfinance.accounts.presentation.controller;

import com.tomazbr9.bfinance.accounts.application.command.CreateAccountCommand;
import com.tomazbr9.bfinance.accounts.application.dto.AccountResponse;
import com.tomazbr9.bfinance.accounts.application.usecase.CreateAccountUseCase;
import com.tomazbr9.bfinance.accounts.application.usecase.DeleteAccountUseCase;
import com.tomazbr9.bfinance.accounts.presentation.request.CreateAccountRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid CreateAccountRequest request){

        CreateAccountCommand command = new CreateAccountCommand(
                request.name(),
                request.initialBalance()
        );

        AccountResponse response = createAccountUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID accountId){
        deleteAccountUseCase.execute(accountId);
        return ResponseEntity.noContent().build();
    }

}
