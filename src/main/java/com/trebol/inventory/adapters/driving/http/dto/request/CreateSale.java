package com.trebol.inventory.adapters.driving.http.dto.request;

import com.trebol.inventory.domain.model.Client;
import com.trebol.inventory.domain.model.SaleDetail;
import com.trebol.inventory.domain.model.TypeInvoice;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
public class CreateSale {

    @NotNull
    private Client client;
    @NotNull
    private List<SaleDetail> details;
    @NotNull
    private TypeInvoice typeInvoice;
    @NotNull
    private LocalDate saleDate;

}
