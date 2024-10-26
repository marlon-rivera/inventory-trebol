package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);
    Optional<Category> getCategoryByName(String name);
    Optional<Category> getCategoryById(Long id);
    List<Category> getAllCategories();

}
