package com.udt.udt_ecommerce.api.billing.response;

import com.udt.udt_ecommerce.core.entity.billing.Billing;
import com.udt.udt_ecommerce.core.entity.billing.PaymentMethod;
import com.udt.udt_ecommerce.core.entity.billing.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillingRESP {
    private Long id;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private Long paymentAt;
    private BigDecimal amount;
    private Long transactionId;

    public BillingRESP(Billing billing) {
        this.id = billing.getId();
        this.paymentMethod = billing.getPaymentMethod();
        this.status = billing.getStatus();
        this.paymentAt = billing.getPaymentAt();
        this.amount = billing.getAmount();
        this.transactionId = billing.getTransaction().getId();
    }

}
