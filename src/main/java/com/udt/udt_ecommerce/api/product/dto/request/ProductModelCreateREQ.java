package com.udt.udt_ecommerce.api.product.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModelCreateREQ {
    @NotNull(message = "Model name is required")
    private String name;

    private String description;

    @NotNull(message = "Stock quantity is required")
    private Integer stockQuantity;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    private String color;
    private Integer size;
}
