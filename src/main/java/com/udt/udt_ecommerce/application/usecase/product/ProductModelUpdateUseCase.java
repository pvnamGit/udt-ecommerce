package com.udt.udt_ecommerce.application.usecase.product;

import com.udt.udt_ecommerce.api.product.dto.request.ProductModelCreateREQ;
import com.udt.udt_ecommerce.api.product.dto.request.ProductModelUpdateREQ;
import com.udt.udt_ecommerce.api.product.dto.response.ProductModelRESP;
import com.udt.udt_ecommerce.core.service.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductModelUpdateUseCase {
  private final ProductService productService;

  @SneakyThrows
  public ProductModelRESP updateProductModel(
      Long productId, Long productModelId, ProductModelUpdateREQ req) {
    return productService.updateProductModel(productId, productModelId, req);
  }
}
