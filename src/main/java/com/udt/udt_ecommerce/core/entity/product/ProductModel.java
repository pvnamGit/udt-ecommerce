package com.udt.udt_ecommerce.core.entity.product;

import com.udt.udt_ecommerce.core.entity.shared.BaseEntity;
import com.udt.udt_ecommerce.core.entity.transaction.TransactionItem;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE product_models SET is_active = false WHERE id=?")
@Table(name = "product_models")
@Where(clause = "is_active = true")
public class ProductModel extends BaseEntity {

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "stock_quantity")
  private int stockQuantity;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @Column(name = "color")
  private String color;

  @Column(name = "size")
  private int size;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "productModel")
  private List<TransactionItem> transactionItems;
}
