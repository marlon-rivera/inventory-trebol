package com.trebol.inventory.adapters.driven.jpa.mysql.adapter;

import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.ISaleEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.ISaleRepository;
import com.trebol.inventory.domain.model.Sale;
import com.trebol.inventory.domain.spi.ISalePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaleAdapter implements ISalePersistencePort {

    private final ISaleRepository repository;
    private final ISaleEntityMapper mapper;

    @Override
    public Sale saveSale(Sale sale) {
        return mapper.toSale(repository.save(mapper.toSaleEntity(sale)));
    }
}
