package com.trebol.inventory.adapters.driving.http.controller;

import com.trebol.inventory.adapters.driving.http.dto.request.CreateProduct;
import com.trebol.inventory.adapters.driving.http.mapper.request.IProductRequestMapper;
import com.trebol.inventory.configuration.exceptionhandler.ExceptionResponse;
import com.trebol.inventory.domain.api.IProductServicePort;
import com.trebol.inventory.domain.model.ProductsCategory;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final IProductServicePort productService;
    private final IProductRequestMapper requestMapper;

    /**
     * Crea un nuevo producto.
     *
     * @param createProduct el producto a crear
     * @return 201 Created si el producto se creó exitosamente,
     *         409 Conflict si la marca o la categoría no existen
     */
    @Operation(summary = "Crea un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Conflicto: la marca o categoría no existen", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/")
    public ResponseEntity<Void> createProduct(@Valid @RequestBody CreateProduct createProduct) {
        productService.createProduct(requestMapper.toProduct(createProduct));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id el ID del producto a eliminar
     * @return 204 No Content si el producto se eliminó exitosamente,
     *         404 Not Found si el producto no existe
     */
    @Operation(summary = "Elimina un producto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene una lista de productos agrupados por categoría.
     *
     * @return 200 OK y la lista de productos agrupados por categoría
     */
    @Operation(summary = "Obtiene una lista de productos agrupados por categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos agrupados por categoría")
    })
    @GetMapping("/")
    public ResponseEntity<List<ProductsCategory>> getProductsByCategory() {
        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }
}
