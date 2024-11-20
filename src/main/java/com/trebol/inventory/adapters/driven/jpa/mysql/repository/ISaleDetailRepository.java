package com.trebol.inventory.adapters.driven.jpa.mysql.repository;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.SaleDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISaleDetailRepository extends JpaRepository<SaleDetailEntity, Long> {

    List<SaleDetailEntity> findBySaleId(Long saleId);

}
