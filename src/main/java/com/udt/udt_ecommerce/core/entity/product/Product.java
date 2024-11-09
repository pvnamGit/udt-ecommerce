package com.udt.udt_ecommerce.core.entity.product;

import com.udt.udt_ecommerce.core.entity.agency.Agency;
import com.udt.udt_ecommerce.core.entity.shared.BaseEntity;
import com.udt.udt_ecommerce.core.entity.transaction.Transaction;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE products SET is_active = false WHERE id=?")
@Table(name = "products")
@Where(clause = "is_active = true")
public class Product extends BaseEntity {

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description")
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "agency_id", nullable = false)
  private Agency agency;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
  private List<ProductModel> productModels;
}
