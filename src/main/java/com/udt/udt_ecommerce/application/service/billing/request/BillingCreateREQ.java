package com.udt.udt_ecommerce.application.service.billing.request;

import com.udt.udt_ecommerce.core.entity.billing.PaymentMethod;
import com.udt.udt_ecommerce.core.entity.billing.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BillingCreateREQ {
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private BigDecimal amount;
}
