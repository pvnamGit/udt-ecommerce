package com.udt.udt_ecommerce.api.transaction.request;

import com.udt.udt_ecommerce.core.entity.billing.PaymentMethod;
import com.udt.udt_ecommerce.core.entity.billing.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TransactionCreateREQ {
    private List<TransactionItemREQ> items;

    @NotNull(message = "Payment status is required")
    private PaymentStatus paymentStatus;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    public BigDecimal calculateTotalAmount() {
        return items.stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Data
    public static class TransactionItemREQ {
        private Long productModelId;
        private int quantity;
        private BigDecimal price;
    }
}
