package com.trebol.inventory.adapters.driving.http.controller;

import com.trebol.inventory.adapters.driving.http.dto.request.CreatePurchase;
import com.trebol.inventory.configuration.exceptionhandler.ExceptionResponse;
import com.trebol.inventory.domain.api.IPurchaseServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final IPurchaseServicePort service;

    @Operation(summary = "Crea una nueva compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Compra creada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Conflicto: un producto no existe",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping()
    public ResponseEntity<Void> savePurchase(@RequestBody CreatePurchase createPurchase){
        service.createPurchase(createPurchase.getSupplier(), createPurchase.getDetails(), createPurchase.getPurchaseDate());
        return ResponseEntity.ok().build();
    }

}
