package com.trebol.inventory.adapters.driving.http.dto.request;

import com.trebol.inventory.domain.model.Client;
import com.trebol.inventory.domain.model.SaleDetail;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class CreateSale {

    @NotNull
    private Client client;
    @NotNull
    private List<SaleDetail> details;

}
