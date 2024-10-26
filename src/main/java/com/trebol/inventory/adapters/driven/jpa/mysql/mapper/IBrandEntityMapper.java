package com.trebol.inventory.adapters.driven.jpa.mysql.mapper;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.trebol.inventory.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {

    Brand toBrand(BrandEntity brandEntity);

    BrandEntity toBrandEntity(Brand brand);

    List<Brand> toBrandList(List<BrandEntity> brandEntities);

    default Optional<Brand> toBrandOptional(Optional<BrandEntity> brandEntity){
        return brandEntity.map(this::toBrand);
    }
}
