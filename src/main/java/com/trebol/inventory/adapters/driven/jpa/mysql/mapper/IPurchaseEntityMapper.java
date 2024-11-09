package com.trebol.inventory.adapters.driven.jpa.mysql.mapper;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.PurchaseEntity;
import com.trebol.inventory.domain.model.Purchase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPurchaseEntityMapper {

    PurchaseEntity toPurchaseEntity(Purchase purchase);

    Purchase toPurchase(PurchaseEntity entity);

}