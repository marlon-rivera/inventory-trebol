package com.trebol.inventory.adapters.driving.http.controller;

import com.trebol.inventory.adapters.driving.http.dto.request.CreateCategory;
import com.trebol.inventory.configuration.exceptionhandler.ExceptionResponse;
import com.trebol.inventory.domain.api.ICategoryServicePort;
import com.trebol.inventory.domain.model.Category;
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
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryServicePort categoryService;

    /**
     * Crea una nueva categoría.
     *
     * @param createCategory la categoría a crear
     * @return 201 Created si la categoría se creó exitosamente,
     *         409 Conflict si una categoría con el mismo nombre ya existe
     */
    @Operation(summary = "Crea una nueva categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente"),
            @ApiResponse(responseCode = "409", description = "Conflicto: la categoría ya existe con el mismo nombre", content = @Content ( mediaType
            = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @PostMapping()
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CreateCategory createCategory) {
        categoryService.saveCategory(createCategory.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Obtiene una lista de todas las categorías.
     *
     * @return 200 OK y la lista de categorías
     */
    @Operation(summary = "Obtiene una lista de todas las categorías")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida exitosamente")
    })
    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
