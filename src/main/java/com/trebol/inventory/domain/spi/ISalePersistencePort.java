package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.Sale;

public interface ISalePersistencePort {

    Sale saveSale(Sale sale);

}
