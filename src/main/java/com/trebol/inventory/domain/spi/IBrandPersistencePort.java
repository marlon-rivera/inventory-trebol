package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.Brand;

import java.util.List;
import java.util.Optional;

public interface IBrandPersistencePort {

    void saveBrand(Brand brand);
    Optional<Brand> getBrand(String brandName);
    Optional<Brand> getBrandById(Long id);
    List<Brand> getAllBrands();

}
