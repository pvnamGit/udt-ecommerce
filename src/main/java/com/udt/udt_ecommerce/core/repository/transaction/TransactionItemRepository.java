package com.udt.udt_ecommerce.core.repository.transaction;

import com.udt.udt_ecommerce.core.entity.transaction.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {}
