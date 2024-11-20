package com.trebol.inventory.adapters.driven.jpa.mysql.adapter;

import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.IPurchaseDetailEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.IPurchaseDetailRepository;
import com.trebol.inventory.domain.model.PurchaseDetail;
import com.trebol.inventory.domain.spi.IPurchaseDetailPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PurchaseDetailAdapter implements IPurchaseDetailPersistencePort {

    private final IPurchaseDetailRepository repository;
    private final IPurchaseDetailEntityMapper mapper;

    @Override
    public void savePurchaseDetail(PurchaseDetail detail) {
        repository.save(mapper.toPurchaseDetailEntity(detail));
    }

    @Override
    public List<PurchaseDetail> loadPurchaseDetailsByIdPurchase(Long id) {
        return mapper.toPurchaseDetail(repository.findByPurchaseId(id));
    }
}
