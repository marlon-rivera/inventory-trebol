package com.trebol.inventory.adapters.driven.jpa.mysql.adapter;

import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.ISaleDetailEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.ISaleDetailRepository;
import com.trebol.inventory.domain.model.SaleDetail;
import com.trebol.inventory.domain.spi.ISaleDetailPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SaleDetailAdapter implements ISaleDetailPersistencePort {

    private final ISaleDetailRepository repository;
    private final ISaleDetailEntityMapper mapper;

    @Override
    public void saveSaleDetail(SaleDetail saleDetail) {
        repository.save(mapper.toSaleDetailEntity(saleDetail));
    }

    @Override
    public List<SaleDetail> loadSaleDetailsBySaleId(Long saleId) {
        return mapper.toSaleDetails(repository.findBySaleId(saleId));
    }

    @Override
    public List<SaleDetail> loadSaleDetailsBySaleIdAndProductId(Long saleId, String productId) {
        return mapper.toSaleDetails(repository.findBySaleIdAndProductId(saleId, productId));
    }
}
