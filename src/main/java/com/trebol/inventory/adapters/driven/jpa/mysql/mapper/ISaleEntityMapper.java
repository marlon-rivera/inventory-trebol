package com.trebol.inventory.adapters.driven.jpa.mysql.mapper;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.SaleEntity;
import com.trebol.inventory.domain.model.Sale;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ISaleEntityMapper {

    Sale toSale(SaleEntity source);

    SaleEntity toSaleEntity(Sale source);

    List<Sale> toSales(List<SaleEntity> source);

}
