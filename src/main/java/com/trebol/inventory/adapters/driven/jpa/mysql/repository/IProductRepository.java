package com.trebol.inventory.adapters.driven.jpa.mysql.repository;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<ProductEntity, String> {

    List<ProductEntity> findByActiveTrue();
    Integer countByCategoryId(Long id);
    @Query("SELECT p FROM product p JOIN p.suppliers s WHERE s.id = :supplierId and p.active = true")
    List<ProductEntity> findProductEntitiesBySupplierId(@Param("supplierId") String supplierId);

}
