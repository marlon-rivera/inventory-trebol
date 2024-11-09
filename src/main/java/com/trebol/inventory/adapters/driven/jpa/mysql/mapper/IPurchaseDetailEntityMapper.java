package com.trebol.inventory.adapters.driven.jpa.mysql.mapper;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.PurchaseDetailEntity;
import com.trebol.inventory.domain.model.PurchaseDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPurchaseDetailEntityMapper {

    PurchaseDetailEntity toPurchaseDetailEntity(PurchaseDetail pd);

    PurchaseDetail toPurchaseDetailEntity(PurchaseDetailEntity pd);

    List<PurchaseDetailEntity> toPurchaseDetailEntityList(List<PurchaseDetail> pds);

    List<PurchaseDetail> toPurchaseDetail(List<PurchaseDetailEntity> pds);

}
