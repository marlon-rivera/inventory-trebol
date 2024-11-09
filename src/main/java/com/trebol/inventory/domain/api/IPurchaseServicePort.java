package com.trebol.inventory.domain.api;

import com.trebol.inventory.domain.model.PurchaseDetail;
import com.trebol.inventory.domain.model.Supplier;

import java.time.LocalDate;
import java.util.List;

public interface IPurchaseServicePort {

    void createPurchase(Supplier supplier, List<PurchaseDetail> detailList, LocalDate purchaseDate);

}
