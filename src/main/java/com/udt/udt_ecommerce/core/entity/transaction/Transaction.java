package com.udt.udt_ecommerce.core.entity.transaction;

import com.udt.udt_ecommerce.core.entity.account.Account;
import com.udt.udt_ecommerce.core.entity.billing.Billing;
import com.udt.udt_ecommerce.core.entity.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE transactions SET is_active = false WHERE id=?")
@Table(name = "transactions")
@Where(clause = "is_active = true")
public class Transaction extends BaseEntity {

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "transaction_at", nullable = false)
    private Long transactionAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_id", nullable = false)
    private Billing billing;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction")
    private List<TransactionItem> transactionItems;
}
