package com.tomazbr9.bfinance.accounts.application.usecase;

import com.tomazbr9.bfinance.accounts.application.command.CreateAccountCommand;
import com.tomazbr9.bfinance.accounts.application.dto.AccountResponse;
import com.tomazbr9.bfinance.accounts.application.exception.AccountNotFoundException;
import com.tomazbr9.bfinance.accounts.domain.entity.Account;
import com.tomazbr9.bfinance.accounts.domain.repository.AccountRepository;
import com.tomazbr9.bfinance.shared.security.CurrentUserProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeleteAccountUseCase {

    private final AccountRepository accountRepository;
    private final CurrentUserProvider currentUserProvider;

    public DeleteAccountUseCase(AccountRepository accountRepository, CurrentUserProvider currentUserProvider) {
        this.accountRepository = accountRepository;
        this.currentUserProvider = currentUserProvider;
    }

    @Transactional
    public void execute(UUID accountId){

        UUID userId = currentUserProvider.getCurrentUserId();

        Account account = accountRepository.findByIdAndUserId(accountId, userId)
                .orElseThrow(AccountNotFoundException::new);

        accountRepository.deleteById(account.getId());

    }

}
