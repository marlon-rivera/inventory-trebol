package com.trebol.inventory.adapters.driven.jpa.mysql.repository;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ISupplierRepository extends JpaRepository<SupplierEntity, String> {

    Optional<SupplierEntity> findById(String id);
    List<SupplierEntity> findAllByActiveTrue();

}
