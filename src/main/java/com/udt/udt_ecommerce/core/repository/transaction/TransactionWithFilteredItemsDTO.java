package com.udt.udt_ecommerce.core.repository.transaction;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TransactionWithFilteredItemsDTO {
    private Long transactionId;
    private BigDecimal totalAmount;
    private Long transactionAt;
    private List<TransactionItemDTO> transactionItems;

    // Constructor, getters, and setters

    @Data
    public static class TransactionItemDTO {
        private Long transactionItemId;
        private int quantity;
        private BigDecimal price;
        private Long productModelId;

        // Constructor, getters, and setters
    }
}