package com.trebol.inventory.adapters.driven.jpa.mysql.repository;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.SaleDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleDetailRepository extends JpaRepository<SaleDetailEntity, Long> {
}
