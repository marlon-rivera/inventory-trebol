package com.trebol.inventory.domain.api.usecase;

import com.trebol.inventory.domain.api.ICategoryServicePort;
import com.trebol.inventory.domain.exception.CategoryAlreadyExistsException;
import com.trebol.inventory.domain.model.Category;
import com.trebol.inventory.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryUseCaseImpl implements ICategoryServicePort {

    private final ICategoryPersistencePort persistencePort;

    @Override
    public void saveCategory(String name) {
        if(persistencePort.getCategoryByName(name).isPresent()) throw new CategoryAlreadyExistsException();
        persistencePort.saveCategory(new Category(null, name));
    }

    @Override
    public List<Category> getAllCategories() {
        return persistencePort.getAllCategories();
    }
}
