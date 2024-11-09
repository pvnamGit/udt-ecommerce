package com.udt.udt_ecommerce.core.service.transaction;

import com.udt.udt_ecommerce.api.transaction.request.TransactionCreateREQ;
import com.udt.udt_ecommerce.api.transaction.response.TransactionRESP;
import com.udt.udt_ecommerce.core.entity.transaction.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionRESP createTransaction(TransactionCreateREQ req);
    List<TransactionRESP> listAllTransactions();

}
