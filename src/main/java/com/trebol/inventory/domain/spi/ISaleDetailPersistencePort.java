package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.SaleDetail;

import java.util.List;

public interface ISaleDetailPersistencePort {

    void saveSaleDetail(SaleDetail saleDetail);
    List<SaleDetail> loadSaleDetailsBySaleId(Long saleId);
    List<SaleDetail> loadSaleDetailsBySaleIdAndProductId(Long saleId, String productId);

}
