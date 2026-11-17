package com.tomazbr9.bfinance.categories.domain.repository;

import com.tomazbr9.bfinance.categories.domain.entity.Category;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {

    Optional<Category> findById(UUID id);

    Category save(Category category);

    void deleteById(UUID id);

    Optional<Category> findByIdAndUserId(UUID id, UUID userId);

}
