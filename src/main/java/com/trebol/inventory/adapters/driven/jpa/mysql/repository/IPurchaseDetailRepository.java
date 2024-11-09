package com.trebol.inventory.adapters.driven.jpa.mysql.repository;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.PurchaseDetailEntity;
import com.trebol.inventory.adapters.driven.jpa.mysql.entity.PurchaseDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPurchaseDetailRepository extends JpaRepository<PurchaseDetailEntity, PurchaseDetailId> {
}
