package com.udt.udt_ecommerce.core.repository.transaction;

import com.udt.udt_ecommerce.api.transaction.TransactionController;
import com.udt.udt_ecommerce.core.entity.account.Account;
import com.udt.udt_ecommerce.core.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  // For admin: fetch all transactions (default provided by JpaRepository)

  // For customer: fetch transactions by customer account ID
  @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId")
  List<Transaction> findAllByAccountId(@Param("accountId") Long accountId);

  // For agency: fetch transactions where their products are in the transaction items
  //    @Query("SELECT DISTINCT t FROM Transaction t JOIN t.transactionItems ti JOIN ti.productModel
  // pm "
  //            + "JOIN pm.product p WHERE p.agency.id = :agencyId")
  //    List<Transaction> findAllByAgencyId(@Param("agencyId") Long agencyId);

  @Query(
      """
    SELECT DISTINCT t
    FROM Transaction t
    JOIN FETCH t.transactionItems ti
    WHERE ti.agency.id = :agencyId
    """)
  List<Transaction> findAllByAgencyId(@Param("agencyId") Long agencyId);
}
