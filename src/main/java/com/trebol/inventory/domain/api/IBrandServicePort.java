package com.trebol.inventory.domain.api;

import com.trebol.inventory.domain.model.Brand;

import java.util.List;

public interface IBrandServicePort {

    void saveBrand(String name);
    List<Brand> getAllBrands();

}
