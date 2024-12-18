package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.Purchase;

import java.time.LocalDate;
import java.util.List;

public interface IPurchasePersistencePort {

    Purchase savePurchase(Purchase purchase);
    List<Purchase> getPurchases();
    List<Purchase> getPurchasesInRange(LocalDate start, LocalDate end);

}
