package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.Purchase;

public interface IPurchasePersistencePort {

    Purchase savePurchase(Purchase purchase);

}
