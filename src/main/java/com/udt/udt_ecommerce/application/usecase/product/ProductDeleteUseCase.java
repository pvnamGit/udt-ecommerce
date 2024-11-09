package com.udt.udt_ecommerce.application.usecase.product;

import com.udt.udt_ecommerce.core.service.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductDeleteUseCase {
  private final ProductService productService;

  public void deleteProduct(Long productId) {
    productService.deleteProduct(productId);
  }
}
