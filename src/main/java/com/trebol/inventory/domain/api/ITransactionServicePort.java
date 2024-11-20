package com.trebol.inventory.domain.api;

import com.trebol.inventory.domain.model.DateSortable;

import java.util.List;

public interface ITransactionServicePort {

    List<DateSortable> getTransactions();

}
