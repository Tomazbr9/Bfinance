package com.tomazbr9.bfinance.accounts.domain.repository;

import com.tomazbr9.bfinance.accounts.domain.entity.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    Optional<Account> findById(UUID id);

    Optional<Account> findByIdAndUserId(UUID id, UUID userId);

    Account save(Account account);

    void deleteById(UUID id);

}
