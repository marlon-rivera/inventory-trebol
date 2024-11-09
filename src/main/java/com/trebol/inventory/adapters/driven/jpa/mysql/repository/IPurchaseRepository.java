package com.trebol.inventory.adapters.driven.jpa.mysql.repository;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
}
