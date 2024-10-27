package com.trebol.inventory.adapters.driven.jpa.mysql.adapter;

import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.IUnitMeasureEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.IUnitMeasureRepository;
import com.trebol.inventory.domain.model.UnitMeasure;
import com.trebol.inventory.domain.spi.IUnitMeasurePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UnitMeasureAdapter implements IUnitMeasurePersistencePort {

    private final IUnitMeasureRepository repository;
    private final IUnitMeasureEntityMapper mapper;

    @Override
    public void saveUnitMeasure(UnitMeasure unitMeasure) {
        repository.save(mapper.toUnitMeasure(unitMeasure));
    }

    @Override
    public List<UnitMeasure> getUnitMeasures() {
        return mapper.toUnitMeasures(repository.findAll());
    }

    @Override
    public Optional<UnitMeasure> getUnitMeasure(String name) {
        return mapper.toOptionalUnitMeasure(repository.findByName(name));
    }

    @Override
    public Optional<UnitMeasure> getUnitMeasureById(Long id) {
        return mapper.toOptionalUnitMeasure(repository.findById(id));
    }
}
