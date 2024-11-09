package com.udt.udt_ecommerce.core.entity.account;

import com.udt.udt_ecommerce.core.entity.agency.Agency;
import com.udt.udt_ecommerce.core.entity.customer.Customer;
import com.udt.udt_ecommerce.core.entity.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
@Where(clause = "is_active = true")
@SQLDelete(sql = "UPDATE accounts SET is_active = false WHERE id=?")
public class Account extends BaseEntity {

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "authority", nullable = false)
  @Enumerated(EnumType.STRING)
  private Authority authority = Authority.CUSTOMER;

  @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
  private Customer customer;

  @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
  private Agency agency;
}
