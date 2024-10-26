package com.trebol.inventory.adapters.driving.http.mapper.request;

import com.trebol.inventory.adapters.driving.http.dto.request.CreateProduct;
import com.trebol.inventory.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IProductRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    Product toProduct(CreateProduct createProduct);

}
