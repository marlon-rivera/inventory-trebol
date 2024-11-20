package com.trebol.inventory.adapters.driving.http.controller;

import com.trebol.inventory.domain.api.ITransactionServicePort;
import com.trebol.inventory.domain.model.DateSortable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionServicePort transactionService;

    @GetMapping("/")
    public ResponseEntity<List<DateSortable>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getTransactions());
    }

}
