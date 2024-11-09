package com.udt.udt_ecommerce.core.entity.agency;

import com.udt.udt_ecommerce.core.entity.account.Account;
import com.udt.udt_ecommerce.core.entity.shared.BaseEntity;
import com.udt.udt_ecommerce.core.entity.shared.Gender;
import com.udt.udt_ecommerce.core.entity.transaction.TransactionItem;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "agencies")
@SQLDelete(sql = "UPDATE agencies SET is_active = false WHERE id=?")
@Where(clause = "is_active = true")
public class Agency extends BaseEntity {
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "year_of_birth")
  private String yearOfBirth;

  @Column(name = "phone")
  private String phone;

  @Column(name = "gender")
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "account_id")
  private Account account;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "agency")
  List<TransactionItem> transactionItems;
}
