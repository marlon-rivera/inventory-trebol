package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.Batch;
import com.trebol.inventory.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface IBatchPersistencePort {

    List<Batch> getBatchsByProduct(Product product);
    Batch saveBatch(Batch batch);
    Optional<Batch> getBatchById(Long id);
    void saveAll(List<Batch> batches);

}
