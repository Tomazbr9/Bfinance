package com.tomazbr9.bfinance.categories.infrastructure.persistence;

import com.tomazbr9.bfinance.categories.domain.entity.Category;
import com.tomazbr9.bfinance.categories.domain.repository.CategoryRepository;
import com.tomazbr9.bfinance.categories.infrastructure.entity.CategoryJpaEntity;
import com.tomazbr9.bfinance.categories.infrastructure.mapper.CategoryMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CategoryJpaRepositoryAdapter implements CategoryRepository {

    private final CategoryJpaRepository repository;
    private final EntityManager entityManager;


    @Override
    public Optional<Category> findById(UUID id) {
        return repository.findById(id).map(CategoryMapper::toEntity);
    }

    @Override
    public Category save(Category category) {

        CategoryJpaEntity jpaEntity = CategoryMapper.toJpaEntity(category);

        if(!repository.existsById(jpaEntity.getId())){
            entityManager.persist(jpaEntity);
            return category;
        }

        CategoryJpaEntity saved = repository.save(jpaEntity);

        return CategoryMapper.toEntity(saved);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Category> findByIdAndUserId(UUID id, UUID userId) {
        return repository.findByIdAndUserId(id, userId).map(CategoryMapper::toEntity);
    }
}
