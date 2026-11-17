package com.tomazbr9.bfinance.categories.application.usecase;

import com.tomazbr9.bfinance.categories.application.command.CreateCategoryCommand;
import com.tomazbr9.bfinance.categories.application.dto.CategoryResponse;
import com.tomazbr9.bfinance.categories.application.exception.CategoryNotFoundException;
import com.tomazbr9.bfinance.categories.domain.entity.Category;
import com.tomazbr9.bfinance.categories.domain.repository.CategoryRepository;
import com.tomazbr9.bfinance.shared.security.CurrentUserProvider;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;
    private final CurrentUserProvider currentUserProvider;

    public DeleteCategoryUseCase(CategoryRepository categoryRepository, CurrentUserProvider currentUserProvider) {
        this.categoryRepository = categoryRepository;
        this.currentUserProvider = currentUserProvider;
    }

    public void execute(UUID id){

        UUID userId = currentUserProvider.getCurrentUserId();

        Category category = categoryRepository.findByIdAndUserId(id, userId)
                .orElseThrow(CategoryNotFoundException::new);

        categoryRepository.deleteById(category.getId());
    }
}
