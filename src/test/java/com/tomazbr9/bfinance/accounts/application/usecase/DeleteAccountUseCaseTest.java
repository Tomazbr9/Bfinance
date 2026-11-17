package com.tomazbr9.bfinance.accounts.application.usecase;

import com.tomazbr9.bfinance.accounts.application.exception.AccountNotFoundException;
import com.tomazbr9.bfinance.accounts.domain.entity.Account;
import com.tomazbr9.bfinance.accounts.domain.repository.AccountRepository;
import com.tomazbr9.bfinance.shared.security.CurrentUserProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteAccountUseCaseTest {

    private AccountRepository repository;
    private CurrentUserProvider currentUserProvider;
    private DeleteAccountUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(AccountRepository.class);
        currentUserProvider = mock(CurrentUserProvider.class);
        useCase = new DeleteAccountUseCase(repository, currentUserProvider);
    }

    @Test
    void shouldDeleteAccountBelongingToAuthenticatedUser() {
        UUID userId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        Account account = Account.restore(accountId, userId, "Inter", BigDecimal.ZERO);
        when(currentUserProvider.getCurrentUserId()).thenReturn(userId);
        when(repository.findByIdAndUserId(accountId, userId)).thenReturn(Optional.of(account));

        useCase.execute(accountId);

        verify(repository).findByIdAndUserId(accountId, userId);
        verify(repository).deleteById(accountId);
    }

    @Test
    void shouldNotDeleteAccountThatIsNotOwnedByAuthenticatedUser() {
        UUID userId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        when(currentUserProvider.getCurrentUserId()).thenReturn(userId);
        when(repository.findByIdAndUserId(accountId, userId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> useCase.execute(accountId));

        verify(repository, never()).deleteById(any(UUID.class));
    }

    @Test
    void shouldNotDeleteNonexistentAccount() {
        UUID userId = UUID.randomUUID();
        UUID nonexistentAccountId = UUID.randomUUID();
        when(currentUserProvider.getCurrentUserId()).thenReturn(userId);
        when(repository.findByIdAndUserId(nonexistentAccountId, userId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> useCase.execute(nonexistentAccountId));

        verify(repository, never()).deleteById(any(UUID.class));
    }
}
