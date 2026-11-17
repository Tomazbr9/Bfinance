package com.tomazbr9.bfinance.accounts.application.usecase;

import com.tomazbr9.bfinance.accounts.application.command.CreateAccountCommand;
import com.tomazbr9.bfinance.accounts.application.dto.AccountResponse;
import com.tomazbr9.bfinance.accounts.domain.entity.Account;
import com.tomazbr9.bfinance.accounts.domain.repository.AccountRepository;
import com.tomazbr9.bfinance.shared.security.CurrentUserProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateAccountUseCaseTest {

    private AccountRepository repository;
    private CurrentUserProvider currentUserProvider;
    private CreateAccountUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(AccountRepository.class);
        currentUserProvider = mock(CurrentUserProvider.class);
        useCase = new CreateAccountUseCase(repository, currentUserProvider);
    }

    @Test
    void shouldCreateAccountForAuthenticatedUser() {
        UUID authenticatedUserId = UUID.randomUUID();
        when(currentUserProvider.getCurrentUserId()).thenReturn(authenticatedUserId);
        when(repository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AccountResponse response = useCase.execute(
                new CreateAccountCommand("Nubank", new BigDecimal("300.00"))
        );

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        verify(repository).save(captor.capture());
        Account saved = captor.getValue();

        assertEquals(authenticatedUserId, saved.getUserId());
        assertEquals("Nubank", saved.getName());
        assertEquals(0, new BigDecimal("300.00").compareTo(saved.getInitialBalance()));
        assertEquals(saved.getId(), response.id());
        assertEquals(saved.getName(), response.name());
        assertEquals(0, saved.getInitialBalance().compareTo(response.initialBalance()));
    }

    @Test
    void shouldUseZeroBalanceWhenCommandOmitsInitialBalance() {
        when(currentUserProvider.getCurrentUserId()).thenReturn(UUID.randomUUID());
        when(repository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AccountResponse response = useCase.execute(new CreateAccountCommand("Dinheiro", null));

        assertEquals(0, BigDecimal.ZERO.compareTo(response.initialBalance()));
        verify(repository, times(1)).save(any(Account.class));
    }
}
