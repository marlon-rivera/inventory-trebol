package com.trebol.inventory.adapters.driving.http.mapper.request;

import com.trebol.inventory.adapters.driving.http.dto.request.CreateSupplier;
import com.trebol.inventory.domain.model.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISupplierRequestMapper {

    Supplier toSupplier(CreateSupplier createSupplier);

}
