package com.tomazbr9.bfinance.identity.infrastructure.mapper;

import com.tomazbr9.bfinance.identity.domain.entity.User;
import com.tomazbr9.bfinance.identity.domain.valueobject.Email;
import com.tomazbr9.bfinance.identity.infrastructure.entity.UserJpaEntity;

public class UserMapper {

    private UserMapper(){

    }

    public static UserJpaEntity toJpaEntity(User user) {
        return UserJpaEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail().value())
                .passwordHash(user.getPasswordHash())
                .role(user.getRole())
                .build();
    }

    public static User toEntity(UserJpaEntity user) {
        return User.restore(
                user.getId(),
                user.getName(),
                Email.of(user.getEmail()),
                user.getPasswordHash(),
                user.getRole()
        );
    }
}