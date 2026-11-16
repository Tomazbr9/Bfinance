package com.tomazbr9.bfinance.accounts.infrastructure.persistence.adapter;

import com.tomazbr9.bfinance.accounts.domain.entity.Account;
import com.tomazbr9.bfinance.accounts.domain.repository.AccountRepository;
import com.tomazbr9.bfinance.accounts.infrastructure.entity.AccountJpaEntity;
import com.tomazbr9.bfinance.accounts.infrastructure.mapper.AccountMapper;
import com.tomazbr9.bfinance.accounts.infrastructure.persistence.repository.AccountJpaRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AccountJpaRepositoryAdapter implements AccountRepository {

    private final AccountJpaRepository repository;
    private final EntityManager entityManager;

    @Override
    public Optional<Account> findById(UUID id) {
        return repository.findById(id).map(AccountMapper::toEntity);
    }

    @Override
    public Optional<Account> findByIdAndUserId(UUID id, UUID userId) {
        return repository.findByIdAndUserId(id, userId).map(AccountMapper::toEntity);
    }

    @Override
    public Account save(Account account) {

        AccountJpaEntity jpaEntity = AccountMapper.toJpaEntity(account);

        if(!repository.existsById(jpaEntity.getId())){
            entityManager.persist(jpaEntity);
            return account;
        }

        AccountJpaEntity saved = repository.save(jpaEntity);

        return AccountMapper.toEntity(saved);

    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

}


