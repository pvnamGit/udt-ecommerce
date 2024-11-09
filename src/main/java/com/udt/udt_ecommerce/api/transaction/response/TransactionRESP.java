package com.udt.udt_ecommerce.api.transaction.response;

import com.udt.udt_ecommerce.api.product.dto.response.ProductModelRESP;
import com.udt.udt_ecommerce.api.product.dto.response.ProductRESP;
import com.udt.udt_ecommerce.core.entity.transaction.Transaction;
import com.udt.udt_ecommerce.core.entity.transaction.TransactionItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class TransactionRESP {
  private Long id;
  private BigDecimal totalAmount;
  private Long transactionAt;
  private Long billingId;
  private List<TransactionItemRESP> items;

  public TransactionRESP(Transaction transaction, List<TransactionItem> transactionItems) {
    this.id = transaction.getId();
    this.totalAmount = calculateTotalAmount(transactionItems);
    this.transactionAt = transaction.getTransactionAt();
    this.billingId = transaction.getBilling().getId();
    this.items =
        transactionItems.stream().map(TransactionItemRESP::new).collect(Collectors.toList());
  }

  public BigDecimal calculateTotalAmount(List<TransactionItem> transactionItems) {
    return transactionItems.stream()
        .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
