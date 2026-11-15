package com.tomazbr9.bfinance.identity.application.usecase;

import com.tomazbr9.bfinance.identity.application.command.CreateUserCommand;
import com.tomazbr9.bfinance.identity.application.exception.EmailAlreadyRegisteredException;
import com.tomazbr9.bfinance.identity.application.port.out.PasswordHasher;
import com.tomazbr9.bfinance.identity.domain.entity.User;
import com.tomazbr9.bfinance.identity.domain.repository.UserRepository;
import com.tomazbr9.bfinance.identity.domain.valueobject.Email;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public CreateUserUseCase(
            UserRepository userRepository,
            PasswordHasher passwordHasher
    ) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public UUID execute(CreateUserCommand command) {

        Email email = Email.of(command.email());

        if (userRepository
                .findByEmail(email)
                .isPresent()) {

            throw new EmailAlreadyRegisteredException();
        }

        String passwordHash =
                passwordHasher.hash(command.password());

        User user = User.create(
                command.name(),
                email,
                passwordHash
        );

        User savedUser = userRepository.save(user);

        return savedUser.getId();

    }
}