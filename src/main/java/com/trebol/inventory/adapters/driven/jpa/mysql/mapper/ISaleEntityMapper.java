package com.trebol.inventory.adapters.driven.jpa.mysql.mapper;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.SaleEntity;
import com.trebol.inventory.domain.model.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISaleEntityMapper {

    Sale toSale(SaleEntity source);

    SaleEntity toSaleEntity(Sale source);

}
