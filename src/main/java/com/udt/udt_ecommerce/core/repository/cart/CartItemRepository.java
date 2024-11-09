package com.udt.udt_ecommerce.core.repository.cart;

import com.udt.udt_ecommerce.core.entity.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {}
