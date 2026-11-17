package com.tomazbr9.bfinance.categories.presentation.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CreateCategoryRequest(

        @NotBlank(message = "Nome da categoria é obrigatorio")
        String name,
        UUID parentId
) {
}
