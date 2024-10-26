package com.trebol.inventory.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClient {

    @NotNull
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String phone;

}
