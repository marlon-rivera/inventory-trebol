package com.trebol.inventory.adapters.driving.http.controller;

import com.trebol.inventory.domain.api.ITransactionServicePort;
import com.trebol.inventory.domain.model.DateSortable;
import com.trebol.inventory.domain.model.Dates;
import com.trebol.inventory.domain.model.Invoice;
import com.trebol.inventory.domain.model.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionServicePort transactionService;

    @GetMapping()
    public ResponseEntity<List<DateSortable>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getTransactions());
    }

    @PostMapping("/{reportType}")
    public ResponseEntity<Invoice> getReport(@PathVariable ReportType reportType, @RequestBody Dates dates) {
        System.out.println(dates);
        return ResponseEntity.ok(transactionService.generateReport(reportType, dates));
    }

}
