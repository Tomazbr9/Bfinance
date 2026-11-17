package com.tomazbr9.bfinance.categories.infrastructure.mapper;

import com.tomazbr9.bfinance.categories.domain.entity.Category;
import com.tomazbr9.bfinance.categories.infrastructure.entity.CategoryJpaEntity;

public final class CategoryMapper {

    private CategoryMapper(){

    }

    public static Category toEntity(CategoryJpaEntity jpaEntity){
        return Category.restore(
                jpaEntity.getId(),
                jpaEntity.getUserId(),
                jpaEntity.getName(),
                jpaEntity.getParentId()
        );
    }

    public static  CategoryJpaEntity toJpaEntity(Category entity){
        return CategoryJpaEntity.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .name(entity.getName())
                .parentId(entity.getParentId())
                .build();
    }
}
