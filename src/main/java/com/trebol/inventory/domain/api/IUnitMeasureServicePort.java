package com.trebol.inventory.domain.api;

import com.trebol.inventory.domain.model.UnitMeasure;

import java.util.List;
import java.util.Optional;

public interface IUnitMeasureServicePort {

    void saveUnitMeasure(String name);
    List<UnitMeasure> getUnitMeasures();

}
