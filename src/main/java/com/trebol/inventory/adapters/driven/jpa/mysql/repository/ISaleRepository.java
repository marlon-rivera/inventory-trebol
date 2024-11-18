package com.trebol.inventory.adapters.driven.jpa.mysql.repository;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {
}
