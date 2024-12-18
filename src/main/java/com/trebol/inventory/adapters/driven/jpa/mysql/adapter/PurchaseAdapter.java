package com.trebol.inventory.adapters.driven.jpa.mysql.adapter;

import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.IPurchaseEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.IPurchaseRepository;
import com.trebol.inventory.domain.model.Purchase;
import com.trebol.inventory.domain.spi.IPurchasePersistencePort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class PurchaseAdapter implements IPurchasePersistencePort {

    private final IPurchaseRepository repository;
    private final IPurchaseEntityMapper mapper;

    @Override
    public Purchase savePurchase(Purchase purchase) {
        return mapper.toPurchase(repository.save(mapper.toPurchaseEntity(purchase)));
    }

    @Override
    public List<Purchase> getPurchases() {
        return mapper.toPurchases(repository.findAll());
    }

    @Override
    public List<Purchase> getPurchasesInRange(LocalDate start, LocalDate end) {
        return mapper.toPurchases(repository.findByDatePurchasedBetween(start, end));
    }
}
