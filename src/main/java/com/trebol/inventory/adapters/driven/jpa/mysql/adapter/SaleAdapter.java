package com.trebol.inventory.adapters.driven.jpa.mysql.adapter;

import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.ISaleEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.ISaleRepository;
import com.trebol.inventory.domain.model.Sale;
import com.trebol.inventory.domain.spi.ISalePersistencePort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class SaleAdapter implements ISalePersistencePort {

    private final ISaleRepository repository;
    private final ISaleEntityMapper mapper;

    @Override
    public Sale saveSale(Sale sale) {
        return mapper.toSale(repository.save(mapper.toSaleEntity(sale)));
    }

    @Override
    public List<Sale> getSales() {
        return mapper.toSales(repository.findAll());
    }

    @Override
    public List<Sale> getSalesInRange(LocalDate start, LocalDate end) {
        return mapper.toSales(repository.findByDateBetween(start, end));
    }
}
