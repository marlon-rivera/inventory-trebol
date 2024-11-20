package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.PurchaseDetail;

import java.util.List;

public interface IPurchaseDetailPersistencePort {

    void savePurchaseDetail(PurchaseDetail detail);
    List<PurchaseDetail> loadPurchaseDetailsByIdPurchase(Long id);

}
