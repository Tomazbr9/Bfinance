package com.tomazbr9.bfinance.accounts.application.usecase;

import com.tomazbr9.bfinance.accounts.application.dto.AccountResponse;
import com.tomazbr9.bfinance.accounts.application.exception.AccountNotFoundException;
import com.tomazbr9.bfinance.accounts.domain.entity.Account;
import com.tomazbr9.bfinance.accounts.domain.repository.AccountRepository;
import com.tomazbr9.bfinance.shared.security.CurrentUserProvider;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetAccountUseCase {

    private AccountRepository accountRepository;
    private CurrentUserProvider currentUserProvider;

    public AccountResponse execute(UUID accountId){

        UUID userId = currentUserProvider.getCurrentUserId();

        Account account = accountRepository.findByIdAndUserId(accountId, userId)
                .orElseThrow(AccountNotFoundException::new);

        return new AccountResponse(account.getId(), account.getName(), account.getInitialBalance());
    }


}
