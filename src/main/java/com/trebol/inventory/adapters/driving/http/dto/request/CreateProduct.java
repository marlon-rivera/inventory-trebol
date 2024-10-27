package com.trebol.inventory.adapters.driving.http.dto.request;

import com.trebol.inventory.domain.model.Brand;
import com.trebol.inventory.domain.model.Category;
import com.trebol.inventory.domain.model.UnitMeasure;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateProduct {

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Integer minStock;
    @NotNull
    private Integer maxStock;
    @NotNull
    private Category category;
    @NotNull
    private Brand brand;
    @NotNull
    private UnitMeasure unitMeasure;
    @NotNull
    private String measuredValue;

}
