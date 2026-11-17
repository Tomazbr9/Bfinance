package com.tomazbr9.bfinance.categories.application.command;

import java.util.UUID;

public record CreateCategoryCommand(
        String name,
        UUID parentId
) {
}
