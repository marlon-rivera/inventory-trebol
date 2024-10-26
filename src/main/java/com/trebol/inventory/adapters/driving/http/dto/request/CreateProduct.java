package com.trebol.inventory.adapters.driving.http.dto.request;

import com.trebol.inventory.domain.model.Brand;
import com.trebol.inventory.domain.model.Category;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateProduct {

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String image;
    @NotNull
    private Integer minStock;
    @NotNull
    private Integer maxStock;
    @NotNull
    private Category category;
    @NotNull
    private Brand brand;
    @NotNull
    private String unitOfMeasure;

}
