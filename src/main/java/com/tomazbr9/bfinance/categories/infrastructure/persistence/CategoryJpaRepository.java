package com.tomazbr9.bfinance.categories.infrastructure.persistence;

import com.tomazbr9.bfinance.categories.infrastructure.entity.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, UUID> {

    Optional<CategoryJpaEntity> findByIdAndUserId(UUID id, UUID userId);

}
