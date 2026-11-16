package com.tomazbr9.bfinance.accounts.application.usecase;

import com.tomazbr9.bfinance.accounts.application.command.CreateAccountCommand;
import com.tomazbr9.bfinance.accounts.application.dto.AccountResponse;
import com.tomazbr9.bfinance.accounts.domain.entity.Account;
import com.tomazbr9.bfinance.accounts.domain.repository.AccountRepository;
import com.tomazbr9.bfinance.shared.security.CurrentUserProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CreateAccountUseCase {

    private AccountRepository accountRepository;
    private CurrentUserProvider currentUserProvider;

    @Transactional
    public AccountResponse execute(CreateAccountCommand command){

        UUID userId = currentUserProvider.getCurrentUserId();

        Account account = Account.create(
                userId,
                command.name(),
                command.initialBalance()
        );

        Account saved = accountRepository.save(account);

        return new AccountResponse(saved.getId(), saved.getName(), saved.getInitialBalance());

    }




}
