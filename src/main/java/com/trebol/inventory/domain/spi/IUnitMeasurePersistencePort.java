package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.UnitMeasure;

import java.util.List;
import java.util.Optional;

public interface IUnitMeasurePersistencePort {

    void saveUnitMeasure(UnitMeasure unitMeasure);
    List<UnitMeasure> getUnitMeasures();
    Optional<UnitMeasure> getUnitMeasure(String name);
    Optional<UnitMeasure> getUnitMeasureById(Long id);

}
