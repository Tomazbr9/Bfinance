package com.tomazbr9.bfinance.shared.security;

import java.util.UUID;

public interface CurrentUserProvider {

    UUID getCurrentUserId();
}