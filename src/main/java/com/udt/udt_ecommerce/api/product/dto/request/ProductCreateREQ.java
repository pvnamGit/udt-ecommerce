package com.udt.udt_ecommerce.api.product.dto.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProductCreateREQ {
  @NotNull(message = "Product name is required")
  private String name;

  private String description;

  @NotNull(message = "Product models are required")
  private List<ProductModelCreateREQ> productModels;
}
