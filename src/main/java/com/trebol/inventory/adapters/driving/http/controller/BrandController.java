package com.trebol.inventory.adapters.driving.http.controller;

import com.trebol.inventory.adapters.driving.http.dto.request.CreateBrand;
import com.trebol.inventory.configuration.exceptionhandler.ExceptionResponse;
import com.trebol.inventory.domain.api.IBrandServicePort;
import com.trebol.inventory.domain.model.Brand;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {

    private final IBrandServicePort brandService;

    /**
     * Crea una nueva marca.
     *
     * @param brand la marca a crear
     * @return 201 Created si la marca se creó exitosamente,
     *         409 Conflict si una marca con el mismo nombre ya existe
     */
    @Operation(summary = "Crea una nueva marca")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Marca creada exitosamente"),
            @ApiResponse(responseCode = "409", description = "Conflicto: la marca ya existe con el mismo nombre", content =
            @Content(mediaType = "application/json",schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/")
    public ResponseEntity<Void> createBrand(@Valid @RequestBody CreateBrand brand) {
        brandService.saveBrand(brand.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Obtiene una lista de todas las marcas.
     *
     * @return 200 OK y la lista de marcas
     */
    @Operation(summary = "Obtiene una lista de todas las marcas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de marcas obtenida exitosamente")
    })
    @GetMapping("/")
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        return ResponseEntity.ok(brands);
    }
}
