package com.trebol.inventory.adapters.driven.jpa.mysql.mapper;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.BatchEntity;
import com.trebol.inventory.domain.model.Batch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface IBatchEntityMapper {

    @Mapping(target = "product.expirationDate", ignore = true)
    Batch toBatch(BatchEntity batchEntity);

    BatchEntity toBatchEntity(Batch batch);

    List<Batch> toBatches(List<BatchEntity> batchEntities);

    default Optional<Batch> toOptionalBatch(Optional<BatchEntity> batchEntityOp){
        return batchEntityOp.map(this::toBatch);
    }

}
