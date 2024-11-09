package com.udt.udt_ecommerce.core.repository.cart;

import com.udt.udt_ecommerce.core.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {}
