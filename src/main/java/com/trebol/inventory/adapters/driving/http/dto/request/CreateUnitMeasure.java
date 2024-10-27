package com.trebol.inventory.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUnitMeasure {

    @NotNull(message = "El nombre no puede ser nulo")
    private String name;

}
