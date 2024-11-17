package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.PurchaseDetail;

public interface IPurchaseDetailPersistencePort {

    void savePurchaseDetail(PurchaseDetail detail);

}
