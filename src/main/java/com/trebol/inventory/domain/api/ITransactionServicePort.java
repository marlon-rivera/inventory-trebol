package com.trebol.inventory.domain.api;

import com.trebol.inventory.domain.model.DateSortable;
import com.trebol.inventory.domain.model.Invoice;
import com.trebol.inventory.domain.model.ReportType;

import java.util.List;

public interface ITransactionServicePort {

    List<DateSortable> getTransactions();
    Invoice generateReport(ReportType type);

}
