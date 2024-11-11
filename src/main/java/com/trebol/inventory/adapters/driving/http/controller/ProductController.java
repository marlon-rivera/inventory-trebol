package com.trebol.inventory.adapters.driving.http.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trebol.inventory.adapters.driving.http.dto.request.CreateProduct;
import com.trebol.inventory.adapters.driving.http.mapper.request.IProductRequestMapper;
import com.trebol.inventory.configuration.exceptionhandler.ExceptionResponse;
import com.trebol.inventory.domain.api.IProductServicePort;
import com.trebol.inventory.domain.model.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final IProductServicePort productService;
    private final IProductRequestMapper requestMapper;
    private final ObjectMapper objectMapper;

    @Operation(summary = "Crea un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Conflicto: la marca o categoría no existen",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image,
            @RequestParam("minStock") Integer minStock,
            @RequestParam("maxStock") Integer maxStock,
            @RequestParam("category") String categoryId,
            @RequestParam("brand") String brandId,
            @RequestParam("unitMeasure") String unitMeasureId,
            @RequestParam("measuredValue") String measuredValue) throws Exception {


        Category category = objectMapper.readValue(categoryId, Category.class);
        Brand brand = objectMapper.readValue(brandId, Brand.class);
        UnitMeasure unitMeasure = objectMapper.readValue(unitMeasureId, UnitMeasure.class);
        CreateProduct createProduct = new CreateProduct(
                name,
                description,
                minStock,
                maxStock,
                category,
                brand,
                unitMeasure,
                measuredValue
        );
        productService.createProduct(requestMapper.toProduct(createProduct), image);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Elimina un producto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtiene una lista de productos agrupados por categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos agrupados por categoría")
    })
    @GetMapping("/")
    public ResponseEntity<List<ProductsCategory>> getProductsByCategory() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Obtiene una lista de la base de los productos o los productos planos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos planos")
    })
    @GetMapping("/flat")
    public ResponseEntity<List<Product>> getAllFlatProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @Operation(summary = "Obtiene una lista de alertas por minima cantidad, o fecha de expiracion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de alertas")
    })
    @GetMapping("/alerts")
    public ResponseEntity<List<Alert>> getAlerts(){
        return ResponseEntity.ok(productService.getAllAlerts());
    }
}