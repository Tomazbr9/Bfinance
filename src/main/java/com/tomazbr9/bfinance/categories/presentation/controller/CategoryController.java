package com.tomazbr9.bfinance.categories.presentation.controller;

import com.tomazbr9.bfinance.categories.application.command.CreateCategoryCommand;
import com.tomazbr9.bfinance.categories.application.dto.CategoryResponse;
import com.tomazbr9.bfinance.categories.application.usecase.CreateCategoryUseCase;
import com.tomazbr9.bfinance.categories.application.usecase.DeleteCategoryUseCase;
import com.tomazbr9.bfinance.categories.presentation.request.CreateCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CreateCategoryRequest request){

        CreateCategoryCommand command = new CreateCategoryCommand(
                request.name(),
                request.parentId()
        );

        CategoryResponse response = createCategoryUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID categoryId){
        deleteCategoryUseCase.execute(categoryId);

        return ResponseEntity.noContent().build();
    }

}
