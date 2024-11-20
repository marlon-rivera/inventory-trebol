package com.trebol.inventory.adapters.driven.jpa.mysql.repository;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.PurchaseDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPurchaseDetailRepository extends JpaRepository<PurchaseDetailEntity, Long> {

    List<PurchaseDetailEntity> findByPurchaseId(Long id);

}
