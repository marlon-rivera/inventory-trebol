package com.trebol.inventory.adapters.driven.jpa.mysql.adapter;

import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.trebol.inventory.domain.model.Brand;
import com.trebol.inventory.domain.spi.IBrandPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {

    private final IBrandRepository repository;
    private final IBrandEntityMapper mapper;

    @Override
    public void saveBrand(Brand brand) {
        repository.save(mapper.toBrandEntity(brand));
    }

    @Override
    public Optional<Brand> getBrand(String brandName) {
        return mapper.toBrandOptional(repository.findByName(brandName));
    }

    @Override
    public Optional<Brand> getBrandById(Long id) {
        return mapper.toBrandOptional(repository.findById(id));
    }

    @Override
    public List<Brand> getAllBrands() {
        return mapper.toBrandList(repository.findAll());
    }
}
