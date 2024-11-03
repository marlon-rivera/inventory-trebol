package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.Batch;
import com.trebol.inventory.domain.model.Product;

import java.util.List;

public interface IBatchPersistencePort {

    List<Batch> getBatchsByProduct(Product product);

}
