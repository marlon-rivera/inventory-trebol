package com.trebol.inventory.adapters.driven.jpa.mysql.mapper;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.trebol.inventory.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface IProductEntityMapper {


    Product toProduct(ProductEntity source);

    ProductEntity toProductEntity(Product source);

    List<Product> toProductsList(List<ProductEntity> source);

    default Optional<Product> toOptionalProduct(Optional<ProductEntity> source) {
        return source.map(this::toProduct);
    }
}

