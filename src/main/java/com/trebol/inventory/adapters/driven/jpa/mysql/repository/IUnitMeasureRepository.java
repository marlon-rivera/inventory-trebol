package com.trebol.inventory.adapters.driven.jpa.mysql.repository;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.UnitMeasureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUnitMeasureRepository extends JpaRepository<UnitMeasureEntity, Long> {

    Optional<UnitMeasureEntity> findByName(String name);

}
