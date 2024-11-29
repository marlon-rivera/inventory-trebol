package com.trebol.inventory.adapters.driven.jpa.mysql.repository;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    List<PurchaseEntity> findByDatePurchasedBetween(LocalDate start, LocalDate end);

}
