package com.trebol.inventory.adapters.driven.jpa.mysql.adapter;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.PurchaseDetailEntity;
import com.trebol.inventory.adapters.driven.jpa.mysql.entity.PurchaseDetailId;
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
    public void savePurchaseDetails(List<PurchaseDetail> details) {
        for (PurchaseDetail detail : details) {
            PurchaseDetailEntity purchaseDetailEntity = mapper.toPurchaseDetailEntity(detail);
            purchaseDetailEntity.setId(new PurchaseDetailId(purchaseDetailEntity.getPurchase().getId(), purchaseDetailEntity.getProduct().getId()));
            repository.save(purchaseDetailEntity);
        }
    }
}
