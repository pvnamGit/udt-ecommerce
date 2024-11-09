package com.udt.udt_ecommerce.api.transaction;

import com.udt.udt_ecommerce.api.shared.BaseEntityResponse;
import com.udt.udt_ecommerce.api.shared.BasePaginationResponse;
import com.udt.udt_ecommerce.api.transaction.request.TransactionCreateREQ;
import com.udt.udt_ecommerce.api.transaction.response.TransactionRESP;
import com.udt.udt_ecommerce.application.usecase.transaction.TransactionCreateUseCase;
import com.udt.udt_ecommerce.application.usecase.transaction.TransactionListUseCase;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${url.prefix}/transactions")
@AllArgsConstructor
public class TransactionController {
  private final TransactionCreateUseCase transactionCreateUseCase;
  private final TransactionListUseCase transactionListUseCase;

  @GetMapping("")
  @PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('AGENCY')")
  public BasePaginationResponse listTransactions() {
    List<TransactionRESP> transactions = transactionListUseCase.listAllTransactions();
    return BasePaginationResponse.success(transactions, transactions.size());
  }

  @PostMapping("")
  @PreAuthorize("hasAuthority('CUSTOMER')")
  public BaseEntityResponse<TransactionRESP> createTransaction(
      @Valid @RequestBody TransactionCreateREQ req) {
    TransactionRESP transaction = transactionCreateUseCase.createTransaction(req);
    return BaseEntityResponse.success(transaction);
  }
}
