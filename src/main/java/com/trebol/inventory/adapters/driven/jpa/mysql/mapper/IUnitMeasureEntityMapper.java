package com.trebol.inventory.adapters.driven.jpa.mysql.mapper;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.UnitMeasureEntity;
import com.trebol.inventory.domain.model.UnitMeasure;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface IUnitMeasureEntityMapper {

    UnitMeasure toUnitMeasure(UnitMeasureEntity entity);

    UnitMeasureEntity toUnitMeasure(UnitMeasure source);

    List<UnitMeasure> toUnitMeasures(List<UnitMeasureEntity> entities);

    default Optional<UnitMeasure> toOptionalUnitMeasure(Optional<UnitMeasureEntity> entity){
        return entity.map(this::toUnitMeasure);
    }

}
