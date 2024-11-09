package com.udt.udt_ecommerce.application.usecase.transaction;

import com.udt.udt_ecommerce.api.transaction.response.TransactionRESP;
import com.udt.udt_ecommerce.core.service.transaction.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionListUseCase {
  private final TransactionService transactionService;

  public List<TransactionRESP> listAllTransactions() {
    return transactionService.listAllTransactions();
  }
}
