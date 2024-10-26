package com.trebol.inventory.adapters.driven.jpa.mysql.mapper;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.trebol.inventory.domain.model.Category;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {

    Category toCategory(CategoryEntity source);

    CategoryEntity toCategoryEntity(Category source);

    List<Category> toCategoriesList(List<CategoryEntity> source);

    default Optional<Category> toOptionalCategory(Optional<CategoryEntity> source){
        return source.map(this::toCategory);
    }
}
