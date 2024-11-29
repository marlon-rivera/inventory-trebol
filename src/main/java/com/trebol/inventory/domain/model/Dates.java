package com.trebol.inventory.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@ToString
public class Dates {

    @NotNull
    private LocalDate start;
    @NotNull
    private LocalDate end;

}
