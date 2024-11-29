package com.trebol.inventory.adapters.driving.http.controller;

import com.trebol.inventory.adapters.driving.http.dto.request.CreateUnitMeasure;
import com.trebol.inventory.configuration.exceptionhandler.ExceptionResponse;
import com.trebol.inventory.domain.api.IUnitMeasureServicePort;
import com.trebol.inventory.domain.model.UnitMeasure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unitMeasure")
@RequiredArgsConstructor
public class UnitMeasureController {

    private final IUnitMeasureServicePort unitMeasureService;

    /**
     * Crea una nueva marca.
     *
     * @param createUnitMeasure la marca a crear
     * @return 201 Created si la unidad de medida se creó exitosamente,
     *         409 Conflict si una unidad de medida con el mismo nombre ya existe
     */
    @Operation(summary = "Crea una nueva unidad de medida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Unidad de medida creada exitosamente"),
            @ApiResponse(responseCode = "409", description = "Conflicto: la unidad de medida ya existe con el mismo nombre", content =
            @Content(mediaType = "application/json",schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping()
    public ResponseEntity<Void> createUnitMeasure(@Valid @RequestBody CreateUnitMeasure createUnitMeasure){
        unitMeasureService.saveUnitMeasure(createUnitMeasure.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Obtiene una lista de todas las unidades de medida.
     *
     * @return 200 OK y la lista de unidades de medida
     */
    @Operation(summary = "Obtiene una lista de todas las unidades de medida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de unidades de medida obtenida exitosamente")
    })
    @GetMapping()
    public ResponseEntity<List<UnitMeasure>> getUnitsMeasure(){
        return ResponseEntity.ok(unitMeasureService.getUnitMeasures());
    }

}
