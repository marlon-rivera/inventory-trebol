package com.trebol.inventory.adapters.driven.jpa.mysql.repository;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {

    List<SaleEntity> findByDateBetween(LocalDate start, LocalDate end);

}
