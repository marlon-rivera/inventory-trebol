package com.trebol.inventory.domain.api;

import com.trebol.inventory.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {

    void saveCategory(String name);
    List<Category> getAllCategories();

}
