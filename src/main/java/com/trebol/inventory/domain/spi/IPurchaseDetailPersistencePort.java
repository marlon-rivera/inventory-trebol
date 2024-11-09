package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.PurchaseDetail;

import java.util.List;

public interface IPurchaseDetailPersistencePort {

    void savePurchaseDetails(List<PurchaseDetail> details);

}
