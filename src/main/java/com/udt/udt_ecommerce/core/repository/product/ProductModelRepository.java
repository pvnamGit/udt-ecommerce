package com.udt.udt_ecommerce.core.repository.product;

import com.udt.udt_ecommerce.core.entity.product.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductModelRepository extends JpaRepository<ProductModel, Long> {}
