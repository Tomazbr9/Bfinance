package com.tomazbr9.bfinance.accounts.infrastructure.mapper;

import com.tomazbr9.bfinance.accounts.domain.entity.Account;
import com.tomazbr9.bfinance.accounts.infrastructure.entity.AccountJpaEntity;

public final class AccountMapper {

    private AccountMapper(){

    }

    public static Account toEntity(AccountJpaEntity jpaEntity){
        return Account.restore(
                jpaEntity.getId(),
                jpaEntity.getUserId(),
                jpaEntity.getName(),
                jpaEntity.getInitialBalance()
        );
    }

    public static AccountJpaEntity toJpaEntity(Account account){
        return AccountJpaEntity.builder()
                .id(account.getId())
                .userId(account.getUserId())
                .name(account.getName())
                .initialBalance(account.getInitialBalance())
                .build();
    }
}
