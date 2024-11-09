package com.udt.udt_ecommerce.core.repository.billing;

import com.udt.udt_ecommerce.core.entity.billing.Billing;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
  // Get billings by customer (customer id is associated with a transaction)
  @Query("SELECT b FROM Billing b JOIN b.transaction t WHERE t.account.id = :accountId")
  List<Billing> findBillingsByCustomer(Long accountId); // For customer to fetch their own billings

  // Get billings by agency (agency owns products that were purchased)
  @Query(
      "SELECT b FROM Billing b JOIN b.transaction t JOIN t.transactionItems ti JOIN ti.productModel pm JOIN pm.product p WHERE p.agency.id = :agencyId")
  List<Billing> findBillingsByAgency(
      Long agencyId); // For agency to fetch billings related to their products
}
