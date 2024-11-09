package com.udt.udt_ecommerce.application.usecase.product;

import com.udt.udt_ecommerce.api.product.dto.response.ProductRESP;
import com.udt.udt_ecommerce.core.service.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductGetDetailUseCase {
  private final ProductService productService;

  @SneakyThrows
  public ProductRESP productGetDetail(Long id) {
    return productService.detail(id);
  }
}
