package com.trebol.inventory.adapters.driven.jpa.mysql.adapter;

import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.ISaleDetailEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.ISaleDetailRepository;
import com.trebol.inventory.domain.model.SaleDetail;
import com.trebol.inventory.domain.spi.ISaleDetailPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaleDetailAdapter implements ISaleDetailPersistencePort {

    private final ISaleDetailRepository repository;
    private final ISaleDetailEntityMapper mapper;

    @Override
    public void saveSaleDetail(SaleDetail saleDetail) {
        repository.save(mapper.toSaleDetailEntity(saleDetail));
    }
}
