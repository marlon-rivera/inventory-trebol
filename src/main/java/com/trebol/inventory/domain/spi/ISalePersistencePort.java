package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.Sale;

import java.util.List;

public interface ISalePersistencePort {

    Sale saveSale(Sale sale);
    List<Sale> getSales();

}
