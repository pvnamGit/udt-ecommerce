package com.udt.udt_ecommerce.application.usecase.transaction;

import com.udt.udt_ecommerce.api.transaction.request.TransactionCreateREQ;
import com.udt.udt_ecommerce.api.transaction.response.TransactionRESP;
import com.udt.udt_ecommerce.core.service.transaction.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionCreateUseCase {
    private final TransactionService transactionService;

    public TransactionRESP createTransaction(TransactionCreateREQ req) {
        return transactionService.createTransaction(req);
    }
}
