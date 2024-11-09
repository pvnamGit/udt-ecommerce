package com.udt.udt_ecommerce.core.repository.customer;

import com.udt.udt_ecommerce.core.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}
