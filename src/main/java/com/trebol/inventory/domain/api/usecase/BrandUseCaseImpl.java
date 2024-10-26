package com.trebol.inventory.domain.api.usecase;

import com.trebol.inventory.domain.api.IBrandServicePort;
import com.trebol.inventory.domain.exception.BrandAlreadyExistsException;
import com.trebol.inventory.domain.model.Brand;
import com.trebol.inventory.domain.spi.IBrandPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BrandUseCaseImpl implements IBrandServicePort {

    private final IBrandPersistencePort persistencePort;

    @Override
    public void saveBrand(String name) {
        if(persistencePort.getBrand(name).isPresent()) throw new BrandAlreadyExistsException();
        persistencePort.saveBrand(new Brand(null, name));
    }

    @Override
    public List<Brand> getAllBrands() {
        return persistencePort.getAllBrands();
    }
}
