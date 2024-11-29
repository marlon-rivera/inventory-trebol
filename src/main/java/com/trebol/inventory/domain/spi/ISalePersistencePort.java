package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.Sale;

import java.time.LocalDate;
import java.util.List;

public interface ISalePersistencePort {

    Sale saveSale(Sale sale);
    List<Sale> getSales();

    List<Sale> getSalesInRange(LocalDate start, LocalDate end);
}
