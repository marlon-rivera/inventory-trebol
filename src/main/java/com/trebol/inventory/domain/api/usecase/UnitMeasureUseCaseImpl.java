package com.trebol.inventory.domain.api.usecase;

import com.trebol.inventory.domain.api.IUnitMeasureServicePort;
import com.trebol.inventory.domain.exception.UnitMeasureAlreadyExistsException;
import com.trebol.inventory.domain.model.UnitMeasure;
import com.trebol.inventory.domain.spi.IUnitMeasurePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UnitMeasureUseCaseImpl implements IUnitMeasureServicePort {

    private final IUnitMeasurePersistencePort persistencePort;

    @Override
    public void saveUnitMeasure(String name) {
        if(persistencePort.getUnitMeasure(name).isPresent()) throw new UnitMeasureAlreadyExistsException();
        persistencePort.saveUnitMeasure(new UnitMeasure(null, name));
    }

    @Override
    public List<UnitMeasure> getUnitMeasures() {
        return persistencePort.getUnitMeasures();
    }

}
