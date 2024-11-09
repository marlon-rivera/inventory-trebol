package com.trebol.inventory.adapters.driving.http.dto.request;

import com.trebol.inventory.domain.model.PurchaseDetail;
import com.trebol.inventory.domain.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePurchase {

    private Supplier supplier;
    private List<PurchaseDetail> details;
    private LocalDate purchaseDate;

}
