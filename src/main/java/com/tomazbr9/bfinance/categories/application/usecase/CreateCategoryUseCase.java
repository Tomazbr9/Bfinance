package com.tomazbr9.bfinance.categories.application.usecase;

import com.tomazbr9.bfinance.categories.application.command.CreateCategoryCommand;
import com.tomazbr9.bfinance.categories.application.dto.CategoryResponse;
import com.tomazbr9.bfinance.categories.domain.entity.Category;
import com.tomazbr9.bfinance.categories.domain.repository.CategoryRepository;
import com.tomazbr9.bfinance.shared.security.CurrentUserProvider;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;
    private final CurrentUserProvider currentUserProvider;

    public CreateCategoryUseCase(CategoryRepository categoryRepository, CurrentUserProvider currentUserProvider) {
        this.categoryRepository = categoryRepository;
        this.currentUserProvider = currentUserProvider;
    }

    public CategoryResponse execute(CreateCategoryCommand command){

        UUID userId = currentUserProvider.getCurrentUserId();

        Category category = Category.create(userId, command.name(), command.parentId());

        Category saved = categoryRepository.save(category);

        return new CategoryResponse(saved.getId(), saved.getName());
    }
}
