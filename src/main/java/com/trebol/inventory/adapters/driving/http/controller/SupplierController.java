package com.trebol.inventory.adapters.driving.http.controller;

import com.trebol.inventory.adapters.driving.http.dto.request.CreateSupplier;
import com.trebol.inventory.adapters.driving.http.dto.request.SupplierUpdate;
import com.trebol.inventory.adapters.driving.http.mapper.request.ISupplierRequestMapper;
import com.trebol.inventory.configuration.exceptionhandler.ExceptionResponse;
import com.trebol.inventory.domain.api.ISupplierServicePort;
import com.trebol.inventory.domain.model.Supplier;
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
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final ISupplierServicePort servicePort;
    private final ISupplierRequestMapper mapper;

    @Operation(summary = "Crea un nuevo proveedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proveedor creado exitosamente"),
            @ApiResponse(responseCode = "409", description = "Conflicto: El proveedor con ese id ya existe.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/")
    public ResponseEntity<Void> createSupplier(@Valid @RequestBody CreateSupplier createSupplier){
        servicePort.saveSupplier(mapper.toSupplier(createSupplier));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Operation(summary = "Elimina un proveedor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Proveedor eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable String id){
        servicePort.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualiza un proveedor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Proveedor actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSupplier(@PathVariable String id, @RequestBody SupplierUpdate supplierUpdate){
        Supplier supplier = new Supplier(id, null, supplierUpdate.getAddress(), supplierUpdate.getPhone(), supplierUpdate.getEmail(), null, false);
        servicePort.updateSupplier(supplier);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtiene todos los proveedores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de proveedores recuperada exitosamente")
    })
    @GetMapping("/")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = servicePort.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

}
