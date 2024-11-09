package com.udt.udt_ecommerce.core.entity.billing;

import com.udt.udt_ecommerce.core.entity.shared.BaseEntity;
import com.udt.udt_ecommerce.core.entity.transaction.Transaction;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE billing SET is_active = false WHERE id=?")
@Where(clause = "is_active = true")
@Table(name = "billing")
public class Billing extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @Column(name = "payment_at", nullable = false)
    private Long paymentAt;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @OneToOne(mappedBy = "billing", fetch = FetchType.LAZY)
    private Transaction transaction;
}