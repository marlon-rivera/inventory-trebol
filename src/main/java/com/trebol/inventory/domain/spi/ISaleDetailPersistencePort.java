package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.SaleDetail;

public interface ISaleDetailPersistencePort {

    void saveSaleDetail(SaleDetail saleDetail);

}
