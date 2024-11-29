package com.trebol.inventory.adapters.driving.http.controller;

import com.trebol.inventory.adapters.driving.http.dto.request.CreateSale;
import com.trebol.inventory.domain.api.ISaleServicePort;
import com.trebol.inventory.domain.model.Invoice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {

    private final ISaleServicePort servicePort;

    @PostMapping()
    public ResponseEntity<Invoice> saveSale(@Valid @RequestBody CreateSale createSale){
        return ResponseEntity.ok(servicePort.saveSale(createSale.getDetails(), createSale.getClient(), createSale.getTypeInvoice(), createSale.getSaleDate()));
    }

}
