package com.tomazbr9.bfinance.categories.domain.entity;

import java.util.UUID;

public class Category {

    private UUID id;

    private UUID userId;

    private String name;

    private UUID parentId;

    public static Category create(UUID userId, String name, UUID parentId){
        Category category = new Category();

        category.id = UUID.randomUUID();
        category.userId = userId;
        category.name = name;
        category.parentId = parentId;

        return category;

    }

    public static Category restore( UUID id, UUID userId, String name, UUID parentId){
        Category category = new Category();

        category.id = id;
        category.userId = userId;
        category.name = name;
        category.parentId = parentId;

        return category;

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getParentId() {
        return parentId;
    }
}
