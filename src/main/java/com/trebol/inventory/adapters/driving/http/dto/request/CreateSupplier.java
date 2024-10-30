package com.trebol.inventory.adapters.driving.http.dto.request;

import com.trebol.inventory.domain.model.TypeSupplier;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateSupplier {

    @NotNull
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    @NotNull
    private String email;
    @NotNull
    private TypeSupplier type;
}
