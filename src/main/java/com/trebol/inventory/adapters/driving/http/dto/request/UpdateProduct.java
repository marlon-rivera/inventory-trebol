package com.trebol.inventory.adapters.driving.http.dto.request;

import com.trebol.inventory.domain.model.Supplier;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProduct {

    private List<Supplier> suppliers;
    private String description;
    private BigInteger minStock;
    private BigInteger maxStock;
    private BigDecimal unitPrice;

}
