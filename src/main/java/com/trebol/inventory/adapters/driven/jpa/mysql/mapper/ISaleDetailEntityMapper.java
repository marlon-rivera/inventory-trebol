package com.trebol.inventory.adapters.driven.jpa.mysql.mapper;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.SaleDetailEntity;
import com.trebol.inventory.domain.model.SaleDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ISaleDetailEntityMapper {

    SaleDetail toSaleDetail(SaleDetailEntity source);

    SaleDetailEntity toSaleDetailEntity(SaleDetail source);

    List<SaleDetail> toSaleDetails(List<SaleDetailEntity> source);

    List<SaleDetailEntity> toSaleDetailEntities(List<SaleDetail> source);

}
