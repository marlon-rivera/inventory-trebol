package com.trebol.inventory.adapters.driven.jpa.mysql.mapper;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.BatchEntity;
import com.trebol.inventory.domain.model.Batch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBatchEntityMapper {

    @Mapping(target = "product.quantityAvalaible", ignore = true)
    @Mapping(target = "product.batchId", ignore = true)
    @Mapping(target = "product.expirationDate", ignore = true)
    Batch toBatch(BatchEntity batchEntity);

    BatchEntity toBatchEntity(Batch batch);

    List<Batch> toBatches(List<BatchEntity> batchEntities);

}
