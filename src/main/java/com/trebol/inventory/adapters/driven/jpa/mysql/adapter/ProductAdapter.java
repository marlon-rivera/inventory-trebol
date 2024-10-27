package com.trebol.inventory.adapters.driven.jpa.mysql.adapter;

import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.IProductEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.IProductRepository;
import com.trebol.inventory.domain.model.Product;
import com.trebol.inventory.domain.spi.IProductPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductAdapter implements IProductPersistencePort {

    private final IProductRepository repository;
    private final IProductEntityMapper mapper;

    @Override
    public void createProduct(Product product) {
        System.out.println(mapper.toProductEntity(product));
        repository.save(mapper.toProductEntity(product));
    }

    @Override
    public void updateProduct(Product product) {
        repository.save(mapper.toProductEntity(product));
    }

    @Override
    public void deleteProduct(Product product) {
        repository.save(mapper.toProductEntity(product));
    }

    @Override
    public List<Product> getAllProducts() {
        return mapper.toProductsList(repository.findByActiveTrue());
    }

    @Override
    public Integer countProductsByCategory(Long id) {
        return repository.countByCategoryId(id);
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return mapper.toOptionalProduct(repository.findById(id));
    }
}
