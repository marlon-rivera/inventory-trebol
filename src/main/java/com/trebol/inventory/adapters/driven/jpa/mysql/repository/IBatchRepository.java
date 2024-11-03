package com.trebol.inventory.adapters.driven.jpa.mysql.repository;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.BatchEntity;
import com.trebol.inventory.adapters.driven.jpa.mysql.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBatchRepository extends JpaRepository<BatchEntity, Long> {

    List<BatchEntity> findAllByProduct(ProductEntity product);

}
