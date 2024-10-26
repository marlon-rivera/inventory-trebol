package com.trebol.inventory.adapters.driven.jpa.mysql.adapter;

import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.trebol.inventory.domain.model.Category;
import com.trebol.inventory.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository repository;
    private final ICategoryEntityMapper mapper;

    @Override
    public void saveCategory(Category category) {
        repository.save(mapper.toCategoryEntity(category));
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return mapper.toOptionalCategory(repository.findByName(name));
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return mapper.toOptionalCategory(repository.findById(id));
    }

    @Override
    public List<Category> getAllCategories() {
        return mapper.toCategoriesList(repository.findAll());
    }
}
