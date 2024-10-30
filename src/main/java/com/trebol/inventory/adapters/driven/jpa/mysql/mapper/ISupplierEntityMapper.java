package com.trebol.inventory.adapters.driven.jpa.mysql.mapper;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.SupplierEntity;
import com.trebol.inventory.domain.model.Supplier;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ISupplierEntityMapper {

    Supplier toSupplier(SupplierEntity supplierEntity);

    SupplierEntity toSupplierEntity(Supplier supplier);

    default Optional<Supplier> toOptionalSupplier(Optional<SupplierEntity> supplierEntity){
        return supplierEntity.map(this::toSupplier);
    }

    List<Supplier> toSupplierList(List<SupplierEntity> supplierEntities);

}
