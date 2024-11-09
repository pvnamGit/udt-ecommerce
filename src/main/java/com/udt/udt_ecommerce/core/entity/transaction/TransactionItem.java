package com.udt.udt_ecommerce.core.entity.transaction;

import com.udt.udt_ecommerce.core.entity.agency.Agency;
import com.udt.udt_ecommerce.core.entity.customer.Customer;
import com.udt.udt_ecommerce.core.entity.product.ProductModel;
import com.udt.udt_ecommerce.core.entity.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE transaction_items SET is_active = false WHERE id=?")
@Table(name = "transaction_items")
@Where(clause = "is_active = true")
public class TransactionItem extends BaseEntity {

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id", nullable = false)
    private Agency agency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_model_id", nullable = false)
    private ProductModel productModel;

}
