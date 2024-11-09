package com.udt.udt_ecommerce.application.usecase.product;

import com.udt.udt_ecommerce.core.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductModelDeleteUseCase {
  private final ProductService productService;

  public void deleteProductModel(Long productId, Long productModelId) {
    productService.deleteProductModel(productId, productModelId);
  }
}
