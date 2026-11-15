package com.tomazbr9.bfinance.identity.infrastructure.security;

import com.tomazbr9.bfinance.identity.domain.entity.User;
import com.tomazbr9.bfinance.identity.domain.repository.UserRepository;
import com.tomazbr9.bfinance.identity.domain.valueobject.Email;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {

        User user = userRepository
                .findByEmail(Email.of(email))
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Usuário não encontrado"
                        )
                );

        return new UserDetailsImpl(user);
    }
}